package src.model;

public abstract class Account{

    protected String name;
    protected String email;
    protected String phone;
    protected String password;
    protected Integer ID;

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPhone(){
        return this.phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Integer getId(){
        return this.ID;
    }

    public abstract Integer generateId();
}
