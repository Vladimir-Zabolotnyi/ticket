package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class VehicleEntity extends AbstractModifyEntity<Long> {
//   @Embedded
//    private CommonInfo commonInfo;

    @Column(name = "name",length = 30,nullable = false)
    private String name;

    @OneToMany(mappedBy = "vehicle")
    private List<JourneyEntity> journeys = new ArrayList<>();

    public void addJourney(final JourneyEntity journey){
        if (Objects.isNull(journey)) throw new IllegalArgumentException("journey is null");
        if (journeys == null){
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
