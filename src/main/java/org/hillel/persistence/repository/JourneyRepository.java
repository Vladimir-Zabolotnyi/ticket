package org.hillel.persistence.repository;

import org.hillel.persistence.entity.JourneyEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;
import java.util.Optional;

@Repository
public class JourneyRepository extends CommonRepository<JourneyEntity,Long>{

    protected JourneyRepository() {
        super(JourneyEntity.class);
    }
}
