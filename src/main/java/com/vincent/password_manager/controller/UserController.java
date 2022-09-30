package com.vincent.password_manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vincent.password_manager.bean.OptionalData;
import com.vincent.password_manager.bean.User;
import com.vincent.password_manager.http.HttpResponseThrowers;
import com.vincent.password_manager.http.Response;
import com.vincent.password_manager.service.UserService;
import com.vincent.password_manager.utils.ConstantType;
import com.vincent.password_manager.utils.SecurityUtils;

@RestController
@RequestMapping("/users")
@PreAuthorize(ConstantType.HAS_ANY_ALL_AUTHORITY)
public class UserController 
{
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN')")
    @GetMapping("{userID}")
    public User getUser(@PathVariable int userID)
    {
        return userService.get(userID);
    }


    @PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN')")
    @GetMapping("all")
    public List<User> getAllUser()
    {
        return userService.getAll();
    }

    @GetMapping("/all/mask")
    public List<User> getAllMaskUser()
    {
        return this.userService.getAllMaskUser();
    }

    @GetMapping("/all/mask/{groupID}")
    public List<User> getAllMaskUser(@PathVariable int groupID)
    {
        return this.userService.getAllMaskUser(groupID);
    }

    @GetMapping
    public User getCurrentLoginUser(HttpServletResponse response, Authentication authentication)
    {
        User user = userService.getCurrentLoginUser(authentication);

        return user;
    }

    @GetMapping("/exist/{username}")
    public Response isUsernameExist(@PathVariable String username)
    {
        int id = userService.isUsernameExist(username);
        
        if(id > 0)
            return new Response(true, id + "");
        else
            return (Response) HttpResponseThrowers.throwBadRequest("username does not exist");
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN')")
    public Response registerUser(HttpServletResponse response, @RequestBody User user)
    {
        if(!userService.isValidUsername(user.getUsername()))
        {
            return (Response) SecurityUtils.sendResponse(response, 400, "username does not pass check", null);
        }
        else
        {
            userService.registerUser(user);
            return new Response(true, 201, "new User created").setStatus(response);
        }
    }

    @PutMapping
    @PreAuthorize(ConstantType.HAS_ANY_ALL_AUTHORITY)
    public Response modifyCurrentUser(HttpServletResponse response, Authentication authentication, @RequestBody User user)
    {
        User loginUser = userService.getCurrentLoginUser(authentication);

        return new Response(userService.modifyUser(loginUser, user), "Modify User success").setStatus(response);
    }

    @PreAuthorize("hasAnyAuthority('" + ConstantType.USER_ROLE_OWNER + "', '" + ConstantType.USER_ROLE_ADMIN + "')")
    @PutMapping("/{id}")
    public Response modifyUser(@PathVariable int id, @RequestBody User user)
    {
        User origin = userService.getUser(id);
        
        if(origin == null)
            return new Response(false, "no origin user found");

        return new Response(userService.modifyUser(origin, user));
    }

    @PreAuthorize(ConstantType.HAS_ANY_ALL_AUTHORITY)
    @PutMapping("/changePassword")
    public Response changePassword(@RequestBody OptionalData optional, Authentication authentication)
    {
        User user = this.userService.getCurrentLoginUser(authentication);
        return this.userService.changePassword(user, optional);
    }
}
