package com.cloudingYo.barrierFree.review.dto.req;

import com.cloudingYo.barrierFree.review.document.Review;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReviewSavedEvent {
    private final Review review; // 저장된 리뷰 정보 포함
}
