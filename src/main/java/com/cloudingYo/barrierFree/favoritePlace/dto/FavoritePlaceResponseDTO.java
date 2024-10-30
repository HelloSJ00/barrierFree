package com.cloudingYo.barrierFree.favoritePlace.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class FavoritePlaceResponseDTO<T> {
    private String message;
    private int status;
    private T data;

    // 메시지만 설정하는 생성자
    public FavoritePlaceResponseDTO(String message) {
        this.message = message;
        this.status = 200; // 기본 상태 코드로 설정 (필요시 수정 가능)
        this.data = null; // 데이터가 없을 때는 null로 초기화
    }

    // 메시지와 데이터 설정하는 생성자
    public FavoritePlaceResponseDTO(String message, T data) {
        this.message = message;
        this.status = 200; // 기본 상태 코드
        this.data = data;
    }
}

    // 메시지, 상태 코드, 데이터 설정하는 생성자
