package org.hillel.persistence.jpa.repository;

import org.hillel.persistence.entity.VehicleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleJpaRepository extends CommonJpaRepository<VehicleEntity, Long>, JpaSpecificationExecutor<VehicleEntity> {


    @Query(value = "select v from VehicleEntity v where v.id between :id_from and :id_to and v.name = :name")
//    @Query(value = "select v.* from vehicle v where v.id between :id_from and :id_to and v.name = :name",countQuery = "",nativeQuery = true)
        /*List;Slice*/ Page<VehicleEntity> findByConditions(@Param("name") String name,
                                                            @Param("id_from") Long idFrom,
                                                            @Param("id_to") Long idTo,
                                                            Pageable page);

    List<SimpleVehicleDto> findAllByNameIs(String name);
}
