package com.vincent.password_manager.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
@Table(name = "item")
public class Item 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;
    
    @Column
    private String username;
    
    @Column
    private String password;
    
    @Column
    private String authenticatorKey;
    
    @Column
    private String url;
    
    @Column
    private String note;
    
    @Column
    private int group_id;

    @Column
    private String publicKey;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private List<Item_storage> itemStorages;


    
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

    public String getAuthenticatorKey() {
        return this.authenticatorKey;
    }

    public void setAuthenticatorKey(String authenticatorKey) {
        this.authenticatorKey = authenticatorKey;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getGroup_id() {
        return this.group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public List<Item_storage> getItemStorages() {
        return this.itemStorages;
    }

    public void setItemStorages(List<Item_storage> itemStorages) {
        this.itemStorages = itemStorages;
    }

    public Item id(int id) {
        setId(id);
        return this;
    }

    public Item name(String name) {
        setName(name);
        return this;
    }

    public Item username(String username) {
        setUsername(username);
        return this;
    }

    public Item password(String password) {
        setPassword(password);
        return this;
    }

    public Item authenticatorKey(String authenticatorKey) {
        setAuthenticatorKey(authenticatorKey);
        return this;
    }

    public Item url(String url) {
        setUrl(url);
        return this;
    }

    public Item note(String note) {
        setNote(note);
        return this;
    }

    public Item group_id(int group_id) {
        setGroup_id(group_id);
        return this;
    }

    public Item itemStorages(List<Item_storage> itemStorages) {
        setItemStorages(itemStorages);
        return this;
    }

    public Item() {
    }

    public Item(int id, String name, String username, String password, String authenticatorKey, String url, String note, int group_id, String publicKey, List<Item_storage> itemStorages) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.authenticatorKey = authenticatorKey;
        this.url = url;
        this.note = note;
        this.group_id = group_id;
        this.publicKey = publicKey;
        this.itemStorages = itemStorages;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public Item publicKey(String publicKey) {
        setPublicKey(publicKey);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Item)) {
            return false;
        }
        Item item = (Item) o;
        return id == item.id && Objects.equals(name, item.name) && Objects.equals(username, item.username) && Objects.equals(password, item.password) && Objects.equals(authenticatorKey, item.authenticatorKey) && Objects.equals(url, item.url) && Objects.equals(note, item.note) && group_id == item.group_id && Objects.equals(publicKey, item.publicKey) && Objects.equals(itemStorages, item.itemStorages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, password, authenticatorKey, url, note, group_id, publicKey, itemStorages);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", authenticatorKey='" + getAuthenticatorKey() + "'" +
            ", url='" + getUrl() + "'" +
            ", note='" + getNote() + "'" +
            ", group_id='" + getGroup_id() + "'" +
            ", publicKey='" + getPublicKey() + "'" +
            ", itemStorages='" + getItemStorages() + "'" +
            "}";
    }

    
    
}
