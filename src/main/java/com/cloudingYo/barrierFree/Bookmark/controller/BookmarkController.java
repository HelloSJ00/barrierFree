package com.cloudingYo.barrierFree.Bookmark.controller;

import com.cloudingYo.barrierFree.Bookmark.service.BookmarkService;
import com.cloudingYo.barrierFree.common.entity.ApiResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.cloudingYo.barrierFree.common.exception.enums.SuccessType.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/spring/bookmark")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @GetMapping("/register")
    public ResponseEntity<ApiResponse<?>> registerFavoritePlace(
            @RequestParam Long placeKey, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId"); // 세션에서 userId 가져오기
        return ResponseEntity.ok(ApiResponse.success(REGISTER_BOOKMARK,bookmarkService.registerBookmark(placeKey,userId)));
    }

    @GetMapping("/delete")
    public ResponseEntity<ApiResponse<?>> deleteFavoritePlace(
            @RequestParam Long placeKey, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId"); // 세션에서 userId 가져오기
        return ResponseEntity.ok(ApiResponse.success(DELETE_BOOKMARK,bookmarkService.deleteFavoritePlace(placeKey, userId)));
    }

    // userId와 page를 인자로 받아 5개씩 페이징된 데이터 반환
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<?>> getFavoritePlaceList(
            @RequestParam(defaultValue = "0") int page,
            HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        return ResponseEntity.ok(ApiResponse.success(GET_BOOKMARK_RANK_5,bookmarkService.getFavoritePlaceList(userId, page)));
    }
}
