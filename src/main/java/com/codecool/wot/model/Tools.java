package com.codecool.wot.model;

import com.codecool.wot.dao.ClassDAO;
import com.codecool.wot.dao.DAOs;
import com.codecool.wot.view.*;

public class Tools {

    private ClassDAO classDAO;
    private View view;

    public Tools(View view, DAOs dao) {
        this.view = view;
        this.classDAO = dao.getcDAO();
    }

    public SchoolClass getClassByName() {

        SchoolClass schoolClass = null;

        while (schoolClass == null) {
            String name = view.getStringInput("Enter class name :");
            schoolClass = classDAO.getBy(name);

            if (schoolClass == null) {
                view.printMessage("NOT FOUND ! ");
            }
        }
        return schoolClass;
    }
}
