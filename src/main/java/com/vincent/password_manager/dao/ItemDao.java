package com.vincent.password_manager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vincent.password_manager.bean.Item;

@Repository
public interface ItemDao extends JpaRepository<Item, Integer> 
{
    @Query(value = "select * from item as i where i.group_id = :groupID", nativeQuery = true)
    public List<Item> getAllByGroupId(@Param("groupID") int groupID);
}
