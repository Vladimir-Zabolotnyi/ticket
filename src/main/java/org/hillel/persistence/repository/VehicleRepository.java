package org.hillel.persistence.repository;

import org.hillel.persistence.entity.VehicleEntity;
import org.springframework.stereotype.Repository;

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
    public Collection<VehicleEntity> findByName(String name) {
//        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        final CriteriaQuery<VehicleEntity> query = criteriaBuilder.createQuery(VehicleEntity.class);
//        final Root<VehicleEntity> from = query.from(VehicleEntity.class);
//        final Join<Object, Object> journeys = from.join(VehicleEntity_.JOURNEYS, JoinType.LEFT);
//        final Predicate byJourneyStationFrom = criteriaBuilder.equal(journeys.get(JourneyEntity_.STATION_FROM), criteriaBuilder.parameter(String.class,"stationFromParam"));
//        journeys.on(byJourneyStationFrom);
//        final Predicate byName = criteriaBuilder.equal(from.get(VehicleEntity_.NAME), criteriaBuilder.parameter(String.class,"nameParam"));
//
//        final Predicate active = criteriaBuilder.equal(from.get(VehicleEntity_.ACTIVE), criteriaBuilder.parameter(Boolean.class,"activeParam"));
//        return entityManager.createQuery(query
//                .select(from)
//                .where(byName,active)
//                .orderBy(new OrderImpl(from.get(VehicleEntity_.ID),false)))
//                .setParameter("nameParam",name)
//                .setParameter("activeParam",true)
//                .setParameter("stationFromParam","Kiev")
//                .setFirstResult(0)
//                .setMaxResults(5)
//                .getResultList();

        return entityManager
                .createQuery("select v from VehicleEntity v left join v.journeys js on js.vehicle.id = v.id order by v.id asc",VehicleEntity.class)
                .setFirstResult(0)
                .setMaxResults(3)
                .getResultList();
    }

    public Collection<VehicleEntity> findAllVehicleWithMaxFreeSeats() {
       return entityManager.createNativeQuery("select  distinct v.* from " +
               "vehicle v inner join seat s on v.id = s.vehicle_id " +
               "inner join journey j on s.journey_id = j.id" +
               " where seat_free = 'Y' " +
               " group by v.id,j.id " +
               "having count(seat_free) = " +
               "(select max(count_f) from " +
               "(select vehicle_id,journey_id, count(seat_free) count_f from seat" +
               " where seat_free = 'Y'" +
               " group by vehicle_id,journey_id) foo) ;",VehicleEntity.class).getResultList();
    }

    public Collection<VehicleEntity> findAllVehicleWithMinFreeSeats() {
        return entityManager.createNativeQuery("select  distinct v.* from " +
                "vehicle v inner join seat s on v.id = s.vehicle_id " +
                "inner join journey j on s.journey_id = j.id" +
                " where seat_free = 'Y' " +
                " group by v.id,j.id " +
                "having count(seat_free) = " +
                "(select min(count_f) from " +
                "(select vehicle_id,journey_id, count(seat_free) count_f from seat" +
                " where seat_free = 'Y'" +
                " group by vehicle_id,journey_id) foo) ;",VehicleEntity.class).getResultList();
    }
}
