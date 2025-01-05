package com.cloudingYo.barrierFree.user.exception;

public class NotExistUserException extends RuntimeException{
    public NotExistUserException(String message) {
        super(message);
    }
}
