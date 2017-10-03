package com.codecool.wot.dao;

import com.codecool.wot.interfaces.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public abstract class AbstractDAO<T, V> implements DAO<T> {


    protected ArrayList<T> objectsList = new ArrayList<T>();
    protected Connection connection;


    public void load(String query) throws SQLException {

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        loadObjectsToLocalList(rs);
        stmt.close();
        rs.close();
    }

    public abstract void loadObjectsToLocalList(ResultSet resultSet) throws SQLException;


    public void update(T object) throws SQLException {

        Statement stmt = connection.createStatement();

        String query = updateQuery(object);

        stmt.executeUpdate(query);

        stmt.close();
        connection.commit();

    }

    public abstract String updateQuery(T object);

    public T getBy(V identifier) throws NullPointerException {
        T person = null;
        for (T object : objectsList){
            if (getByCondition(object, identifier)){
                person = object;
            }
        }
        return person;
    }

    public abstract boolean getByCondition(T object, V identifier);

    public String getIDFromDB(V identifier) throws SQLException {

        Statement stmt = connection.createStatement();
        String query = getIDFromDBQuery(identifier);
        ResultSet rs = stmt.executeQuery(query);

        return rs.getString(0);


    }

    public abstract String getIDFromDBQuery(V identifier);


    public void saveToDataBase(String ... args) throws  SQLException {

        Statement stmt = connection.createStatement();

        String query = insertionQuery(args);

        stmt.executeUpdate(query);

        stmt.close();
        connection.commit();

    }

    public void saveToDataBase(String ID, String classID) throws  SQLException {

        Statement stmt = connection.createStatement();

        String query = insertionQuery(ID, classID);

        stmt.executeUpdate(query);

        stmt.close();
        connection.commit();

    }

    public abstract String insertionQuery(String ... args);

    public String insertionQuery(String ID, String classID) {

        String values = String.format("('%d', '%d')", ID, classID);
        String query = "INSERT INTO persons_classes (personId, classId) VALUES " + values;

        return  query;
    }

    public void setObjectList(ArrayList<T> newObjectsList) {
        objectsList = newObjectsList;
    }

    public ArrayList<T> getObjectList() {
        return objectsList;
    }

    public void add(T object) {
        objectsList.add(object);
    }

    public void remove(T object) {
        objectsList.remove(object);
    }


}
