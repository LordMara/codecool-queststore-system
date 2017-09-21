package com.codecool.wot.model;

import com.codecool.wot.dao.QuestDAO;

public class Quest {

    private String name;
    private String description;


    public Quest(String name, String description, QuestDAO questDAO) {
        this.name = name;
        this.description = description;
        questDAO.add(this);
    }
}
