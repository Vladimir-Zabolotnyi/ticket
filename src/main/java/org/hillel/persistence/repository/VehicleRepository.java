package org.hillel.persistence.repository;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Objects;

@Repository
public class VehicleRepository extends CommonRepository<VehicleEntity, Long> {


    protected VehicleRepository() {
        super(VehicleEntity.class);
    }

    @Override
    public void remove(VehicleEntity entity) {
        if (Objects.isNull(entity)) throw new IllegalArgumentException("entity is null");
        entity = findById(entity.getId()).get();
        entity.removeAllJourney();
        super.remove(entity);
    }

    @Override
    public Collection<VehicleEntity> findAll() {
      return null;
    }
}
