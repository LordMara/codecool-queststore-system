package com.codecool.wot.model;

import com.codecool.wot.dao.QuestDAO;

public class Quest {

    private String name;
    private String description;
    private Integer ID;
    private Float price;
    private static Integer lastID = 1;

    public Quest(String name, String description, Float price) {
        this.name = name;
        this.description = description;
        generateID();
        this.price = price;
    }

    private void generateID() {
        this.ID = Quest.lastID;
        Quest.lastID++;

    }
}
