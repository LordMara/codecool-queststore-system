package com.codecool.wot.model;

import com.codecool.wot.dao.QuestCategoryDAO;

public class Quest {
    private static Integer lastId = 1;
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private QuestCategory questCategory;

    public Quest(String name, String description, Double price, Integer questCategoryId) {
        this.id = ++lastId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.questCategory = QuestCategoryDAO.getInstance().getQuestCategory(questCategoryId);
    }

    public Quest(Integer id, String name, String description, Double price, Integer questCategoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.questCategory = QuestCategoryDAO.getInstance().getQuestCategory(questCategoryId);

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

    public QuestCategory getQuestCategory() {
        return questCategory;
    }

    public void setQuestCategory() {
        this.questCategory = null;
    }

    public void setQuestCategory(QuestCategory questCategory) {
        this.questCategory = questCategory;
    }
}
