package com.example.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class JsonTools {
    public static final int SUCCESS = 0;
    public static final int SYS_ERROR = 10;
    public static final int LOGIN_ERROR = 20;
    public static final int NO_LOGIN_ERROR = 21;
    public static final int ADD_ERROR = 444;
    public static final int DEL_ERROR = 445;
    public static final int UPDATE_ERROR = 446;

    //...
    public static String exeSuccess(){
        return querySuccess("data", 1);
    }
    public static String objectToJson(Object data) {
        String string = new Gson().toJson(data);
        return string;
    }
    public static String executeSuccess(){
        return querySuccess(null);
    }
    public static String querySuccess(String name,Object value){

        Map resultMap = new LinkedHashMap();
        resultMap.put(name, value);
        return querySuccess(resultMap);
    }
    public static String querySuccess(Map<String,Object> resultMap){
        Map map = new LinkedHashMap();
        map.put("code",0);
        map.put("result",resultMap);
        return new Gson().toJson(map);
    }

    public static String error(int code){
        Map map = new LinkedHashMap();
        map.put("code",code);
        return new Gson().toJson(map);
    }
}

