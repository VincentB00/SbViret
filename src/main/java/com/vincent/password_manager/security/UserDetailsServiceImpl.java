package com.vincent.password_manager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vincent.password_manager.bean.User;
import com.vincent.password_manager.dao.UserDao;
import com.vincent.password_manager.http.HttpResponseThrowers;


@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
    {
        User user = userDao.findByUsername(username);

        if(user == null || !user.isEnabled())
            throw new UsernameNotFoundException(username + " not exist/invalid");

        return user;
    }
}
