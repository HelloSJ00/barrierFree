package com.cloudingYo.barrierFree.Bookmark.repository;

import com.cloudingYo.barrierFree.Bookmark.entity.Bookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookmarkRepository {
    Bookmark save(Bookmark bookmark);
    boolean deleteByPlaceKeyAndUserId(Long placeKey, Long userId);
    Page<Bookmark> findByUserIdOrderByCreatedDateDesc(Long userId, Pageable pageable);
}
