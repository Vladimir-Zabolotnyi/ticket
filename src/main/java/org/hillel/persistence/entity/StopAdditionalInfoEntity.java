package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.StringJoiner;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "stop_additional_info")
@DynamicUpdate
public class StopAdditionalInfoEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "city")
    private String city;

    @Column(name = "year_of_built")
    private Integer yearOfBuilt;

    @OneToOne
    @MapsId
    @JoinColumn(name = "stop_id")
    private StopEntity stop;

    @Override
    public String toString() {
        return new StringJoiner(", ", StopAdditionalInfoEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("longitude=" + longitude)
                .add("latitude=" + latitude)
                .toString();
    }
}
