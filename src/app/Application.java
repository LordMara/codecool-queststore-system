package src.app;

import src.model.*;
import src.view.*;
import src.controller.*;

class Application{

    public static void main(String[] args){

        // MentorController mentor = new MentorController();
        // mentor.startController();
        AdministratorController admin = new AdministratorController();
        admin.startController();
    }

}
