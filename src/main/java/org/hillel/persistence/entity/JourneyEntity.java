package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hillel.persistence.entity.enums.DirectionType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "journey")
public class JourneyEntity extends AbstractModifyEntity<Long> {

    @Column(name = "station_from",length = 30,nullable = false)
    private String stationFrom;

    @Column(name = "station_to",length = 30,nullable = false)
    private String stationTo;

    @Column(name = "date_from",nullable = false)
//    @Temporal(TemporalType.DATE)
    private Instant dateFrom;

    @Column(name = "date_to",nullable = false)
//    @Temporal(TemporalType.DATE)
    private Instant dateTo;

    @Column(name = "direction", length = 30)
    @Enumerated(EnumType.STRING)
    private DirectionType direction=DirectionType.TO;

}
