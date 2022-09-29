package com.vincent.password_manager.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GroupNode 
{
    private String name;
    private String path;
    private int groupID;
    private List<GroupNode> children;


    public GroupNode() 
    {
        children = new ArrayList<>();
    }

    public GroupNode(String name, String path, int groupID, List<GroupNode> children) {
        this.name = name;
        this.path = path;
        this.groupID = groupID;
        this.children = children;
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

    public int getGroupID() {
        return this.groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public List<GroupNode> getChildren() {
        return this.children;
    }

    public void setChildren(List<GroupNode> children) {
        this.children = children;
    }

    public GroupNode name(String name) {
        setName(name);
        return this;
    }

    public GroupNode path(String path) {
        setPath(path);
        return this;
    }

    public GroupNode groupID(int groupID) {
        setGroupID(groupID);
        return this;
    }

    public GroupNode children(List<GroupNode> children) {
        setChildren(children);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GroupNode)) {
            return false;
        }
        GroupNode groupNode = (GroupNode) o;
        return Objects.equals(name, groupNode.name) && Objects.equals(path, groupNode.path) && groupID == groupNode.groupID && Objects.equals(children, groupNode.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, path, groupID, children);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", path='" + getPath() + "'" +
            ", groupID='" + getGroupID() + "'" +
            ", children='" + getChildren() + "'" +
            "}";
    }
    
}
