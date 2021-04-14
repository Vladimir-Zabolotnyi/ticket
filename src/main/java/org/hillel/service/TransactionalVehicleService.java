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
    public VehicleEntity createOrUpdateVehicle(final VehicleEntity vehicle) {
        if (Objects.isNull(vehicle)) throw new IllegalArgumentException("vehicle is null");
        return vehicleRepository.createOrUpdate(vehicle);
    }

    @Transactional
    public void remove(VehicleEntity vehicle) {
        if (Objects.isNull(vehicle)) throw new IllegalArgumentException("vehicle is null");
        vehicleRepository.remove(vehicle);
    }

    @Transactional
    public void removeById(Long vehicleId) {
        if (Objects.isNull(vehicleId)) throw new IllegalArgumentException("vehicleId is null");
        vehicleRepository.removeById(vehicleId);
    }
}
