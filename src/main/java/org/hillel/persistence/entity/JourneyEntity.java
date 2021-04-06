package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hillel.persistence.entity.enums.DirectionType;

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
@Table(name = "journey")
@DynamicUpdate
public class JourneyEntity extends AbstractModifyEntity<Long> {


    @Column(name = "station_from", length = 30, nullable = false)
    private String stationFrom;

    @Column(name = "station_to", length = 30, nullable = false)
    private String stationTo;

    @Column(name = "date_from", nullable = false)
//    @Temporal(TemporalType.DATE)
    private Instant dateFrom;

    @Column(name = "date_to", nullable = false)
//    @Temporal(TemporalType.DATE)
    private Instant dateTo;

    @Column(name = "direction", length = 30)
    @Enumerated(EnumType.STRING)
    private DirectionType direction = DirectionType.TO;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;

    public void addVehicle(final VehicleEntity vehicle) {
        if (Objects.isNull(vehicle)) throw new IllegalArgumentException("vehicle is null");
        this.vehicle = vehicle;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "journey_stop", /*indexes = @Index(name = "journey_stop",columnList = "journey_id,stop_id"),*/
            joinColumns = @JoinColumn(name = "journey_id"),
            inverseJoinColumns = @JoinColumn(name = "stop_id")
    )
    private List<StopEntity> stops = new ArrayList<>();

    public void addStop(final StopEntity stop) {
        if (Objects.isNull(stop)) return;
        if (stops == null) {
            stops = new ArrayList<>();
        }
        stops.add(stop);
        stop.addJourney(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JourneyEntity entity = (JourneyEntity) o;
        return getId() != null && Objects.equals(getId(), entity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationFrom, stationTo, dateFrom, dateTo, direction, vehicle);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", JourneyEntity.class.getSimpleName() + "[", "]")
                .add("stationFrom='" + stationFrom + "'")
                .add("stationTo='" + stationTo + "'")
                .add("dateFrom=" + dateFrom)
                .add("dateTo=" + dateTo)
                .add("direction=" + direction)
                .add("vehicle=" + vehicle)
                .toString();
    }
}
