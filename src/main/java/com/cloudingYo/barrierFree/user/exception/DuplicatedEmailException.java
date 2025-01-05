package com.cloudingYo.barrierFree.user.exception;

public class DuplicatedEmailException extends RuntimeException{
    public DuplicatedEmailException(String message) {
        super(message);
    }
}
