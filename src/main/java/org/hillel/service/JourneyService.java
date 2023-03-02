package org.hillel.service;

import java.util.Optional;
import javax.transaction.Transactional;
import org.hillel.persistence.entity.JourneyEntity;

public interface JourneyService {


    @Transactional
    JourneyEntity createOrUpdateJourney(JourneyEntity journeyEntity);

    Optional<JourneyEntity> findById(Long id, boolean withDependencies);

}
