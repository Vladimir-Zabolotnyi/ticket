package org.hillel.persistence.repository;

import java.util.Objects;
import org.hillel.persistence.entity.VehicleEntity;
import org.springframework.stereotype.Repository;

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

}
