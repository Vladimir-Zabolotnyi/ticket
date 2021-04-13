package org.hillel.persistence.repository;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.SeatEntity;
import org.hillel.persistence.entity.StopTimeEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

@Repository
public class SeatRepository extends CommonRepository<SeatEntity, Long> {


    protected SeatRepository() {

        super(SeatEntity.class);
    }

    @Override
    public SeatEntity createOrUpdate(SeatEntity entity) {
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
