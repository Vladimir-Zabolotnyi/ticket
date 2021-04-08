package org.hillel.persistence.repository;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.SeatEntity;
import org.hillel.persistence.entity.StopTimeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

@Repository
public class SeatRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Long create(final SeatEntity seat) {
        if (Objects.isNull(seat)) throw new IllegalArgumentException("seat is null");
        entityManager.persist(seat);
        return seat.getId();
    }

    public void save(final SeatEntity seat) {
        entityManager.merge(seat);
    }
}
