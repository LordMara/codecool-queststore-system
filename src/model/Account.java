package src.model;

public abstract class Account{

    protected String name;
    protected String surname;
    protected String email;
    protected String phone;
    protected String password;
    protected String ID;

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getSurname(){
        return this.surname;
    }

    public void setSurname(String surname){
        this.surname = surname;
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

    protected String generateId(String uniqeSign){
        String ID = generateRandom(uniqeSign);

        while(checkIsUnique(ID)){
            ID = generateRandom(uniqeSign);
        }
        return ID;
    }

    private boolean checkIsUnique<T>(String ID){
        ActualClass = this.getClass()

        for (T member : ActualClass.members){
            if member.getId().equals(ID){
                return false;
            }
        }
        return true;
    }

    private String generateRandom(String uniqeSign){
        Random rand = new Random();
        Integer number = rand.nextInt(9999);
        return String.format("%s%d", uniqeSign, number);
    }
}
