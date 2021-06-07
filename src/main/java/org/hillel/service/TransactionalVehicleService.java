package org.hillel.service;

import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.jpa.repository.SimpleVehicleDto;
import org.hillel.persistence.jpa.repository.VehicleJpaRepository;
import org.hillel.persistence.jpa.repository.specification.VehicleSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TransactionalVehicleService {

    @Autowired
    private VehicleJpaRepository vehicleRepository;

//    @Autowired
//    private TransactionTemplate transactionTemplate;
//
//    @Autowired
//    private PlatformTransactionManager platformTransactionManager;
//
//    @PersistenceContext
//    private EntityManagerFactory entityManagerFactory;

    @Transactional()
    public VehicleEntity createOrUpdateVehicle(final VehicleEntity vehicle) {
        if (Objects.isNull(vehicle)) throw new IllegalArgumentException("vehicle is null");

//        EntityManager em=entityManagerFactory.createEntityManager();
//        final EntityTransaction transaction = em.getTransaction();
//
//        try {
//            transaction.begin();
//            em.persist(vehicle);
//            transaction.commit();
//        } catch (Exception e) {
//            transaction.rollback();
//        }


//        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
//        final TransactionStatus transaction = platformTransactionManager.getTransaction(transactionDefinition);
//        try {vehicleRepository.createOrUpdate(vehicle);
//        platformTransactionManager.commit(transaction);
//        } catch (TransactionException e) {
//            platformTransactionManager.rollback(transaction);
//        }


//        return transactionTemplate.execute((status -> vehicleRepository.createOrUpdate(vehicle)));

        return vehicleRepository.save(vehicle);
    }

    @Transactional
    public void remove(VehicleEntity vehicle) {
        if (Objects.isNull(vehicle)) throw new IllegalArgumentException("vehicle is null");
        vehicleRepository.delete(vehicle);
    }

    @Transactional
    public void removeById(Long vehicleId) {
        if (Objects.isNull(vehicleId)) throw new IllegalArgumentException("vehicleId is null");
        vehicleRepository.deleteById(vehicleId);
    }

    @Transactional()
    public void disableById(Long id) {
        vehicleRepository.disableById(id);
    }


    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllByName(String name) {

        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setName(name);
        vehicleEntity.setActive(true);
        vehicleEntity.setId(2L);
        Example<VehicleEntity> vehicleEntityExample = Example.of(vehicleEntity);

        return vehicleRepository.findAll(VehicleSpecification.byNameAndExample(name, vehicleEntity));
    }


//    @Transactional(readOnly = true)
//    public Collection<VehicleEntity> findAllByCondition(String name) {
//        Page<VehicleEntity> byConditions = vehicleRepository.findByConditions(name,
//                1l,
//                100l,
//                PageRequest.of(1, 3, Sort.by(VehicleEntity_.ID)));
//        return byConditions.getContent();
//    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findByIds(Long... ids) {
        return (Collection<VehicleEntity>) vehicleRepository.findAllById(Arrays.asList(ids));
    }

    @Transactional(readOnly = true)
    public Optional<VehicleEntity> findById(Long id, boolean withDependencies) {
        return vehicleRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAll() {
        return (Collection<VehicleEntity>) vehicleRepository.findAll();
    }

    public List<SimpleVehicleDto> findAllSimpleVehicles(String name) {
            return vehicleRepository.findAllByNameIs( name);
    }

//    @Transactional(readOnly = true)
//    public Collection<VehicleEntity> findAllAsNative(){
//        return vehicleRepository.findAllAsNative();
//    }
//
//    @Transactional(readOnly = true)
//    public Collection<VehicleEntity> findAllAsNamed(){
//        return vehicleRepository.findAllAsNamed();
//    }
//    @Transactional(readOnly = true)
//    public Collection<VehicleEntity> findAllAsCriteria(){
//        return vehicleRepository.findAllAsCriteria();
//    }
//    @Transactional(readOnly = true)
//    public Collection<VehicleEntity> findAllAsStoredProcedure(){
//        return vehicleRepository.findAllAsStoredProcedure();
//    }
//
//    @Transactional(readOnly = true)
//    public Collection<VehicleEntity> findAllUsingPagingSorting(String orderName,boolean ascOrder,int firstRes,int maxRes){
//        return vehicleRepository.findAllUsingPagingSorting(orderName, ascOrder, firstRes, maxRes);
//    }
//
//    @Transactional(readOnly = true)
//    public Collection<VehicleEntity> findAllVehicleWithMaxFreeSeats(){
//        return vehicleRepository.findAllVehicleWithMaxFreeSeats();
//    }
//    @Transactional(readOnly = true)
//    public Collection<VehicleEntity> findAllVehicleWithMinFreeSeats(){
//        return vehicleRepository.findAllVehicleWithMinFreeSeats();
//    }
}
