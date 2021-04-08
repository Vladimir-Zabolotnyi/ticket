package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "seat")

public class SeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade ={CascadeType.PERSIST})
    @JoinColumn(name = "journey_id")
    private JourneyEntity journey;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;

    @Column(name = "seat_free")
    @Type(type = "yes_no")
    private boolean seatFree = true;

    @Column(name= "carriage_number")
    private String carriageNumber;

    @Column(name="seat_number")
    private String seatNumber;

    @Override
    public String toString() {
        return new StringJoiner(", ", SeatEntity.class.getSimpleName() + "[", "]")
                .add("seatFree=" + seatFree)
                .add("carriageNumber='" + carriageNumber + "'")
                .add("seatNumber='" + seatNumber + "'")
                .toString();
    }
}
