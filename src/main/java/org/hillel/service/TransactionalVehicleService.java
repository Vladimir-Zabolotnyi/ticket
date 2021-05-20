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
        return  vehicleRepository.findByName(name);
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

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllUsingPagingSorting(String orderName,boolean ascOrder,int firstRes,int maxRes){
        return vehicleRepository.findAllUsingPagingSorting(orderName, ascOrder, firstRes, maxRes);
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllVehicleWithMaxFreeSeats(){
       return vehicleRepository.findAllVehicleWithMaxFreeSeats();
    }
    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllVehicleWithMinFreeSeats(){
        return vehicleRepository.findAllVehicleWithMinFreeSeats();
    }
}
