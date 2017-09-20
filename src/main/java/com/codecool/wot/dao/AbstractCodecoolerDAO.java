package com.codecool.wot.dao;

import java.util.LinkedList;

public abstract class AbstractCodecoolerDAO <T> extends AbstractDAO <T> {

    public static LinkedList<T> getByClass(String className){
        return null;
    }

    public static LinkedList<T> getByLogin(String login){
        return null;
    }

}
