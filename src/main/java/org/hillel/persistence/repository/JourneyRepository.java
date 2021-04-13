package org.hillel.persistence.repository;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;
import java.util.Optional;

@Repository
public class JourneyRepository extends CommonRepository<JourneyEntity, Long> {

    protected JourneyRepository() {
        super(JourneyEntity.class);
    }


    @Override
    public JourneyEntity createOrUpdate(JourneyEntity entity) {
        if (Objects.isNull(entity)) throw new IllegalArgumentException("entity is null");
        VehicleEntity vehicle = entity.getVehicle();
        if (Objects.nonNull(vehicle)) {
            if (!entityManager.contains(vehicle)) {
                entity.setVehicle(entityManager.merge(vehicle));
            }
        }
        return super.createOrUpdate(entity);

    }
}
