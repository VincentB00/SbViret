package com.vincent.password_manager.bean;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "group_user")
public class Group_user 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int group_id;
    
    @Column
    private int user_id;
    
    @Column
    private String authority = "NORMAL";

    public Group_user() {
    }

    public Group_user(int id, int group_id, int user_id, String authority) {
        this.id = id;
        this.group_id = group_id;
        this.user_id = user_id;
        this.authority = authority;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup_id() {
        return this.group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getUser_id() {
        return this.user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Group_user id(int id) {
        setId(id);
        return this;
    }

    public Group_user group_id(int group_id) {
        setGroup_id(group_id);
        return this;
    }

    public Group_user user_id(int user_id) {
        setUser_id(user_id);
        return this;
    }

    public Group_user authority(String authority) {
        setAuthority(authority);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Group_user)) {
            return false;
        }
        Group_user group_user = (Group_user) o;
        return id == group_user.id && group_id == group_user.group_id && user_id == group_user.user_id && Objects.equals(authority, group_user.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, group_id, user_id, authority);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", group_id='" + getGroup_id() + "'" +
            ", user_id='" + getUser_id() + "'" +
            ", authority='" + getAuthority() + "'" +
            "}";
    }

}
