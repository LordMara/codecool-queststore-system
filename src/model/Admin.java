package src.model;

public class Admin{

    private static ArrayList<Admin> admins = new ArrayList<>();

    public Admin(String name, String surname, String email, String password){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.ID = generateId("A");
    }

}
