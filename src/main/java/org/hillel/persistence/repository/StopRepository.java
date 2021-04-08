package org.hillel.persistence.repository;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

@Repository
public class StopRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Long create(final StopEntity stop) {
        if (Objects.isNull(stop)) throw new IllegalArgumentException("stop is null");
        entityManager.persist(stop);
        return stop.getId();
    }
    public void save(final StopEntity stop) {
        entityManager.merge(stop);
    }
}
