package org.hillel.persistence.repository;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopAdditionalInfoEntity;
import org.hillel.persistence.entity.StopEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

@Repository
public class StopAdditionalInfoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Long create(final StopAdditionalInfoEntity stopAdditionalInfo) {
        if (Objects.isNull(stopAdditionalInfo)) throw new IllegalArgumentException("stopAdditionalInfo is null");
        entityManager.persist(stopAdditionalInfo);
        return stopAdditionalInfo.getId();
    }

    public void save(final StopAdditionalInfoEntity stopAdditionalInfo) {
        entityManager.merge(stopAdditionalInfo);
    }
}
