package org.hillel.service;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.repository.StopRepository;
import org.hillel.persistence.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.Objects;

@Service
public class TransactionalVehicleService {
    @Autowired
   private VehicleRepository vehicleRepository;


    @Transactional
    public VehicleEntity createOrUodateVehicle(final VehicleEntity vehicle) {
        if (Objects.isNull(vehicle)) throw new IllegalArgumentException("vehicle is null");
        return vehicleRepository.createOrUpdate(vehicle);
    }

}
