package src.model;

public class Student extends Account{

    public Student(name, surname, email, password){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.ID = generateId();
    }
}
