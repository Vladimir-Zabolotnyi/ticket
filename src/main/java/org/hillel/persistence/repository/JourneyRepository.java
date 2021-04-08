package org.hillel.persistence.repository;

import org.hillel.persistence.entity.JourneyEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;
import java.util.Optional;

@Repository
public class JourneyRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Long create(final JourneyEntity journey){
        if (Objects.isNull(journey)) throw new IllegalArgumentException("journey is null");
    entityManager.persist(journey);
    return journey.getId();
    }

    public Optional<JourneyEntity> findById(Long id) {
       return Optional.ofNullable(entityManager.find(JourneyEntity.class,id));
    }

    public void save(final JourneyEntity journey) {
        entityManager.merge(journey);
    }
}
