package com.cloudingYo.barrierFree.Bookmark.repository;

import com.cloudingYo.barrierFree.Bookmark.entity.Bookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkJPARepository extends JpaRepository<Bookmark, Long> {
    boolean deleteByPlaceKeyAndUserId(Long placeKey, Long userId);
    Page<Bookmark> findByUserIdOrderByCreatedDateDesc(Long userId, Pageable pageable);
}
