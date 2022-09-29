package com.vincent.password_manager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vincent.password_manager.bean.Item;
import com.vincent.password_manager.bean.User;
import com.vincent.password_manager.dao.ItemDao;
import com.vincent.password_manager.http.HttpResponseThrowers;
import com.vincent.password_manager.http.Response;
import com.vincent.password_manager.utils.ReplacementUtils;

@Service
public class ItemService 
{
    @Autowired
    private ItemDao itemDao;

    @Autowired
    private GroupService groupService;

    public Item get(int id)
    {
        Optional<Item> origin = this.itemDao.findById(id);

        if(!origin.isPresent())
            HttpResponseThrowers.throwBadRequest("invalid or missing itemID");

        return origin.get();
    }

    public List<Item> getAll()
    {
        return this.itemDao.findAll();
    }

    public List<Item> getAll(int groupID, User user)
    {
        this.groupService.checkIsPermited(groupID, user, null);
        
        return this.itemDao.getAllByGroupId(groupID);
    }

    public Item createItem(Item item, User user)
    {
        List<String> list = new ArrayList<>();
        list.add("ADMIN");
        list.add("OWNER");

        this.groupService.checkIsPermited(item.getGroup_id(), user, list);

        item.setPublicKey(user.getPublicKey());

        item = this.itemDao.save(item);
        
        return item;
    }
    
    public Response modifyItem(Item item, User user)
    {
        List<String> list = new ArrayList<>();
        list.add("ADMIN");
        list.add("OWNER");
        
        this.groupService.checkIsPermited(item.getGroup_id(), user, list);

        Item origin = this.get(item.getId());

        ReplacementUtils.replaceValue(origin, item);

        this.itemDao.save(origin);

        return new Response(true, 200, "Item have been modified");
    }

    public Response deleteItem(int itemID, User user)
    {
        Item item = this.get(itemID);
        List<String> list = new ArrayList<>();
        list.add("ADMIN");
        list.add("OWNER");
        
        this.groupService.checkIsPermited(item.getGroup_id(), user, list);

        this.itemDao.deleteById(itemID);
        return new Response(true, "item have been removed");
    }
}
