package org.hillel.persistence.repository;

import org.hillel.persistence.entity.StopTimeEntity;
import org.springframework.stereotype.Repository;

@Repository
public class StopTimeRepository extends CommonRepository<StopTimeEntity, Long> {


    protected StopTimeRepository() {
        super(StopTimeEntity.class);
    }

}
