import model.*;
import view.*;
import controller.*;

class Application{

    public static void main(String[] args){
        MentorController ctrl = new MentorController();

        ctrl.createStudent();
        ctrl.createStudent();
        ctrl.showAllStudents();
    }
}
