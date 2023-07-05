package com.pragma.powerup.domain.model;

public class Dish extends ObjectModel {
    private String description;
    private double price;
    private String imageUrl;
    private boolean isActive;
    private long categoryId;
    private long restaurantId;

    public Dish(){

    }

    public Dish(Long id, String name, String description, double price, String imageUrl, long categoryId, long restaurantId) {
        super(id, name);
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.isActive = true;
        this.categoryId = categoryId;
        this.restaurantId = restaurantId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
