package com.vincle.appitems.model;

import java.io.Serializable;
import java.util.Set;

public class Item implements Serializable {
    private Integer id;
    private Boolean cooling;
    private String name;
    private String status;
    private Set<Packaging> packagings;
    private Set<Capacity> capacitys;
    private String created;
    private String updated;
    private String deleted;
    private String client_id;

    public Item() {
    }

    public Item(Integer id, Boolean cooling, String name, String status, Set<Packaging> packagings,
                Set<Capacity> capacitys, String created, String updated, String deleted,
                String client_id) {
        this.id = id;
        this.cooling = cooling;
        this.name = name;
        this.status = status;
        this.packagings = packagings;
        this.capacitys = capacitys;
        this.created = created;
        this.updated = updated;
        this.deleted = deleted;
        this.client_id = client_id;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getCooling() {
        return cooling;
    }

    public void setCooling(Boolean cooling) {
        this.cooling = cooling;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Packaging> getPackagings() {
        return packagings;
    }

    public void setPackagings(Set<Packaging> packagings) {
        this.packagings = packagings;
    }

    public Set<Capacity> getCapacitys() {
        return capacitys;
    }

    public void setCapacitys(Set<Capacity> capacitys) {
        this.capacitys = capacitys;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    @Override
    public String toString() {
        return "Id: "+ getId()
                + ", Cooling: " + getCooling()
                + ", Name: " + getName()
                + ", Status: " + getStatus()
                + ", Packaging: " + getPackagings()
                + ", Capacity: " + getCapacitys()
                + ", Created: " + getCreated()
                + ", Updated: " + getUpdated()
                + ", Deleted: " + getDeleted()
                + ", Client: " + getClient_id();
    }
}
