package com.cloudingYo.barrierFree.place.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaceDTO {
        private Long id;
        private String PLACE_NM;
        private String en_placename;
        private Long PLACE_KEY;
        private Long REC_SCORE;
        private String category;
        private Double latitude;
        private Double longitude;
}
