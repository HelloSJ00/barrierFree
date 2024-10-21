package com.cloudingYo.barrierFree.place.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class PlaceResponseDTO<T> {
    private int status;
    private String message;
    private T data;  // 제네릭 타입으로 데이터 설정

    // 실패 응답 생성 메서드
    public static <T> PlaceResponseDTO<T> fail(String message) {
        return PlaceResponseDTO.<T>builder()
                .status(400)
                .message(message)
                .build();
    }

    // 성공 응답 생성 메서드 (데이터 없음)
    public static <T> PlaceResponseDTO<T> success(String message) {
        return PlaceResponseDTO.<T>builder()
                .status(200)
                .message(message)
                .build();
    }

    // 성공 응답 생성 메서드 (데이터 포함)
    public static <T> PlaceResponseDTO<T> success(String message, T data) {
        return PlaceResponseDTO.<T>builder()
                .status(200)
                .message(message)
                .data(data)
                .build();
    }

    // 실패 응답 생성 메서드 (데이터 포함)
    public static <T> PlaceResponseDTO<T> fail(String message, T data) {
        return PlaceResponseDTO.<T>builder()
                .status(400)
                .message(message)
                .data(data)
                .build();
    }
}

