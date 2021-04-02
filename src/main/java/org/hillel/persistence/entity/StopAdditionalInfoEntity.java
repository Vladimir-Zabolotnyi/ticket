package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "stop_additional_info")
public class StopAdditionalInfoEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "longitude",nullable = false)
    private double longitude;

    @Column(name = "latitude",nullable = false)
    private double latitude;

    @OneToOne
    @MapsId
    @JoinColumn(name="stop_id")
    private StopEntity stop;
}
