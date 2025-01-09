package com.cloudingYo.barrierFree.common.exception.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorType {

    /**
     * 400 BAD REQUEST
     */
    NOT_FOUND_USER_INFORMATION(HttpStatus.BAD_REQUEST,"등록되지 않은 유저입니다"),
    REQUEST_VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    INVALID_TYPE_ERROR(HttpStatus.BAD_REQUEST, "잘못된 타입이 입력되었습니다."),
    NOT_FOUND_PLACE_INFORMATION(HttpStatus.BAD_REQUEST,"장소를 찾을 수 없습니다."),

    /**
     * 409 CONFLICT
     */
    REVIEW_ALREADY_EXIST(HttpStatus.CONFLICT,"이미 리뷰를 작성한 장소입니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }



}
