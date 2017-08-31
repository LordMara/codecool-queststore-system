import src.model.*;
import src.view.*;
import src.controller.*;

class Application{

    public static void main(String[] args){
        MentorController ctrl = new MentorController();

        ctrl.createStudent();
        ctrl.createStudent();
        ctrl.showAllStudents();
    }
}
