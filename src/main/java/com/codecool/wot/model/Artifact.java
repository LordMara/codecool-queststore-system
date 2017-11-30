package com.codecool.wot.model;

public class Artifact {
    private static Integer lastId = 1;
    private Integer id;
    private String name;
    private String description;
    private Double price;

    public Artifact(String name, String description, Double price) {
        this.id = ++lastId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Artifact(Integer id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;

        if(lastId < id) {
            lastId = id;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
