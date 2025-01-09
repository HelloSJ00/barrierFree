package com.cloudingYo.barrierFree.Bookmark.repository;

import com.cloudingYo.barrierFree.Bookmark.entity.Bookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookmarkRepositoryImpl implements BookmarkRepository{

    private final BookmarkJPARepository bookmarkJPARepository;

    @Override
    public Bookmark save(Bookmark bookmark) {
        return bookmarkJPARepository.save(bookmark);
    }

    @Override
    public boolean deleteByPlaceKeyAndUserId(Long placeKey, Long userId) {
        return bookmarkJPARepository.deleteByPlaceKeyAndUserId(placeKey,userId);
    }

    @Override
    public Page<Bookmark> findByUserIdOrderByCreatedDateDesc(Long userId, Pageable pageable) {
        return bookmarkJPARepository.findByUserIdOrderByCreatedDateDesc(userId,pageable);
    }
}
