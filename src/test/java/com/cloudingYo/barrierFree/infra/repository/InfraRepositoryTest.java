package com.cloudingYo.barrierFree.infra.repository;

import com.cloudingYo.barrierFree.infra.entity.Infra;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class InfraRepositoryTest {
//    @Autowired
//    EntityManager em ;
//    @Autowired
//    InfraRepository infraRepository;
//
//    @Test
//    void findInfraByAll() {
//        // given
//        Infra infraA = Infra.builder()
//                .infraname("a")
//                .build();
//        infraRepository.save(infraA);
//
//        Infra infraB = Infra.builder()
//                .infraname("b")
//                .build();
//        infraRepository.save(infraB);
//
//        em.flush();
//
//        // when
//        Infra findInfraA = infraRepository.findByInfraname("a");
//        Infra findInfraB = infraRepository.findByInfraname("b");
//
//        // then
//        assertEquals(findInfraA.getInfraname(), infraA.getInfraname());
//        assertEquals(findInfraB.getInfraname(), infraB.getInfraname());
//    }
}