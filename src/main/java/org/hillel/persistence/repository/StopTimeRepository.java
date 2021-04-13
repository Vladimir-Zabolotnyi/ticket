package org.hillel.persistence.repository;

import org.hillel.persistence.entity.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

@Repository
public class StopTimeRepository extends CommonRepository<StopTimeEntity, Long> {


    protected StopTimeRepository() {
        super(StopTimeEntity.class);
    }

}
