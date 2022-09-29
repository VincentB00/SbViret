package com.vincent.password_manager.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vincent.password_manager.bean.Group;

@Repository
public interface GroupDao extends JpaRepository<Group, Integer>
{
    @Query(value = "select * from item_group as ig where ig.user_id = :userID", nativeQuery = true)
    public List<Group> getAllByUser_id(@Param("userID") int user_id);

    @Modifying
    @Transactional
    @Query(value = "call password_manager.getAllRelatedGroup(:userID);", nativeQuery = true)
    public List<Group> getAllReatedGroup(@Param("userID") int user_id);
}
