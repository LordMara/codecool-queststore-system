package com.codecool.wot.dao;

import com.codecool.wot.model.SchoolClass;

import java.util.ArrayList;

public class ClassDAO extends AbstractDAO<SchoolClass> {

    public void loadClasses(){

    }

    public SchoolClass getByName(String name) throws NullPointerException {

        for (SchoolClass schoolClass : objectsList){
            if (schoolClass.getName().equals(name)){
                return schoolClass;
            }
        }
        throw new NullPointerException();
    }

}
