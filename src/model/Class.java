package src.model;

import java.util.ArrayList;

public class Class {

    Mentor mentor;
    ArrayList<Student> students;
    String name;
    public Class(Mentor newMentor, ArrayList<Student> newStudents, String newName) {

        mentor = newMentor;
        students = newStudents;
        name = newName;
    }

    public Mentor getMentor() {
        return mentor;
    }


    public void setMentor(Mentor newMentor) {
        mentor = newMentor;
    }


    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> newStudents) {
        students = newStudents;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

}