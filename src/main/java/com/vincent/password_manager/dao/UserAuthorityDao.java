package com.vincent.password_manager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vincent.password_manager.bean.UserAuthority;

@Repository
public interface UserAuthorityDao extends JpaRepository<UserAuthority, Integer> 
{
    public UserAuthority findByAuthority(String authority);
}
