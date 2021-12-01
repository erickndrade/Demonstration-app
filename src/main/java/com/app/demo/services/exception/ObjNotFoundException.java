package com.app.demo.services.exception;

public class ObjNotFoundException extends RuntimeException{

    public ObjNotFoundException(String message){
        super(message);
    }

}
