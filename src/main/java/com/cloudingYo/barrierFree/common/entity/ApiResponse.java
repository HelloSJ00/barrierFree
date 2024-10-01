package com.cloudingYo.barrierFree.common.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;  // data 필드에 제네릭을 사용하여 다양한 타입의 데이터를 받도록 설정

    public static ApiResponse<?> fail(String s) {
        return ApiResponse.builder()
                .status(400)
                .message(s)
                .build();
    }

    public static ApiResponse<?> success(String s) {
        return ApiResponse.builder()
                .status(200)
                .message(s)
                .build();
    }
}
