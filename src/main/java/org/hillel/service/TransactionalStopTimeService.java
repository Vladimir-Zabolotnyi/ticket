package org.hillel.service;

import java.util.Collection;
import java.util.Objects;
import org.hillel.persistence.entity.StopTimeEntity;
import org.hillel.persistence.repository.StopTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionalStopTimeService {

    @Autowired
    private StopTimeRepository stopTimeRepository;


    @Transactional
    public StopTimeEntity createOrUpdateStopTime(final StopTimeEntity stopTime) {
        if (Objects.isNull(stopTime)) throw new IllegalArgumentException("stopTime is null");
        return stopTimeRepository.createOrUpdate(stopTime);
    }

    @Transactional
    public void remove(StopTimeEntity stopTime) {
        if (Objects.isNull(stopTime)) throw new IllegalArgumentException("stopTime is null");
        stopTimeRepository.remove(stopTime);
    }

    @Transactional
    public void removeById(Long stopTimeId) {
        if (Objects.isNull(stopTimeId)) throw new IllegalArgumentException("stopTimeId is null");
        stopTimeRepository.removeById(stopTimeId);
    }

    @Transactional(readOnly = true)
    public Collection<StopTimeEntity> findAll() {
        return stopTimeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Collection<StopTimeEntity> findAllAsNative() {
        return stopTimeRepository.findAllAsNative();
    }

    @Transactional(readOnly = true)
    public Collection<StopTimeEntity> findAllAsNamed() {
        return stopTimeRepository.findAllAsNamed();
    }

    @Transactional(readOnly = true)
    public Collection<StopTimeEntity> findAllAsCriteria() {
        return stopTimeRepository.findAllAsCriteria();
    }

    @Transactional(readOnly = true)
    public Collection<StopTimeEntity> findAllAsStoredProcedure() {
        return stopTimeRepository.findAllAsStoredProcedure();
    }
}
