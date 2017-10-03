package com.codecool.wot.model;

import com.codecool.wot.dao.QuestDAO;

public class Quest {

    private String name;
    private String description;
    private String ID;
    private Float price;

    public Quest(String name, String description, Float price, String ID) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.ID = ID;
    }

}
