package com.cloudingYo.barrierFree.review.service;

import com.cloudingYo.barrierFree.review.document.Review;
import com.cloudingYo.barrierFree.review.dto.req.ReviewSavedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ReviewEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public ReviewEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishReviewSavedEvent(Review review) {
        eventPublisher.publishEvent(new ReviewSavedEvent(review));
    }
}
