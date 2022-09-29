package com.vincent.password_manager.bean;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;
    
    @Column
    private boolean accountNonExpired = true;
    
    @Column
    private boolean accountNonLocked = true;
    
    @Column
    private boolean credentialsNonExpired = true;
    
    @Column
    private boolean enabled = true;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(name = "privilege",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name ="user_authority_id", referencedColumnName = "id")
    )
    private List<UserAuthority> userRoles;

    @Column
    private String publicKey;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    public boolean getAccountNonExpired() {
        return this.accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public boolean getAccountNonLocked() {
        return this.accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public boolean getCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<UserAuthority> getUserRoles() {
        return this.userRoles;
    }

    public void setUserRoles(List<UserAuthority> userRoles) {
        this.userRoles = userRoles;
    }

    public User id(int id) {
        setId(id);
        return this;
    }

    public User username(String username) {
        setUsername(username);
        return this;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }

    public User accountNonExpired(boolean accountNonExpired) {
        setAccountNonExpired(accountNonExpired);
        return this;
    }

    public User accountNonLocked(boolean accountNonLocked) {
        setAccountNonLocked(accountNonLocked);
        return this;
    }

    public User credentialsNonExpired(boolean credentialsNonExpired) {
        setCredentialsNonExpired(credentialsNonExpired);
        return this;
    }

    public User enabled(boolean enabled) {
        setEnabled(enabled);
        return this;
    }

    public User userRoles(List<UserAuthority> userRoles) {
        setUserRoles(userRoles);
        return this;
    }


    
    public String getPublicKey() {
        return this.publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public User publicKey(String publicKey) {
        setPublicKey(publicKey);
        return this;
    }


    public User() {
    }

    public User(int id, String username, String password, String email, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled, List<UserAuthority> userRoles, String publicKey) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.userRoles = userRoles;
        this.publicKey = publicKey;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User email(String email) {
        setEmail(email);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && accountNonExpired == user.accountNonExpired && accountNonLocked == user.accountNonLocked && credentialsNonExpired == user.credentialsNonExpired && enabled == user.enabled && Objects.equals(userRoles, user.userRoles) && Objects.equals(publicKey, user.publicKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled, userRoles, publicKey);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", email='" + getEmail() + "'" +
            ", accountNonExpired='" + isAccountNonExpired() + "'" +
            ", accountNonLocked='" + isAccountNonLocked() + "'" +
            ", credentialsNonExpired='" + isCredentialsNonExpired() + "'" +
            ", enabled='" + isEnabled() + "'" +
            ", userRoles='" + getUserRoles() + "'" +
            ", publicKey='" + getPublicKey() + "'" +
            "}";
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userRoles;
    }
}
