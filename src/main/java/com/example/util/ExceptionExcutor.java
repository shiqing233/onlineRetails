package com.example.util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionExcutor {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handlerExcutor(Exception e){
        if(e.getClass()==MyException.class){
            return JsonTools.error(((MyException)e).getErrorCode());
        }else {
            e.printStackTrace();
            return JsonTools.error(JsonTools.SYS_ERROR);
        }
    }
}
