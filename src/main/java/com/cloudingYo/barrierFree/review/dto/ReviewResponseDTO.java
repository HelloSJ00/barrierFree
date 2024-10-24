package com.cloudingYo.barrierFree.review.dto;

import lombok.*;
import org.bson.types.ObjectId;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDTO<T> {
        private int status;
        private String message;
        private T data;  // data 필드에 제네릭을 사용하여 다양한 타입의 데이터를 받도록 설정

        public static <T> ReviewResponseDTO<T> fail(String s) {
                return ReviewResponseDTO.<T>builder()
                        .status(400)
                        .message(s)
                        .build();
        }

        public static <T> ReviewResponseDTO<T> success(String s) {
                return ReviewResponseDTO.<T>builder()
                        .status(200)
                        .message(s)
                        .build();
        }

        public static <T> ReviewResponseDTO<T> success(String s, T data) {
                return ReviewResponseDTO.<T>builder()
                        .status(200)
                        .message(s)
                        .data(data)
                        .build();
        }

        public static <T> ReviewResponseDTO<T> fail(String s, T data) {
                return ReviewResponseDTO.<T>builder()
                        .status(400)
                        .message(s)
                        .data(data)
                        .build();
        }
}
