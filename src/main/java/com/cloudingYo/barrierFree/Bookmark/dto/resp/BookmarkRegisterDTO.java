package com.cloudingYo.barrierFree.Bookmark.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookmarkRegisterDTO {
    private Long placeKey;
    private String placename;
    private boolean bookmarked;
}
