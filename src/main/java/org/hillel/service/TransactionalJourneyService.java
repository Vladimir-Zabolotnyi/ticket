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
public class TransactionalJourneyService implements JourneyService  {
    @Autowired
    private JourneyRepository journeyRepository;


    @Transactional
    @Override
    public Long createJourney(final JourneyEntity journeyEntity) {
        if (Objects.isNull(journeyEntity)) throw new IllegalArgumentException("journeyEntity is null");
        return journeyRepository.create(journeyEntity);
    }

    @Override
    public Collection<Journey> find(String stationFrom, String stationTo, LocalDateTime departure, LocalDateTime arrival) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<JourneyEntity> getById(Long id, boolean withDependencies) {
        Optional<JourneyEntity> byId = journeyRepository.findById(id);
        if(withDependencies && byId.isPresent()){
            final  JourneyEntity journeyEntity = byId.get();
            journeyEntity.getVehicle().getName();
            journeyEntity.getStops().size();
        }
        return byId;
    }

    @Override
    @Transactional
    public void save(JourneyEntity journey) {
        journeyRepository.save(journey);
    }
}
