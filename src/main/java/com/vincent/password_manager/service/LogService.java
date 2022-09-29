package com.vincent.password_manager.service;

import java.util.ArrayList;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vincent.password_manager.bean.Log;
import com.vincent.password_manager.dao.LogDao;
import com.vincent.password_manager.utils.ViewPackers;

@Service
public class LogService 
{
    @Autowired
    private LogDao logDao;

    public LogService()
    {
        
    }

    public void saveLog(HttpServletRequest request, HttpServletResponse response, String logStr)
    {
        Log log = new Log();
        log.setRequest(ViewPackers.customToJson(request));
        log.setResponse(ViewPackers.customToJson(response));
        log.setLog(logStr);

        log.setRequester_ip(request.getRemoteAddr());
        log.setRequest_path(request.getMethod() + " " + request.getRequestURI());

        logDao.save(log);
        logDao.deleteExtraLog();
    }
}
