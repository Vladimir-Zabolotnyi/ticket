package org.hillel.persistence.repository;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.entity.StopTimeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

@Repository
public class StopTimeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Long create(final StopTimeEntity stopTime) {
        if (Objects.isNull(stopTime)) throw new IllegalArgumentException("stopTime is null");
        entityManager.persist(stopTime);
        return stopTime.getId();
    }

    public void save(final StopTimeEntity stopTime) {
        entityManager.merge(stopTime);
    }
}
