package com.vincent.password_manager.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.vincent.password_manager.http.Response;
import com.vincent.password_manager.utils.ViewPackers;

@Component
@Order(11)
public class ResponseStatusFilter implements Filter
{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException 
    {
        ContentCachingResponseWrapper responseCacheWrapperObject = new ContentCachingResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request, responseCacheWrapperObject);
        byte[] responseArray = responseCacheWrapperObject.getContentAsByteArray();
        String responseStr = new String(responseArray, responseCacheWrapperObject.getCharacterEncoding());

        try
        {
            Response r = ViewPackers.gson.fromJson(responseStr, Response.class);
            if(r.getStatus() > 0)
                responseCacheWrapperObject.setStatus(r.getStatus());
        }
        catch(Exception ex){}

        responseCacheWrapperObject.copyBodyToResponse();
    }
    
}
