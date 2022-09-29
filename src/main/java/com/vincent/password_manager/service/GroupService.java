package com.vincent.password_manager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vincent.password_manager.bean.Group;
import com.vincent.password_manager.bean.GroupNode;
import com.vincent.password_manager.bean.Group_user;
import com.vincent.password_manager.bean.Item;
import com.vincent.password_manager.bean.User;
import com.vincent.password_manager.dao.GroupDao;
import com.vincent.password_manager.dao.ItemDao;
import com.vincent.password_manager.http.HttpResponseThrowers;
import com.vincent.password_manager.http.Response;
import com.vincent.password_manager.utils.ReplacementUtils;

@Service
public class GroupService 
{
    @Autowired
    private GroupDao groupDao;

    @Autowired
    private ItemDao itemDao;
    
    public List<Group> getAll(int userID)
    {
        return this.groupDao.getAllByUser_id(userID);
    }

    public List<Group> getAllRelated(int userID)
    {
        List<Group> groups = this.groupDao.getAllReatedGroup(userID);

        return groups;
    }

    public List<GroupNode> getAllNode(int userID)
    {
        // List<String> roles = new ArrayList<>();
        // roles.add("OWNER");

        List<GroupNode> groupNodes = new ArrayList<>();

        List<Group> groups = this.getAll(userID);

        // groups = groups.stream().filter((g) -> this.isPermited(g.getId(), userID, roles)).collect(Collectors.toList());

        for (Group group : groups) 
        {
            String path = group.getPath();

            String[] splitPath = path.split("/");

            path = splitPath[0];

            GroupNode currentGroupNode = null;

            currentGroupNode = findNode(groupNodes, currentGroupNode, path);

            if(splitPath.length > 1)
            {
                for(int count = 1; count < splitPath.length; count++)
                {
                    path += "/" + splitPath[count];
                    currentGroupNode = findNode(currentGroupNode.getChildren(), currentGroupNode, path);
                }
            }

            currentGroupNode.setGroupID(group.getId());
            currentGroupNode.setName(group.getName());
            currentGroupNode.setPath(group.getPath());
        }

        return groupNodes;
    }

    public List<GroupNode> getAllRelatedNode(int userID)
    {
        // List<String> roles = new ArrayList<>();
        // roles.add("NORMAL");
        // roles.add("ADMIN");

        List<GroupNode> groupNodes = new ArrayList<>();

        List<Group> groups = this.getAllRelated(userID);

        // groups = groups.stream().filter((g) -> this.isPermited(g.getId(), userID, roles)).collect(Collectors.toList());

        for (Group group : groups) 
        {
            String path = group.getPath();

            String[] splitPath = path.split("/");

            path = splitPath[0];

            GroupNode currentGroupNode = null;

            currentGroupNode = findNode(groupNodes, currentGroupNode, path);

            if(splitPath.length > 1)
            {
                for(int count = 1; count < splitPath.length; count++)
                {
                    path += "/" + splitPath[count];
                    currentGroupNode = findNode(currentGroupNode.getChildren(), currentGroupNode, path);
                }
            }

            currentGroupNode.setGroupID(group.getId());
            currentGroupNode.setName(group.getName());
            currentGroupNode.setPath(group.getPath());
        }

        return groupNodes;
    }

    private GroupNode findNode(List<GroupNode> groupNodes, GroupNode currentGroupNode, String path)
    {
        currentGroupNode = null;
        for (GroupNode node : groupNodes)  
        {
            String abPath = node.getPath();
            if(abPath.equals(path))
            {
                currentGroupNode = node;
                break;
            }
        }

        if(currentGroupNode == null)
        {
            GroupNode groupNode = new GroupNode();
            groupNode.setPath(path);
            currentGroupNode = groupNode;
            groupNodes.add(groupNode);
        }

        return currentGroupNode;
    }

    public Group getById(int id)
    {
        Optional<Group> optional = groupDao.findById(id);
        if(!optional.isPresent())
            HttpResponseThrowers.throwBadRequest("Missing or invalid original group id");

        return optional.get();
    }

    public Response createGroup(User user, Group group)
    {
        group.setGroup_users(null);
        group.setId(0);

        group = groupDao.save(group);

        List<Group_user> list = new ArrayList<>();
        Group_user group_user = new Group_user();
        group_user.setAuthority("OWNER");
        group_user.setGroup_id(group.getId());
        group_user.setUser_id(user.getId());
        list.add(group_user);

        group.setGroup_users(list);
        group.setUser_id(user.getId());

        groupDao.save(group);

        return new Response(true, 201, "new group have been created");
    }

    public Response modifyGroup(Group target, User user)
    {
        Group origin = this.getById(target.getId());

        List<String> list = new ArrayList<>();
        list.add("ADMIN");
        list.add("OWNER");
        
        this.checkIsPermited(origin.getId(), user, list);

        ReplacementUtils.replaceValue(origin, target);

        this.groupDao.save(origin);

        return new Response(true, 200, "group have been modified");
    }

    public Response deleteGroup(int groupID, User user)
    {
        List<String> list = new ArrayList<>();
        // list.add("ADMIN");
        list.add("OWNER");
        
        this.checkIsPermited(groupID, user, list);

        Group group = this.getById(groupID);
        String[] split =  group.getPath().split("/");
        String pathName = split[split.length - 1];
        List<Group> groups = this.getAll(user.getId());

        groups.parallelStream().forEach((g) -> {
            if(g.getPath().indexOf(group.getPath()) == 0)
            {
                if(g.getPath().contains(pathName + "/"))
                    g.setPath(g.getPath().replace(pathName + "/", ""));
                else
                    g.setPath(g.getPath().replace(pathName, ""));
            }
        });

        this.deleteAllItemFromGroup(groupID);
        this.groupDao.deleteById(group.getId());

        return new Response(true, 200, "Group have been deleted");
    }

    private void deleteAllItemFromGroup(int groupID)
    {
        List<Item> items = this.itemDao.getAllByGroupId(groupID);

        items.parallelStream().forEach((i) -> {this.itemDao.deleteById(i.getId());});
    }

    public boolean isPermited(int groupID, User user)
    {
        Group group = this.getById(groupID);

        if(group.getGroup_users().parallelStream().anyMatch((groupUser) -> groupUser.getUser_id() == user.getId()))
            return true;
        else
            return false;
    }

    public boolean isPermited(int groupID, User user, List<String> roles)
    {
        Group group = this.getById(groupID);

        if(group.getGroup_users().parallelStream().anyMatch((groupUser) -> (groupUser.getUser_id() == user.getId()) && roles.parallelStream().anyMatch((role) -> role.equals(groupUser.getAuthority()))))
            return true;
        else
            return false;
    }

    public boolean isPermited(int groupID, int userID, List<String> roles)
    {
        Group group = this.getById(groupID);

        if(group.getGroup_users().parallelStream().anyMatch((groupUser) -> (groupUser.getUser_id() == userID) && roles.parallelStream().anyMatch((role) -> role.equals(groupUser.getAuthority()))))
            return true;
        else
            return false;
    }

    public void checkIsPermited(int groupID, User user, List<String> roles)
    {
        if(roles == null)
        {
            if(!this.isPermited(groupID, user))
                HttpResponseThrowers.throwForbidden("Forbidden resources for this user");
        }
        else
        {
            if(!this.isPermited(groupID, user, roles))
                HttpResponseThrowers.throwForbidden("Forbidden resources for this user");
        }
    }

    public void checkIsPermited(int groupID, User user)
    {
        if(!this.isPermited(groupID, user))
            HttpResponseThrowers.throwForbidden("Forbidden resources for this user");
    }
}
