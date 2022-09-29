package com.vincent.password_manager.bean;

import java.util.ArrayList;
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

@Entity
@Table(name = "item_group")
public class Group 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String path;

    @Column
    private int user_id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private List<Group_user> group_users;

    public Group()
    {
        group_users = new ArrayList<>();
    }


    public Group(int id, String name, String path, int user_id, List<Group_user> group_users) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.user_id = user_id;
        this.group_users = group_users;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getUser_id() {
        return this.user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public List<Group_user> getGroup_users() {
        return this.group_users;
    }

    public void setGroup_users(List<Group_user> group_users) {
        this.group_users = group_users;
    }

    public Group id(int id) {
        setId(id);
        return this;
    }

    public Group name(String name) {
        setName(name);
        return this;
    }

    public Group path(String path) {
        setPath(path);
        return this;
    }

    public Group user_id(int user_id) {
        setUser_id(user_id);
        return this;
    }

    public Group group_users(List<Group_user> group_users) {
        setGroup_users(group_users);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Group)) {
            return false;
        }
        Group group = (Group) o;
        return id == group.id && Objects.equals(name, group.name) && Objects.equals(path, group.path) && user_id == group.user_id && Objects.equals(group_users, group.group_users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, path, user_id, group_users);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", path='" + getPath() + "'" +
            ", user_id='" + getUser_id() + "'" +
            ", group_users='" + getGroup_users() + "'" +
            "}";
    }
}
