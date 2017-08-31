package src.app;

import src.model.*;
import src.view.*;
import src.controller.*;

class Application{

    public static void main(String[] args){
        MentorController ctrl = new MentorController();
        AdministratorController controller = new AdministratorController();
        controller.createMentor();
        ctrl.createStudent();
        ctrl.createStudent();
        ctrl.showAllStudents();
        controller.showAllMentors();
    }
}
