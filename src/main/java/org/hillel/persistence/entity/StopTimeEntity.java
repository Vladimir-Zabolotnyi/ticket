package org.hillel.persistence.entity;

import java.time.Instant;
import java.util.StringJoiner;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "stop_time")
@DynamicUpdate

@NamedQueries(value = {
        @NamedQuery(name = "findAllAsNamedStopTimeEntity", query = "from StopTimeEntity")
})
public class StopTimeEntity extends AbstractModifyEntity<Long> {

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "journey_id")
    private JourneyEntity journey;

    @ManyToOne()
    @JoinColumn(name = "stop_id")
    private StopEntity stop;

    @Column(name = "arrival_time")
    private Instant arrivalTime;

    @Column(name = "departure_time")
    private Instant departureTime;

    @Column(name = "stop_sequence")
    private Integer stopSequence;

    @Override
    public String toString() {
        return new StringJoiner(", ", StopTimeEntity.class.getSimpleName() + "[", "]")
                .add("journey=" + journey.getStationFrom() + "-" + journey.getStationTo())
                .add("stop=" + stop.getCommonInfo())
                .add("arrivalTime=" + arrivalTime)
                .add("departureTime=" + departureTime)
                .add("stopSequence=" + stopSequence)
                .toString();
    }


}
