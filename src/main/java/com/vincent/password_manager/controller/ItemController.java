package com.vincent.password_manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vincent.password_manager.bean.Item;
import com.vincent.password_manager.bean.User;
import com.vincent.password_manager.http.Response;
import com.vincent.password_manager.service.ItemService;
import com.vincent.password_manager.service.UserService;
import com.vincent.password_manager.utils.ConstantType;

@RestController
@RequestMapping("/items")
@PreAuthorize(ConstantType.HAS_ANY_ALL_AUTHORITY)
public class ItemController 
{
    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/{groupID}")
    public List<Item> getAll(@PathVariable int groupID, Authentication authentication)
    {
        User user =  this.userService.getCurrentLoginUser(authentication);
        return this.itemService.getAll(groupID, user);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Item createNewItem(@RequestBody Item item, Authentication authentication)
    {
        User user =  this.userService.getCurrentLoginUser(authentication);
        return this.itemService.createItem(item, user);
    }

    @PutMapping
    public Response modifyItem(@RequestBody Item item, Authentication authentication)
    {
        User user =  this.userService.getCurrentLoginUser(authentication);
        return this.itemService.modifyItem(item, user);
    }

    @DeleteMapping("{itemID}")
    public Response deleteItem(@PathVariable int itemID, Authentication authentication)
    {
        User user =  this.userService.getCurrentLoginUser(authentication);
        return this.itemService.deleteItem(itemID, user);
    }
}
