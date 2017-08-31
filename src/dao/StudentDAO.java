package src.dao;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;

import src.dao.AbstractDAO;
import src.model.Student;
import sun.security.util.Password;

public class StudentDAO extends AbstractDAO {
    private ArrayList<Student> objectList;
    private String path;

    public StudentDAO(String path) {
        this.objectList = new ArrayList<>();
        this.path = path;
        load();
    }

    public void load(String path) {
        String line;
        try (Scanner readFile = new Scanner(new File(this.path))) {
            while (readFile.hasNext()) {
                line = readFile.nextLine();
                String[] temp = line.split(",");

                Student student = new Student();

                student.setName(temp[0]);
                student.setSurname(temp[1]);
                student.setLogin(temp[2]);
                student.setPassword(temp[3]);
                // student.setCodecoolClass(temp[4]);

                addObject(student);
            }

        }
    }

    public ArrayList<Student> getAllObjects() {
        return this.objectList;
    }

    public void addObject(Student object) {
        this.objectList.add(object);
    }

    public boolean removeObject(Student object) {
        this.objectList.remove(object);
        return true;
    }

    public void save() {
        try (PrintWriter writer = new PrintWriter(this.path, "UTF-8")) {
            for (Student student : this.objectList) {
                String name = student.getName();
                String surname = student.getSurname();
                String login = student.getLogin();
                String password = student.getPassword();
                writer.println(String.format("%s, %s, %s, %s", name, surname, login, password));
            }
        }

    }
}