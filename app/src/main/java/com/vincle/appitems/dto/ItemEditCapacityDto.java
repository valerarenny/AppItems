package com.vincle.appitems.dto;

public class ItemEditCapacityDto {
    private Long oldId;
    private Long newId;

    public ItemEditCapacityDto() {
    }

    public ItemEditCapacityDto(Long oldId, Long newId) {
        this.oldId = oldId;
        this.newId = newId;
    }

    public Long getOldId() {
        return oldId;
    }

    public void setOldId(Long oldId) {
        this.oldId = oldId;
    }

    public Long getNewId() {
        return newId;
    }

    public void setNewId(Long newId) {
        this.newId = newId;
    }
}
