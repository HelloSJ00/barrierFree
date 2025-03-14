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
    // User
    PROCESS_SUCCESS(HttpStatus.OK, "호출 성공"),
    LOGIN_SUCCESS(HttpStatus.OK, "로그인에 성공했습니다."),
    SIGNUP_SUCCESS(HttpStatus.OK,"회원가입이 완료되었습니다. "),
    LOGOUT_SUCCESS(HttpStatus.OK, "로그아웃에 성공했습니다."),
    FIND_USER_INFORMATION(HttpStatus.OK,"유저 정보를 찾았습니다."),
    UPDATE_SUCCESS_USER_INFORMATION(HttpStatus.OK,"유저 정보 업데이트 성공"),
    EMAIL_AVAILABLE(HttpStatus.OK,"사용 가능한 이메일입니다."),
    SIGN_OUT_SUCCESS(HttpStatus.OK,"회원 탈퇴 성공하였습니다"),

    // Bookmark
    REGISTER_BOOKMARK(HttpStatus.OK,"북마크 저장에 성공하였습니다."),
    DELETE_BOOKMARK(HttpStatus.OK,"북마크에서 장소를 삭제하였습니다."),
    GET_BOOKMARK_RANK_5(HttpStatus.OK,"북마크 상위 5개 장소를 조회하였습니다."),

    // Place
    FIND_PLACE_INFORMATION(HttpStatus.OK,"장소 좌표를 조회했습니다"),

    // Review
    REGISTER_REVIEW(HttpStatus.OK,"리뷰 등록에 성공하였습니다."),
    DELETE_REVIEW(HttpStatus.OK,"리뷰를 삭제하였습니다."),
    GET_PLACE_REVIEWS(HttpStatus.OK,"장소 리뷰를 조회하였습니다."),
    GET_MY_REVIEWS(HttpStatus.OK,"나의 리뷰를 조회하였습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }

}
