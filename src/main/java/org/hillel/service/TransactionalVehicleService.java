package org.hillel.service;

import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

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

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllByName(String name){
        return  vehicleRepository.findAllByName(name);
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findByIds(Long... ids) {
        return vehicleRepository.findByIds(ids);
    }

    @Transactional(readOnly = true)
    public Optional<VehicleEntity> findById(Long id, boolean withDependencies) {
        return vehicleRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public  Collection<VehicleEntity> findAll(){
        return vehicleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllAsNative(){
        return vehicleRepository.findAllAsNative();
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllAsNamed(){
        return vehicleRepository.findAllAsNamed();
    }
    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllAsCriteria(){
        return vehicleRepository.findAllAsCriteria();
    }
    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllAsStoredProcedure(){
        return vehicleRepository.findAllAsStoredProcedure();
    }
}
