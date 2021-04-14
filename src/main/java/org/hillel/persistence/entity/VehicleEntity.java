package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "vehicle")
@DynamicUpdate
@DynamicInsert
public class VehicleEntity extends AbstractModifyEntity<Long> {

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "year_of_built", length = 30, nullable = false)
    private Integer yearOfBuilt;

    @Column(name = "country_of_built", length = 30, nullable = false)
    private String countryOfBuilt;

    @OneToMany(mappedBy = "vehicle", cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY, orphanRemoval = true)
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

    public void removeAllJourney() {
        if ((CollectionUtils.isEmpty(journeys))) return;
        journeys.forEach(item -> item.setVehicle((null)));

    }

    @Override
    public String toString() {
        return new StringJoiner(", ", VehicleEntity.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("yearOfBuilt=" + yearOfBuilt)
                .add("countryOfBuilt='" + countryOfBuilt + "'")
                .toString();
    }
}
