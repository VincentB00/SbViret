package com.vincent.password_manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vincent.password_manager.bean.Group;
import com.vincent.password_manager.bean.GroupNode;
import com.vincent.password_manager.bean.User;
import com.vincent.password_manager.http.Response;
import com.vincent.password_manager.service.GroupService;
import com.vincent.password_manager.service.UserService;
import com.vincent.password_manager.utils.ConstantType;

@RestController
@RequestMapping("/groups")
@PreAuthorize(ConstantType.HAS_ANY_ALL_AUTHORITY)
public class GroupController 
{
    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    @GetMapping("{groupID}")
    public Group get(@PathVariable int groupID, Authentication authentication)
    {
        User user = userService.getCurrentLoginUser(authentication);
        this.groupService.checkIsPermited(groupID, user);
        return this.groupService.getById(groupID);
    }

    @GetMapping
    public List<Group> getAll(Authentication authentication)
    {
        User user = userService.getCurrentLoginUser(authentication);
        return groupService.getAll(user.getId());
    }

    @GetMapping("/related")
    public List<Group> getAllRelated(Authentication authentication)
    {
        User user = userService.getCurrentLoginUser(authentication);
        return groupService.getAllRelated(user.getId());
    }

    @GetMapping("/node")
    public List<GroupNode> getAllNode(Authentication authentication)
    {
        User user = userService.getCurrentLoginUser(authentication);
        return groupService.getAllNode(user.getId());
    }

    @GetMapping("/node/related")
    public List<GroupNode> getAllRelatedNode(Authentication authentication)
    {
        User user = userService.getCurrentLoginUser(authentication);
        return groupService.getAllRelatedNode(user.getId());
    }

    @PostMapping
    public Response createGroup(Authentication authentication, @RequestBody Group group)
    {
        User user = userService.getCurrentLoginUser(authentication);
        return this.groupService.createGroup(user, group);
    }

    @PutMapping
    public Response modifyGroup(@RequestBody Group group, Authentication authentication)
    {
        User user = userService.getCurrentLoginUser(authentication);
        return this.groupService.modifyGroup(group, user);
    }

    @DeleteMapping("/{groupID}")
    public Response deleteGroup(@PathVariable int groupID, Authentication authentication)
    {
        User user = userService.getCurrentLoginUser(authentication);
        return this.groupService.deleteGroup(groupID, user);
    }
}
