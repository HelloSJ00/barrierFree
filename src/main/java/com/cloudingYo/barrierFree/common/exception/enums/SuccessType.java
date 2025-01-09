package com.cloudingYo.barrierFree.common.exception.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessType {
    /**
     * 200 OK
     */
    PROCESS_SUCCESS(HttpStatus.OK, "호출 성공"),
    LOGIN_SUCCESS(HttpStatus.OK, "로그인에 성공했습니다."),
    SIGNUP_SUCCESS(HttpStatus.OK,"회원가입이 완료되었습니다. "),
    LOGOUT_SUCCESS(HttpStatus.OK, "로그아웃에 성공했습니다."),
    FIND_USER_INFORMATION(HttpStatus.OK,"유저 정보를 찾았습니다."),
    UPDATE_SUCCESS_USER_INFORMATION(HttpStatus.OK,"유저 정보 업데이트 성공"),
    EMAIL_AVAILABLE(HttpStatus.OK,"사용 가능한 이메일입니다."),
    SIGN_OUT_SUCCESS(HttpStatus.OK,"회원 탈퇴 성공하였습니다");


    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }

}
