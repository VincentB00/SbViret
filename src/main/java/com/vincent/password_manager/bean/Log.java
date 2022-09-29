package com.vincent.password_manager.bean;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "log")
public class Log 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    private String requester_ip;
    
    @Column
    private String request_path;
    
    @Column
    private String request;
    
    @Column
    private String response;

    @Column
    private String log;
    
    @Column
    @CreationTimestamp
    private Date create_time;


    public Log() {
    }

    public Log(int id, String requester_ip, String request_path, String request, String response, String log, Date create_time) {
        this.id = id;
        this.requester_ip = requester_ip;
        this.request_path = request_path;
        this.request = request;
        this.response = response;
        this.log = log;
        this.create_time = create_time;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequester_ip() {
        return this.requester_ip;
    }

    public void setRequester_ip(String requester_ip) {
        this.requester_ip = requester_ip;
    }

    public String getRequest_path() {
        return this.request_path;
    }

    public void setRequest_path(String request_path) {
        this.request_path = request_path;
    }

    public String getRequest() {
        return this.request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return this.response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getLog() {
        return this.log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Date getCreate_time() {
        return this.create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Log id(int id) {
        setId(id);
        return this;
    }

    public Log requester_ip(String requester_ip) {
        setRequester_ip(requester_ip);
        return this;
    }

    public Log request_path(String request_path) {
        setRequest_path(request_path);
        return this;
    }

    public Log request(String request) {
        setRequest(request);
        return this;
    }

    public Log response(String response) {
        setResponse(response);
        return this;
    }

    public Log log(String log) {
        setLog(log);
        return this;
    }

    public Log create_time(Date create_time) {
        setCreate_time(create_time);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Log)) {
            return false;
        }
        Log log = (Log) o;
        return id == log.id && Objects.equals(requester_ip, log.requester_ip) && Objects.equals(request_path, log.request_path) && Objects.equals(request, log.request) && Objects.equals(response, log.response) && Objects.equals(log, log.log) && Objects.equals(create_time, log.create_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requester_ip, request_path, request, response, log, create_time);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", requester_ip='" + getRequester_ip() + "'" +
            ", request_path='" + getRequest_path() + "'" +
            ", request='" + getRequest() + "'" +
            ", response='" + getResponse() + "'" +
            ", log='" + getLog() + "'" +
            ", create_time='" + getCreate_time() + "'" +
            "}";
    }
    

}
