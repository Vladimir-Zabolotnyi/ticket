package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "journey")
public class JourneyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "station_from",length = 30,nullable = false)
    private String stationFrom;

    @Column(name = "station_to",length = 30,nullable = false)
    private String stationTo;


}
