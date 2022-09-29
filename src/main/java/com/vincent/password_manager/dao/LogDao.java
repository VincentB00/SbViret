package com.vincent.password_manager.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vincent.password_manager.bean.Log;

@Repository
public interface LogDao extends JpaRepository<Log, Integer>  
{
    @Modifying
    @Transactional
    @Query(value = "call password_manager.remove_top_log();", nativeQuery = true)
    public void deleteTopLog();

    @Modifying
    @Transactional
    @Query(value = "call password_manager.remove_extra_log((SELECT * FROM password_manager.max_log));", nativeQuery = true)
    public void deleteExtraLog();
}
