package com.cloudingYo.barrierFree.infra.repository;

import com.cloudingYo.barrierFree.infra.entity.Infra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfraRepository extends JpaRepository<Infra, Long> {
    Infra findByInfraname(String infraname);
}
