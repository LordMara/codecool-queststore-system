package src.model;

public class Admin{

    private static ArrayList<Admin> admins = new ArrayList<>();


    public Admin(String name, String surname, String email, String password){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.ID = generateId();
    }

    protected generateId(){

        String ID = generateRandom();

        while(checkIsUnique(ID)){
            ID = generateRandom();
        }
        return ID;
    }

    private boolean checkIsUnique(String ID){
        for (Student admin : Admin.admins){
            if admin.getId().equals(ID){
                return false;
            }
        }
        return true;
    }

    private String generateRandom(){
        Random rand = new Random();
        Integer number = rand.nextInt(9999);
        return String.format("A%d",number);
    }

}
