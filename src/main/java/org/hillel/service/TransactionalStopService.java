package org.hillel.service;

import java.util.Collection;
import java.util.Objects;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionalStopService {
    @Autowired
    private StopRepository stopRepository;

    @Transactional
    public StopEntity createOrUpdateStop(final StopEntity stop) {
        if (Objects.isNull(stop)) throw new IllegalArgumentException("stop is null");
        return stopRepository.createOrUpdate(stop);
    }

    @Transactional
    public void remove(StopEntity stop) {
        if (Objects.isNull(stop)) throw new IllegalArgumentException("stop is null");
        stopRepository.remove(stop);
    }

    @Transactional
    public void removeByID(Long stopId) {
        if (Objects.isNull(stopId)) throw new IllegalArgumentException("stopId is null");
        stopRepository.removeById(stopId);
    }

    @Transactional(readOnly = true)
    public Collection<StopEntity> findAllByName(String name) {
        return stopRepository.findAllByName(name);
    }

    @Transactional(readOnly = true)
    public Collection<StopEntity> findAll() {
        return stopRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Collection<StopEntity> findAllAsNative() {
        return stopRepository.findAllAsNative();
    }

    @Transactional(readOnly = true)
    public Collection<StopEntity> findAllAsNamed() {
        return stopRepository.findAllAsNamed();
    }

    @Transactional(readOnly = true)
    public Collection<StopEntity> findAllAsCriteria() {
        return stopRepository.findAllAsCriteria();
    }

    @Transactional(readOnly = true)
    public Collection<StopEntity> findAllAsStoredProcedure() {
        return stopRepository.findAllAsStoredProcedure();
    }
}

