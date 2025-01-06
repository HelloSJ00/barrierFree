package com.cloudingYo.barrierFree.place.exception;

import com.cloudingYo.barrierFree.user.exception.NotExistUserException;

public class NotFoundPlaceException extends RuntimeException{
    public NotFoundPlaceException(String message) {
        super(message);
    }
}
