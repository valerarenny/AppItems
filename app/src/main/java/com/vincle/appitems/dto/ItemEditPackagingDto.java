package com.vincle.appitems.dto;

public class ItemEditPackagingDto {
    private Long oldId;
    private Long newId;

    public ItemEditPackagingDto() {
    }

    public ItemEditPackagingDto(Long oldId, Long newId) {
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
