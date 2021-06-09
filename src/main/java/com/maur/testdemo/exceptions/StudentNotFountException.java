package com.maur.testdemo.exceptions;

public class StudentNotFountException extends RuntimeException{
    public StudentNotFountException(String message, Exception exception){
        super(message, exception);
    }
    public StudentNotFountException(String message){
        super(message);
    }
}
