package com.cloudingYo.barrierFree.review.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewRequestDTO {
    private int placeKey;
    private Long userId;
    private int rating;
    private String content;
}
