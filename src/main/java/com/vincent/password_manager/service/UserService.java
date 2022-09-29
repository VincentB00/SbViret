package com.vincent.password_manager.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.vincent.password_manager.bean.Group;
import com.vincent.password_manager.bean.User;
import com.vincent.password_manager.bean.UserAuthority;
import com.vincent.password_manager.dao.UserAuthorityDao;
import com.vincent.password_manager.dao.UserDao;
import com.vincent.password_manager.http.AuthenticationSuccessResponse;
import com.vincent.password_manager.http.HttpResponseThrowers;
import com.vincent.password_manager.http.Response;
import com.vincent.password_manager.security.Sha256PasswordEncoder;
import com.vincent.password_manager.utils.ReplacementUtils;

@Service
public class UserService 
{
    @Autowired
    private UserDao userDao;

    @Autowired
    private Sha256PasswordEncoder sha256PasswordEncoder;

    @Autowired
    private UserAuthorityDao userAuthorityDao;

    @Autowired
    private GroupService groupService;

    /**
     * this method will check if username is meet all requirement
     * username will check for
     * 1. does not exist
     * 2. ...
     * @param username username to be check
     * @return true if meet all requirement else false
     */
    public boolean isValidUsername(String username)
    {
        return !(isUsernameExist(username) > 0);
    }

    /**
     * this method will check if username already exist in database or not
     * @param username username to be check
     * @return return true if exist else false
     */
    public int isUsernameExist(String username)
    {   
        List<User> users = userDao.findAllByUsername(username);

        users = users.parallelStream().filter((user) -> user.getUsername().equals(username)).collect(Collectors.toList());

        int id = 0;

        if(!users.isEmpty())
            id = users.get(0).getId();
        
        // if(!(users != null && users.parallelStream().anyMatch(user -> user.getUsername().equals(username))))
        //     return 0;

        return id;
    }

    public User get(int id)
    {
        Optional<User> optional = this.userDao.findById(id);

        if(!optional.isPresent())
            HttpResponseThrowers.throwBadRequest("Invalid or missing User ID");

        return optional.get();
    }
    
    public List<User> getAll()
    {
        return userDao.findAll();
    }

    public List<User> getAll(int groupID)
    {
        Group group = this.groupService.getById(groupID);
        List<User> users = new ArrayList<>();

        group.getGroup_users().forEach((g) -> {
            users.add(this.get(g.getUser_id()));
        });

        return users;
    }

    public User getMaskUser(int userID)
    {
        User user = this.get(userID);
        return this.maskUser(user);
    }

    public User maskUser(User user)
    {
        user.setPassword(null);
        user.setPublicKey(null);
        user.setUserRoles(null);
        user.setAccountNonExpired(false);
        user.setAccountNonLocked(false);
        user.setCredentialsNonExpired(false);
        user.setEnabled(false);

        return user;
    }

    public List<User> getAllMaskUser()
    {
        List<User> list =  getAll();

        list.parallelStream().forEach((u) -> {
            this.maskUser(u);
        });

        return list;
    }

    public List<User> getAllMaskUser(int groupID)
    {
        List<User> list =  getAll(groupID);

        list.parallelStream().forEach((u) -> {
            this.maskUser(u);
        });

        return list;
    }

    public User getUser(int id)
    {
        return userDao.findById(id).get();
    }

    public void registerUser(User user)
    {
        user.setPassword(sha256PasswordEncoder.encode(user.getPassword()));
        user.setPublicKey(sha256PasswordEncoder.encode(user.getUsername()));
        
        UserAuthority authority = userAuthorityDao.findByAuthority("NORMAL");

        List<UserAuthority> userAuthorities = new ArrayList<>();

        userAuthorities.add(authority);
        
        user.setUserRoles(userAuthorities);

        userDao.save(user);
    }

    public boolean modifyUser(User originUser, User targetUser)
    {
        int originId = originUser.getId();

        String oldPassword = originUser.getPassword();
        String newPassword = targetUser.getPassword();

        targetUser.setPassword(null);

        User dbUser = userDao.findById(originId).get();

        Boolean success = ReplacementUtils.replaceValue(dbUser, targetUser);

        if(success)
        {
            if(newPassword != null && !newPassword.trim().equals(""))
                dbUser.setPassword(sha256PasswordEncoder.encode(newPassword));

            if(newPassword.equals(oldPassword))
                dbUser.setPassword(oldPassword);

            userDao.save(dbUser);
        }

        return success;
    }

    public Response isLogin(Authentication authentication) 
    {
		if (authentication != null) {
			Response response = new AuthenticationSuccessResponse(true, 200, "Logged In!", userDao.findByUsername(authentication.getName()));
			return response;
		} 
        else 
        {
			return new Response(false);
		}
	}

    public User getCurrentLoginUser(Authentication authentication)
    {
        if (authentication != null) {
			return userDao.findByUsername(authentication.getName());
		} 
        else 
        {
			return null;
		}
    }
}
