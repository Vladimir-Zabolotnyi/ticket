package org.hillel.service.notInUse;

import org.hillel.persistence.entity.JourneyEntity;

import javax.transaction.Transactional;
import java.util.Optional;

public interface JourneyService {


    @Transactional
    JourneyEntity createOrUpdateJourney(JourneyEntity journeyEntity);

    Optional<JourneyEntity> findById(Long id, boolean withDependencies);

}
