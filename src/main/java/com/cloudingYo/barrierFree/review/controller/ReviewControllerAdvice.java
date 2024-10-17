package com.cloudingYo.barrierFree.review.controller;

import com.cloudingYo.barrierFree.review.exception.ReviewAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ReviewControllerAdvice {
    @ExceptionHandler(ReviewAlreadyExistsException.class)
    public ResponseEntity<Object> handleReviewAlreadyExistsException(ReviewAlreadyExistsException ex) {
        // 클라이언트에게 409 Conflict 상태와 함께 메시지를 전송
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
