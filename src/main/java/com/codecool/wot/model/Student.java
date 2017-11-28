package com.codecool.wot.model;

import com.codecool.wot.dao.ClassDAO;
import com.codecool.wot.dao.WalletDAO;
import com.codecool.wot.interfaces.Codecooler;

public class Student extends Account implements Codecooler {
     private SchoolClass schoolClass;

    public Student(String name, String surname, String email, String login, String password) {
        super(name, surname, email, login, password);
    }

    public Student( String name, String surname, String email, String login, String password,Integer ID, Integer classId) {

        super(name, surname, email, login, password, ID);
        this.schoolClass = ClassDAO.getInstance().getClass(classId);
        this.schoolClass.assignPerson(this);
    }

    @Override
    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    @Override
    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    @Override
    public void setSchoolClass() {
        this.schoolClass = null;
    }
}
