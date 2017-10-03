package com.codecool.wot.dao;

import com.codecool.wot.model.SchoolClass;

import java.sql.*;

public class ClassDAO extends AbstractDAO<SchoolClass> {

    public ClassDAO(Connection connection) throws SQLException {

        this.connection = connection;
        String loadQuery = "SELECT * FROM classes";
        load(loadQuery);
    }




    /////////////////////////////////////////////////////////////////



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


    public SchoolClass getByName(String name) throws NullPointerException {

        for (SchoolClass schoolClass : objectsList){
            if (schoolClass.getName().equals(name)){
                return schoolClass;
            }
        }
        throw new NullPointerException();
    }

    private void saveToDataBase(SchoolClass schoolClass) {

        try {

            Statement stmt = connection.createStatement();

            String values = String.format("(%d, %s)", schoolClass.getId(), schoolClass.getName());

            String query = "INSERT INTO classes (classId, name) VALUES " + values;

            stmt.executeUpdate(query);

            stmt.close();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
