package com.cloudingYo.barrierFree.place.dto;

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
        private String name;
        private String latitude;
        private String longitude;
}
