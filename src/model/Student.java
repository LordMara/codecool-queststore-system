package src.model;

public class Student extends Account{
    private Class class;
    // private Team team;  TODO
    private Wallet wallet;
    // private Level level; TODO

    public Student(String name, String surname, String email, String password, Class class){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.class = class;
        this.ID = generateId();
        this.wallet = new Wallet();
        // this.level = new Level(); TODO

    }

    public Class getClass(){
        return this.class;
    }

    public void setClass(Class class){
        this.class = class;
    }

    // public void setTeam(Team team){
    //     this.team = team; TODO
    // }

    // public Team getTeam(){
    //     return this.team; TODO
    // }

    // public Level getLevel(){
    //     return this.level;TODO
    // }

    public String generateId(){

    }


}
