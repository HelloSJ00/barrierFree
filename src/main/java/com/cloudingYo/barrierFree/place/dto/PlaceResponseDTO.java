package com.cloudingYo.barrierFree.place.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class PlaceResponseDTO<T> {
    private int status;
    private String message;
    private T data;  // data 필드에 제네릭을 사용하여 다양한 타입의 데이터를 받도록 설정
}
