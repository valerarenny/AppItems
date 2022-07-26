package com.vincle.appitems.model;

import java.io.Serializable;

public class Capacity implements Serializable {
    private Long  id;
    private String amount;
    private String units;
    private String description;

    public Capacity() {
    }

    public Capacity(Long id, String amount, String units, String description) {
        this.id = id;
        this.amount = amount;
        this.units = units;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Id: " + getId()
                + ", Amount: " + getAmount()
                + ", Unit: " + getUnits()
                + ", Description: " + getDescription();
    }
}
