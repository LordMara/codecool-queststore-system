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

}