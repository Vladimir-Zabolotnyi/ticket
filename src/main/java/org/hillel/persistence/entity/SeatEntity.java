package org.hillel.persistence.entity;

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
import org.hibernate.annotations.Type;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "seat")
@DynamicUpdate

@NamedQueries(value = {
        @NamedQuery(name = "findAllAsNamedSeatEntity", query = "from SeatEntity")
})
public class SeatEntity extends AbstractModifyEntity<Long> {

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "journey_id")
    private JourneyEntity journey;

    @ManyToOne()
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;

    @Column(name = "seat_free")
    @Type(type = "yes_no")
    private boolean seatFree = true;

    @Column(name = "carriage_number")
    private String carriageNumber;

    @Column(name = "seat_number")
    private String seatNumber;

    @Override
    public String toString() {
        return new StringJoiner(", ", SeatEntity.class.getSimpleName() + "[", "]")
                .add("seatFree=" + seatFree)  //Show only free-true(look in JourneyEntity)
                .add("carriageNumber='" + carriageNumber + "'")
                .add("seatNumber='" + seatNumber + "'")
                .toString();
    }
}
