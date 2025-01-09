package com.cloudingYo.barrierFree.Bookmark.service;

import com.cloudingYo.barrierFree.Bookmark.dto.req.BookmarkDTO;
import com.cloudingYo.barrierFree.Bookmark.dto.resp.BookmarkRegisterDTO;
import org.springframework.data.domain.Page;

public interface BookmarkService {
    BookmarkRegisterDTO registerBookmark(Long placeKey, Long userId);
    boolean deleteFavoritePlace(Long placeKey, Long userId);
    Page<BookmarkDTO> getFavoritePlaceList(Long userId, int page);
}
