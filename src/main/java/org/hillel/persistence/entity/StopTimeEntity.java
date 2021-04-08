package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.StringJoiner;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "stop_time")
public class StopTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "journey_id")
    private JourneyEntity journey;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "stop_id")
    private StopEntity stop;

    @Column(name = "arrival_time")
    private Instant arrivalTime;

    @Column(name = "departure_time")
    private Instant departureTime;

    @Column(name = "stop_sequence")
    private int stopSequence;

    @Override
    public String toString() {
        return new StringJoiner(", ", StopTimeEntity.class.getSimpleName() + "[", "]")
                .add("journey=" + journey.getStationFrom() +"-"+ journey.getStationTo())
                .add("stop=" + stop.getCommonInfo())
                .add("arrivalTime=" + arrivalTime)
                .add("departureTime=" + departureTime)
                .add("stopSequence=" + stopSequence)
                .toString();
    }


}
