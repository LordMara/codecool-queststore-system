package com.codecool.wot.model;

public class QuestCategory {
    private static Integer lastId = 1;
    private Integer id;
    private String name;

    public QuestCategory(String name) {
        this.id = ++lastId;
        this.name = name;
    }

    public QuestCategory(Integer id, String name) {
        this.id = id;
        this.name = name;

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
}
