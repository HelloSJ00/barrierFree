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
    private T data;  // data 필드에 제네릭을 사용하여 다양한 타입의 데이터를 받도록 설정

    public static PlaceResponseDTO<?> fail(String s) {
        return PlaceResponseDTO.builder()
                .status(400)
                .message(s)
                .build();
    }

    public static PlaceResponseDTO<?> success(String s) {
        return PlaceResponseDTO.builder()
                .status(200)
                .message(s)
                .build();
    }

    public static PlaceResponseDTO<?> success(String s, PlaceDTO data) {
        return PlaceResponseDTO.builder()
                .status(200)
                .message(s)
                .data(data)
                .build();
    }

    public static PlaceResponseDTO<List<PlaceDTO>> success(String message, List<PlaceDTO> placeDTOS) {
        return PlaceResponseDTO.<List<PlaceDTO>>builder()  // 제네릭 타입 명시
                .status(200)
                .message(message)
                .data(placeDTOS)
                .build();
    }


    public PlaceResponseDTO<?> fail(String s, T data) {
        return PlaceResponseDTO.builder()
                .status(400)
                .message(s)
                .data(data)
                .build();
    }
}
