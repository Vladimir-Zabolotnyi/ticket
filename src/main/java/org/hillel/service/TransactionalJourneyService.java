package org.hillel.service;

import org.hillel.Journey;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Service(value = "transactionalJourneyService")
public class TransactionalJourneyService {
    @Autowired
    private JourneyRepository journeyRepository;


    @Transactional
    public JourneyEntity createOrUpdateJourney(final JourneyEntity entity) {
        if (Objects.isNull(entity)) throw new IllegalArgumentException("entity is null");
        return journeyRepository.createOrUpdate(entity);
    }


    @Transactional(readOnly = true)
    public Optional<JourneyEntity> findById(Long id, boolean withDependencies) {
        Optional<JourneyEntity> byId = journeyRepository.findById(id);
        if (withDependencies && byId.isPresent()) {
            final JourneyEntity journeyEntity = byId.get();
            journeyEntity.getVehicle().getName();
            journeyEntity.getStopsTime().size();
            journeyEntity.getSeats().size();
        }
        return byId;
    }

}
