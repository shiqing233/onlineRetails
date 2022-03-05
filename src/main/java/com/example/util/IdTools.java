package com.example.util;

public class IdTools {
    public static String getId(){
        return ((Long)System.currentTimeMillis()).toString();
    }
}
