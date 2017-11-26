package com.codecool.wot.model;

import java.util.LinkedList;
import java.util.List;

public class SchoolClass {
    private static Integer lastId = 1;
    private Integer id;
    private String name;
    private List<Account> persons;

    public SchoolClass(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.persons = new LinkedList<>();

        if(lastId < id) {
            lastId = id;
        }
    }

    public SchoolClass(String name) {
        this.id = ++lastId;
        this.name = name;
        this.persons = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getPersons() {
        return this.persons;
    }

    public void removePerson(Account person) {
        this.persons.remove(person);
    }

    public void assignPerson(Account person) {
        this.persons.add(person);
    }

    public Integer getId() {
        return id;
    }
}
