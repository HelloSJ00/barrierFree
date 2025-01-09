package com.cloudingYo.barrierFree.place.service;

import com.cloudingYo.barrierFree.place.dto.PlaceCoordinateDTO;
import com.cloudingYo.barrierFree.place.exception.NotFoundPlaceException;
import com.cloudingYo.barrierFree.place.repository.StubEmptyPlaceRepository;
import com.cloudingYo.barrierFree.place.repository.StubExistPlaceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PlaceServiceImplTest {
    /*
    PlaceCoordinateDTO getPlaceCoordinateV2(int placeKey);
     */
    @Test
    void 장소키로_장소_좌표를_조회할_수있다(){
        //givrn
        int placeKey = 1;
        PlaceServiceImpl placeService = new PlaceServiceImpl(new StubExistPlaceRepository());

        //when
        PlaceCoordinateDTO placeCoordinateV2 = placeService.getPlaceCoordinateV2(placeKey);

        //then
        org.assertj.core.api.Assertions.assertThat(placeCoordinateV2).isNotNull();
    }

    @Test
    void 유효하지_않은_장소키로_조회시_예외_처리(){
        //givrn
        int placeKey = 1;
        PlaceServiceImpl placeService = new PlaceServiceImpl(new StubEmptyPlaceRepository());

        //then
        Assertions.assertThrows(NotFoundPlaceException.class,()->{
            placeService.getPlaceCoordinateV2(placeKey);
        });
    }
}