package com.vincle.appitems.dto;

import java.util.Set;

public class ItemEditDto {
    private Integer id;
    private Boolean cooling;
    private String name;
    private Set<ItemEditPackagingDto> packagings;
    private Set<ItemEditCapacityDto> capacitys;
    private String client_id;

    public ItemEditDto() {
    }

    public ItemEditDto( Integer id, Boolean cooling, String name,
                       Set<ItemEditPackagingDto> packagings, Set<ItemEditCapacityDto> capacitys,
                       String client_id) {
        this.id = id;
        this.cooling = cooling;
        this.name = name;
        this.packagings = packagings;
        this.capacitys = capacitys;
        this.client_id = client_id;
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

    public Set<ItemEditPackagingDto> getPackagings() {
        return packagings;
    }

    public void setPackagings(Set<ItemEditPackagingDto> packagings) {
        this.packagings = packagings;
    }

    public Set<ItemEditCapacityDto> getCapacitys() {
        return capacitys;
    }

    public void setCapacitys(Set<ItemEditCapacityDto> capacitys) {
        this.capacitys = capacitys;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  "Cooling: " + getCooling()
                + ", Name: " + getName()
                + ", Packaging: " + getPackagings()
                + ", Capacity: " + getCapacitys()
                + ", Client: " + getClient_id();
    }


}
