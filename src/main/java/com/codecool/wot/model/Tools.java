package com.codecool.wot.model;

import com.codecool.wot.dao.ClassDAO;
import com.codecool.wot.view.*;

public class Tools {

    private ClassDAO classDAO;
    private View view;

    Tools(View view, ClassDAO classDAO) {
        this.view = view;
        this.classDAO = classDAO;
    }

    public SchoolClass getClassByName() {

        SchoolClass schoolClass = null;

        while (schoolClass == null) {
            String name = view.getStringInput("Enter class name :");
            schoolClass = classDAO.getByName(name);

            if (schoolClass == null) {
                view.printMessage("NOT FOUND ! ");
            }
        }
        return schoolClass;
    }
}
