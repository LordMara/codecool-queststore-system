package com.codecool.wot.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Tools {

    public List<String> parseURI (String uri) {

        String[] actions  = uri.split("/");
        ArrayList<String> parsedActions = new ArrayList<>(Arrays.asList(actions));

        for(String elem : parsedActions){
            if(! (elem.trim().length() > 0)){
                parsedActions.remove(elem);
            }
        }

        return parsedActions;
    }
}
