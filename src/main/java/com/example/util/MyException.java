package com.example.util;

public class MyException extends  RuntimeException{
    private int errorCode;

    public MyException(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
