package org.hillel.service;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Service(value = "transactionalJourneyService")
public class TransactionalJourneyService {

    @Autowired
    private JourneyRepository journeyRepository;


    @Transactional
    public JourneyEntity createOrUpdateJourney(final JourneyEntity journey) {
        if (Objects.isNull(journey)) throw new IllegalArgumentException("journey is null");
        return journeyRepository.createOrUpdate(journey);
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

    @Transactional
    public void remove(JourneyEntity journey) {
        if (Objects.isNull(journey)) throw new IllegalArgumentException("journey is null");
        journeyRepository.remove(journey);
    }

    @Transactional
    public void removeById(Long journeyId) {
        if (Objects.isNull(journeyId)) throw new IllegalArgumentException("journeyId is null");
        journeyRepository.removeById(journeyId);
    }

    @Transactional(readOnly = true)
    public Collection<JourneyEntity> findAll(){

        Collection<JourneyEntity> allJourneys = journeyRepository.findAll();
        for (JourneyEntity journeyEntity : allJourneys) {
            journeyEntity.getStopsTime().size();
            journeyEntity.getVehicle().getName();
            journeyEntity.getSeats().size();

        }
        return allJourneys;
    }

    @Transactional(readOnly = true)
    public Collection<JourneyEntity> findAllAsNative(){
        Collection<JourneyEntity> allJourneys = journeyRepository.findAllAsNative();
        for (JourneyEntity journeyEntity : allJourneys) {
            journeyEntity.getStopsTime().size();
            journeyEntity.getVehicle().getName();
            journeyEntity.getSeats().size();
        }
        return allJourneys;
    }

    @Transactional(readOnly = true)
    public Collection<JourneyEntity> findAllAsNamed(){
        Collection<JourneyEntity> allJourneys = journeyRepository.findAllAsNamed();
        for (JourneyEntity journeyEntity : allJourneys) {
            journeyEntity.getStopsTime().size();
            journeyEntity.getVehicle().getName();
            journeyEntity.getSeats().size();
        }
        return allJourneys;
    }

    @Transactional(readOnly = true)
    public Collection<JourneyEntity> findAllAsCriteria(){
        Collection<JourneyEntity> allJourneys = journeyRepository.findAllAsCriteria();
        for (JourneyEntity journeyEntity : allJourneys) {
            journeyEntity.getStopsTime().size();
            journeyEntity.getVehicle().getName();
            journeyEntity.getSeats().size();
        }
        return allJourneys;
    }

    @Transactional(readOnly = true)
    public Collection<JourneyEntity> findAllAsStoredProcedure(){
        Collection<JourneyEntity> allJourneys= journeyRepository.findAllAsStoredProcedure();
        for (JourneyEntity journeyEntity : allJourneys) {
            journeyEntity.getStopsTime().size();
            journeyEntity.getVehicle().getName();
            journeyEntity.getSeats().size();
        }
        return allJourneys;
    }
}
