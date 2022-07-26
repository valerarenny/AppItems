package com.vincle.appitems.dto;

import java.util.Set;

public class ItemCreateDto {
    private Boolean cooling;
    private String name;
    private String client_id;
    private Set<Long> packaging;
    private Set<Long> capacity;

    public ItemCreateDto() {
    }

    public ItemCreateDto(Boolean cooling, String name,
                         String client_id, Set<Long> packaging, Set<Long> capacity) {
        this.cooling = cooling;
        this.name = name;
        this.client_id = client_id;
        this.packaging = packaging;
        this.capacity = capacity;
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

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public Set<Long> getPackaging() {
        return packaging;
    }

    public void setPackaging(Set<Long> packaging) {
        this.packaging = packaging;
    }

    public Set<Long> getCapacity() {
        return capacity;
    }

    public void setCapacity(Set<Long> capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Cooling: " + getCooling()
                + ", Name: " + getName()
                + ", Packaging: " + getPackaging()
                + ", Capacity: " + getCapacity()
                + ", Client: " + getClient_id();
    }
}
