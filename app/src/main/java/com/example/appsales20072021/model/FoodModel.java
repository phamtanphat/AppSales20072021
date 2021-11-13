package com.example.appsales20072021.model;

import java.util.List;

public class FoodModel {
    public String foodId;
    public String foodName;
    public List<ImageModel> images;
    public String description;
    public int price;
    public String cateId;
    public String cateName;
    public String createdAt;
    public String updatedAt;

    @Override
    public String toString() {
        return "FoodModel{" +
                "foodId='" + foodId + '\'' +
                ", foodName='" + foodName + '\'' +
                ", images=" + images +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", cateId='" + cateId + '\'' +
                ", cateName='" + cateName + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
