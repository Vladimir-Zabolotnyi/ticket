package org.hillel.service;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.SeatEntity;
import org.hillel.persistence.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class TransactionalSeatService {

    @Autowired
    private SeatRepository seatRepository;


    @Transactional
    public SeatEntity createOrUpdateSeat(final SeatEntity seat) {
        if (Objects.isNull(seat)) throw new IllegalArgumentException("seat is null");
        return seatRepository.createOrUpdate(seat);
    }


}