package com.codecool.wot.model;

import com.codecool.wot.dao.ClassDAO;

public class Mentor extends Account{

    private SchoolClass schoolClass;

    public Mentor(String name, String surname, String email, String login, String password) {
        super(name, surname, email, login, password);
    }

    public Mentor(String name, String surname, String email, String login, String password, Integer ID, Integer classId) {
        super(name, surname, email, login, password, ID);
        this.schoolClass = ClassDAO.getInstance().getClass(classId);
        ClassDAO.getInstance().addPersonToMemory(this.schoolClass, this);
    }

    @Override
    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public void setSchoolClass() {
        this.schoolClass = null;
    }
}
