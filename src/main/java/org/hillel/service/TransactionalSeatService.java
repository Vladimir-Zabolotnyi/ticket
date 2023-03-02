package org.hillel.service;

import java.util.Collection;
import java.util.Objects;
import org.hillel.persistence.entity.SeatEntity;
import org.hillel.persistence.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionalSeatService {

    @Autowired
    private SeatRepository seatRepository;


    @Transactional
    public SeatEntity createOrUpdateSeat(final SeatEntity seat) {
        if (Objects.isNull(seat)) throw new IllegalArgumentException("seat is null");
        return seatRepository.createOrUpdate(seat);
    }

    @Transactional
    public void remove(SeatEntity seat) {
        if (Objects.isNull(seat)) throw new IllegalArgumentException("seat is null");
        seatRepository.remove(seat);
    }

    @Transactional
    public void removeById(Long seatId) {
        if (Objects.isNull(seatId)) throw new IllegalArgumentException("seatId is null");
        seatRepository.removeById(seatId);
    }

    @Transactional(readOnly = true)
    public Collection<SeatEntity> findAll() {
        return seatRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Collection<SeatEntity> findAllAsNative() {
        return seatRepository.findAllAsNative();
    }

    @Transactional(readOnly = true)
    public Collection<SeatEntity> findAllAsNamed() {
        return seatRepository.findAllAsNamed();
    }

    @Transactional(readOnly = true)
    public Collection<SeatEntity> findAllAsCriteria() {
        return seatRepository.findAllAsCriteria();
    }

    @Transactional(readOnly = true)
    public Collection<SeatEntity> findAllAsStoredProcedure() {
        return seatRepository.findAllAsStoredProcedure();
    }
}
