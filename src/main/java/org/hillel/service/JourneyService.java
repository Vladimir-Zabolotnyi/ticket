package org.hillel.service;

import org.hillel.Journey;
import org.hillel.persistence.entity.JourneyEntity;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

public interface JourneyService {


    @Transactional
    JourneyEntity createOrUpdateJourney(JourneyEntity journeyEntity);

    Optional<JourneyEntity> findById(Long id,boolean withDependencies);

    }
