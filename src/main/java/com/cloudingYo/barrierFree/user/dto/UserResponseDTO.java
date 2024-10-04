package com.cloudingYo.barrierFree.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO<T> {
        private int status;
        private String message;
        private T data;  // data 필드에 제네릭을 사용하여 다양한 타입의 데이터를 받도록 설정

        public static UserResponseDTO<?> fail(String s) {
            return UserResponseDTO.builder()
                    .status(400)
                    .message(s)
                    .build();
        }

        public static UserResponseDTO<?> success(String s) {
            return UserResponseDTO.builder()
                    .status(200)
                    .message(s)
                    .build();
        }

        public static UserResponseDTO<?> success(String s, UserDTO data) {
            return UserResponseDTO.builder()
                    .status(200)
                    .message(s)
                    .data(data)
                    .build();
        }

    public static UserResponseDTO<?> success(String s, boolean b) {
        return UserResponseDTO.builder()
                .status(200)
                .message(s)
                .data(b)
                .build();
    }

    public UserResponseDTO<?> fail(String s, T data) {
            return UserResponseDTO.builder()
                    .status(400)
                    .message(s)
                    .data(data)
                    .build();
        }
}
