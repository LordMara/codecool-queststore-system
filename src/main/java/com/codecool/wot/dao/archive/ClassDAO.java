package com.codecool.wot.dao.archive;

import com.codecool.wot.dao.AbstractDAO;
import com.codecool.wot.model.SchoolClass;

import java.sql.*;

public class ClassDAO extends AbstractDAO<SchoolClass> {

    private Connection connection;

    public ClassDAO(Connection connection) {
        this.connection = connection;
        loadClasses();
    }

    @Override
    public void add(SchoolClass object) {
        super.add(object);
        saveToDataBase(object);
    }

    private void loadClasses() {

        try {

            Statement stmt = connection.createStatement();

            String query = "SELECT * FROM classes";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                Integer classId = rs.getInt("classId");
                String name = rs.getString("name");

                objectsList.add(new SchoolClass(classId, name));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateClass(SchoolClass schoolClass) {

        try {
            Statement stmt = connection.createStatement();

            String query = String.format("UPDATE classes SET name = '%s' WHERE classId = %d ;", schoolClass.getName(), schoolClass.getId());

            stmt.executeUpdate(query);

            stmt.close();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public SchoolClass getByName(String name) {
        SchoolClass schoolClass = null;
        for (SchoolClass elem : objectsList){
            if (elem.getName().equals(name)){
                schoolClass = elem;
            }
        }
        return schoolClass;
    }

    private void saveToDataBase(SchoolClass schoolClass) {

        try {

            Statement stmt = connection.createStatement();

            String values = String.format("(%d, '%s')", schoolClass.getId(), schoolClass.getName());

            String query = "INSERT INTO classes (classId, name) VALUES " + values;

            stmt.executeUpdate(query);

            stmt.close();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
