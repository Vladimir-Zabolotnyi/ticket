package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "vehicle")
@DynamicUpdate
public class VehicleEntity extends AbstractModifyEntity<Long> {

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "year_of_built", length = 30, nullable = false)
    private Integer yearOfBuilt;

    @Column(name = "country_of_built", length = 30, nullable = false)
    private String countryOfBuilt;

    @OneToMany(mappedBy = "vehicle", cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<SeatEntity> seats = new ArrayList<>();

    public void addSeat(final SeatEntity seat) {
        if (Objects.isNull(seat)) throw new IllegalArgumentException("seat is null");
        if (seats == null) {
            seats = new ArrayList<>();
        }
        seats.add(seat);
        seat.setVehicle(this);
    }

    @OneToMany(mappedBy = "vehicle")
    private List<JourneyEntity> journeys = new ArrayList<>();

    public void addJourney(final JourneyEntity journey) {
        if (Objects.isNull(journey)) throw new IllegalArgumentException("journey is null");
        if (journeys == null) {
            journeys = new ArrayList<>();
        }
        journeys.add(journey);
        journey.addVehicle(this);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", VehicleEntity.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .toString();
    }
}
