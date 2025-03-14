package com.cloudingYo.barrierFree.common.exception;

import com.cloudingYo.barrierFree.common.entity.ApiResponse;
import com.cloudingYo.barrierFree.common.exception.model.CustomException;
import jakarta.validation.UnexpectedTypeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static com.cloudingYo.barrierFree.common.exception.enums.ErrorType.INVALID_TYPE_ERROR;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    /**
     * 400 VALIDATION_ERROR
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnexpectedTypeException.class)
    protected ApiResponse<?> handleUnexprectedTypeException(final UnexpectedTypeException e) {
        return ApiResponse.error(INVALID_TYPE_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiResponse<?> handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException e) {
        return ApiResponse.error(INVALID_TYPE_ERROR);
    }

    /**
     * CONFLICT_ERROR
     */

    /**
     * CUSTOM_ERROR
     */
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ApiResponse<?>> handleCustomException(CustomException e) {

        log.warn("CustomException Occured: {}", e.getMessage());

        return ResponseEntity.status(e.getHttpStatus())
                .body(ApiResponse.error(e.getErrorType()));
    }
}
