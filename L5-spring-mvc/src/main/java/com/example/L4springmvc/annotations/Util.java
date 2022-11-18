package com.example.L4springmvc.annotations;

import jakarta.annotation.PostConstruct;

import java.util.HashMap;

public class Util {

    public static HashMap map;

    @PostConstruct
    public void init(){

        //Dao
        map = new HashMap();
        map.put("provider1", "Airtel");
        map.put("provider2", "Jio");
    }

}
