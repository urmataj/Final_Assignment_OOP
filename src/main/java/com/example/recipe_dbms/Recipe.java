package com.example.recipe_dbms;


import javafx.scene.control.Button;

public class Recipe {

    private String title;
    private String description;
    private Double time;
    private String ingNames;
    private String dishType;
    private String isVeg;
    private String region;
    private String creatorName;

    public Recipe() {}

    public Recipe(String title, String description, Double time, String ingNames, String dishType, String isVeg, String region, String creatorName) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.ingNames = ingNames;
        this.dishType = dishType;
        this.isVeg = isVeg;
        this.region = region;
        this.creatorName = creatorName;
    }

    public Recipe(String title, String description, Double time, String dishType, String isVeg, String region) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.dishType = dishType;
        this.isVeg = isVeg;
        this.region = region;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTime() {return time;}

    public void setTime(Double time) {this.time = time;}

    public String getDishType() {return dishType;}

    public void setDishType(String dishType) {this.dishType = dishType;}

    public String getIsVeg() {return isVeg;}

    public void setIsVeg(String isVeg) {this.isVeg = isVeg;}

    public String getRegion() {return region;}

    public void setRegion(String region) {this.region = region;}

    public String getCreatorName() {return creatorName;}

    public void setCreatorName(String creatorName) {this.creatorName = creatorName;}

    public String getIngNames() {return ingNames;}

    public void setIngNames(String ingNames) {this.ingNames = ingNames;}

}
