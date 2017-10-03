package com.codecool.wot.dao;

import com.codecool.wot.interfaces.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public abstract class AbstractDAO<T> implements DAO<T> {


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


    public void add(T object) {
        objectsList.add(object);
    }

    public void update(T object) {}

    public void remove(T object) {
        objectsList.remove(object);
    }

    public T getBy(String identifier) {}

    public T getBy(Integer identifier) {}

    public void setObjectList(ArrayList<T> newObjectsList) {
        objectsList = newObjectsList;
    }

    public ArrayList<T> getObjectList() {
        return objectsList;
    }

    public void saveToDataBase(T object) {}


}
