package com.codecool.wot.model;

public class SchoolClass {

    private String Id;
    private String name;

    public SchoolClass(String Id, String name) {
        this.Id = Id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return Id;
    }

}
