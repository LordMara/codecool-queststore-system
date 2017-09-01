package src.model;

import java.util.ArrayList;

public class Class {

    Mentor mentor;
    ArrayList<Student> students;
    public Class(Mentor newMentor, ArrayList<Student> newStudents) {

        mentor = newMentor;
        students = newStudents;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void setMentor(Mentor newMentor) {
        mentor = newMentor;
    }

}