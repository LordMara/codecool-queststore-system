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

        for (T object : objectsList){
            if (getByCondition(object, identifier)){
                return object;
            }
        }
        throw new NullPointerException();
    }

    public abstract boolean getByCondition(T object, V identifier);


    public void saveToDataBase(T object) throws  SQLException {

        Statement stmt = connection.createStatement();

        String query = insertionQuery(object);

        stmt.executeUpdate(query);

        stmt.close();
        connection.commit();

    }

    public abstract String insertionQuery(T object);

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
