package com.vincent.password_manager.bean;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item_storage")
public class Item_storage 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    private int item_id;
    
    @Column
    private String name;
    
    @Column
    private String value;

    public Item_storage() {
    }

    public Item_storage(int id, int item_id, String name, String value) {
        this.id = id;
        this.item_id = item_id;
        this.name = name;
        this.value = value;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItem_id() {
        return this.item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Item_storage id(int id) {
        setId(id);
        return this;
    }

    public Item_storage item_id(int item_id) {
        setItem_id(item_id);
        return this;
    }

    public Item_storage name(String name) {
        setName(name);
        return this;
    }

    public Item_storage value(String value) {
        setValue(value);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Item_storage)) {
            return false;
        }
        Item_storage item_storage = (Item_storage) o;
        return id == item_storage.id && item_id == item_storage.item_id && Objects.equals(name, item_storage.name) && Objects.equals(value, item_storage.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item_id, name, value);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", item_id='" + getItem_id() + "'" +
            ", name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }
    

}
