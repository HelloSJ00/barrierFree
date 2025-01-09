package com.cloudingYo.barrierFree.Bookmark.service;

import com.cloudingYo.barrierFree.Bookmark.dto.req.BookmarkDTO;
import com.cloudingYo.barrierFree.Bookmark.dto.resp.BookmarkRegisterDTO;
import com.cloudingYo.barrierFree.Bookmark.entity.Bookmark;
import com.cloudingYo.barrierFree.Bookmark.repository.BookmarkRepository;
import com.cloudingYo.barrierFree.common.exception.model.CustomException;
import com.cloudingYo.barrierFree.place.entity.Place;
import com.cloudingYo.barrierFree.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.cloudingYo.barrierFree.common.exception.enums.ErrorType.NOT_FOUND_PLACE_INFORMATION;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final PlaceRepository placeRepository;

    @Override
    public BookmarkRegisterDTO registerBookmark(Long placeKey, Long userId){
        Place place = placeRepository.findByPlaceKey(placeKey)
                .orElseThrow(()->new CustomException(NOT_FOUND_PLACE_INFORMATION));

        Bookmark bm = bookmarkRepository.save(Bookmark.builder()
                .placeKey(placeKey)
                .placeId(place.getId())
                .userId(userId)
                .placename(place.getPlacename())
                .longitude(place.getLongitude())
                .latitude(place.getLatitude())
                .build());

        return BookmarkRegisterDTO.builder()
                .placeKey(bm.getPlaceKey())
                .placename(bm.getPlacename())
                .build();
    }

    @Override
    public boolean deleteFavoritePlace(Long placeKey, Long userId){
        return bookmarkRepository.deleteByPlaceKeyAndUserId(placeKey, userId);
    }

    @Override
    public Page<BookmarkDTO> getFavoritePlaceList(Long userId, int page) {
        Pageable pageable = PageRequest.of(page, 5); // 페이지당 5개 설정

        // Streamable<FavoritePlaceDTO>을 List<FavoritePlaceDTO>로 변환
        List<BookmarkDTO> bookmarkDTOList = bookmarkRepository
                .findByUserIdOrderByCreatedDateDesc(userId, pageable)
                .stream()
                .map(bookmark -> {
                    Optional<Place> placeOptional = placeRepository.findByPlaceKey(bookmark.getPlaceKey());

                    return placeOptional.map(place -> BookmarkDTO.builder()
                            .placeKey(bookmark.getPlaceKey())
                            .placename(place.getPlacename())
                            .latitude(place.getLatitude())
                            .longitude(place.getLongitude())
                            .bookmarked(true)
                            .build()).orElse(null);
                })
                .filter(dto -> dto != null) // null 값 제거
                .collect(Collectors.toList());

        // List<FavoritePlaceDTO>를 Page<FavoritePlaceDTO>로 변환
        return new PageImpl<>(bookmarkDTOList, pageable, bookmarkDTOList.size());
    }
}
