package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "stop")
@DynamicUpdate
@DynamicInsert

@NamedQueries(value = {
        @NamedQuery(name = "findAllAsNamedStopEntity", query = "from StopEntity")
})
public class StopEntity extends AbstractModifyEntity<Long> {

    @Embedded
    private CommonInfo commonInfo;

    @Transient
    private boolean applyToJourneyBuild;

    @OneToOne(mappedBy = "stop", cascade = {CascadeType.PERSIST}, orphanRemoval = true)
    private StopAdditionalInfoEntity additionalInfo;

    public void addStopAdditionalInfo(StopAdditionalInfoEntity stopAdditionalInfo) {
        if (stopAdditionalInfo == null) {
            this.additionalInfo = null;
            return;
        }
        stopAdditionalInfo.setStop(this);
        this.additionalInfo = stopAdditionalInfo;
    }

    @OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY, mappedBy = "stop", orphanRemoval = true)
    private List<StopTimeEntity> stopsTime = new ArrayList<>();

    @Override
    public String toString() {
        return new StringJoiner(", ", StopEntity.class.getSimpleName() + "[", "]")
                .add("commonInfo=" + commonInfo)
                .add("additionalInfo=" + additionalInfo)
                .toString();
    }
}
