package com.codecool.wot.model;

import java.util.LinkedList;
import java.util.List;

public class SchoolClass {
    private Integer Id;
    private String name;
    private List<Account> persons;

    public SchoolClass(Integer Id, String name) {
        this.Id = Id;
        this.name = name;
        this.persons = new LinkedList<>();
    }

    public SchoolClass(String name) {
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
        return Id;
    }
}
