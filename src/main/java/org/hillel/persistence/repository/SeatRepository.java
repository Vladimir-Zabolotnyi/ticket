package org.hillel.persistence.repository;

import org.hillel.persistence.entity.SeatEntity;
import org.springframework.stereotype.Repository;

@Repository
public class SeatRepository extends CommonRepository<SeatEntity, Long> {


    protected SeatRepository() {

        super(SeatEntity.class);
    }


}
