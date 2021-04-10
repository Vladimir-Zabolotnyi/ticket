package org.hillel.persistence.repository;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.SeatEntity;
import org.hillel.persistence.entity.StopTimeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

@Repository
public class SeatRepository extends CommonRepository<SeatEntity,Long>{


    protected SeatRepository() {
        super(SeatEntity.class);
    }
}
