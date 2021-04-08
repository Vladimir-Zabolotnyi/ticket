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
@Table(name = "stop")

public class StopEntity extends AbstractModifyEntity<Long> {

    @Embedded
    private CommonInfo commonInfo;

    @Transient
    private boolean applyToJourneyBuild;

    @OneToOne(mappedBy = "stop", cascade = {CascadeType.PERSIST})
    private StopAdditionalInfoEntity additionalInfo;

    public void addStopAdditionalInfo(StopAdditionalInfoEntity stopAdditionalInfo) {
        if (stopAdditionalInfo == null) {
            this.additionalInfo = null;
            return;
        }
        stopAdditionalInfo.setStop(this);
        this.additionalInfo = stopAdditionalInfo;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", StopEntity.class.getSimpleName() + "[", "]")
                .add("commonInfo=" + commonInfo)
                .add("additionalInfo=" + additionalInfo)
                .toString();
    }
}
