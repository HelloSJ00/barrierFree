package com.cloudingYo.barrierFree.place.service;

import com.cloudingYo.barrierFree.place.dto.PlaceDTO;
import com.cloudingYo.barrierFree.place.dto.PlaceResponseDTO;
import com.cloudingYo.barrierFree.place.entity.Place;
import com.cloudingYo.barrierFree.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;

    @Override
    @Transactional(readOnly = true)
    public PlaceResponseDTO getPlace(PlaceDTO placeDTO){
        Place place = placeRepository.findByPlacename(placeDTO.getPlacename());
        return PlaceResponseDTO.builder()
                .placename(place.getPlacename())
                .latitude(place.getLatitude())
                .longitude(place.getLongitude())
                .build();
    }

    /*
    * 장소 등록 아마 스프링 배치를 활용할 예정
     */
    @Override
    public void registerAllPlace(PlaceDTO placeDTO) {
    }

    @Override
    public void registerPlace(PlaceDTO placeDTO) {
        Place registerPlace = Place.builder()
                .placename(placeDTO.getPlacename())
                .latitude(placeDTO.getLatitude())
                .longitude(placeDTO.getLongitude())
                .build();
        placeRepository.save(registerPlace);
    }

    @Override
    public void updatePlace(PlaceDTO placeDTO) {
        Place updatePlace = placeRepository.findByPlacename(placeDTO.getPlacename());
        if (updatePlace == null) {
            throw new IllegalArgumentException("수정할 장소가 존재하지 않습니다.");
        }
        else{
            /*
            * 수정할 내용이 있으면 수정
             */
        }
    }

    @Override
    public void deletePlace(PlaceDTO placeDTO) {
        Place deletePlace = placeRepository.findByPlacename(placeDTO.getPlacename());
        if (deletePlace == null) {
            throw new IllegalArgumentException("삭제할 장소가 존재하지 않습니다.");
        }
        else{
            placeRepository.delete(deletePlace);
        }
    }
}
