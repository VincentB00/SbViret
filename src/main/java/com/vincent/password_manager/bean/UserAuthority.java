package com.vincent.password_manager.bean;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "user_authority")
public class UserAuthority implements GrantedAuthority
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String authority;
    

    public UserAuthority() {
    }

    public UserAuthority(int id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public UserAuthority id(int id) {
        setId(id);
        return this;
    }

    public UserAuthority authority(String authority) {
        setAuthority(authority);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserAuthority)) {
            return false;
        }
        UserAuthority userAuthority = (UserAuthority) o;
        return id == userAuthority.id && Objects.equals(authority, userAuthority.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authority);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", authority='" + getAuthority() + "'" +
            "}";
    }

    
    
}
