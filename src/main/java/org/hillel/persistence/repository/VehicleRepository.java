package org.hillel.persistence.repository;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

@Repository
public class VehicleRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Long create(final VehicleEntity vehicle) {
        if (Objects.isNull(vehicle)) throw new IllegalArgumentException("vehicle is null");
        entityManager.persist(vehicle);
        return vehicle.getId();
    }

    public void save(final VehicleEntity vehicle) {
        entityManager.merge(vehicle);
    }
}
