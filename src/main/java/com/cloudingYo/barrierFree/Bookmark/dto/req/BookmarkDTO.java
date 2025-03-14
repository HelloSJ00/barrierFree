package com.cloudingYo.barrierFree.Bookmark.dto.req;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookmarkDTO {
    private Long placeKey;
    private String placename;
    private boolean bookmarked;
    private Double latitude;
    private Double longitude;
}
