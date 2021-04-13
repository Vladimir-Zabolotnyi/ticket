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

    @Override
    public StopTimeEntity createOrUpdate(StopTimeEntity entity) {
        if (Objects.isNull(entity)) throw new IllegalArgumentException("entity is null");

        StopEntity stop = entity.getStop();
        if (Objects.nonNull(stop)) {
            if (!entityManager.contains(stop)) {
                entity.setStop(entityManager.merge(stop));
            }
        }
        return super.createOrUpdate(entity);

    }
}
