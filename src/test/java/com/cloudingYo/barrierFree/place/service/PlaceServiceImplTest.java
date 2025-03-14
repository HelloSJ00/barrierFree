package com.cloudingYo.barrierFree.place.service;

import com.cloudingYo.barrierFree.place.dto.resp.PlaceCoordinateDTO;
import com.cloudingYo.barrierFree.place.repository.StubExistPlaceRepository;
import org.junit.jupiter.api.Test;

class PlaceServiceImplTest {
    /*
    PlaceCoordinateDTO getPlaceCoordinateV2(int placeKey);
     */
    @Test
    void 장소키로_장소_좌표를_조회할_수있다() {
        //givrn
        Long placeKey = 1L;
        PlaceServiceImpl placeService = new PlaceServiceImpl(new StubExistPlaceRepository());

        //when
        PlaceCoordinateDTO placeCoordinateV2 = placeService.getPlaceCoordinateV2(placeKey);

        //then
        org.assertj.core.api.Assertions.assertThat(placeCoordinateV2).isNotNull();
    }

//    @Test
//    void 유효하지_않은_장소키로_조회시_예외_처리(){
//        //givrn
//        Long placeKey = 1L;
//        PlaceServiceImpl placeService = new PlaceServiceImpl(new StubEmptyPlaceRepository());
//
//        //then
//        Assertions.assertThrows(CustomException.class,()->{
//            placeService.getPlaceCoordinateV2(placeKey);
//        });
//    }
}