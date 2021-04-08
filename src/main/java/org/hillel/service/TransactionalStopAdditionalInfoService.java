package org.hillel.service;

import org.hillel.persistence.entity.SeatEntity;
import org.hillel.persistence.entity.StopAdditionalInfoEntity;
import org.hillel.persistence.repository.SeatRepository;
import org.hillel.persistence.repository.StopAdditionalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class TransactionalStopAdditionalInfoService {
    @Autowired
    private StopAdditionalInfoRepository stopAdditionalInfoRepository;


    @Transactional
    public Long createStopAdditionalInfo(final StopAdditionalInfoEntity stopAdditionalInfo) {
        if (Objects.isNull(stopAdditionalInfo)) throw new IllegalArgumentException("stopAdditionalInfo is null");
        return stopAdditionalInfoRepository.create(stopAdditionalInfo);
    }

    @Transactional
    public void save(final StopAdditionalInfoEntity stopAdditionalInfo) {
        if (Objects.isNull(stopAdditionalInfo)) throw new IllegalArgumentException("stopAdditionalInfo is null");
        stopAdditionalInfoRepository.save(stopAdditionalInfo);
    }
}
