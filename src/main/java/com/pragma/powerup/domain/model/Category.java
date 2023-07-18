package com.pragma.powerup.domain.model;

import lombok.Builder;

public class Category extends ObjectModel{
    private String description;

    public Category(){

    }

    public Category(Long id, String name, String description) {
        super(id, name);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
