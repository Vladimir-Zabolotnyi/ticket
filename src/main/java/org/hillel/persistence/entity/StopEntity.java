package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "stop")
public class StopEntity extends AbstractModifyEntity<Long> {

@Embedded
private CommonInfo commonInfo;

@OneToOne(mappedBy = "stop",cascade = {CascadeType.PERSIST} )
    private StopAdditionalInfoEntity additionalInfo;

@Transient
private boolean applyToJourneyBuild;

public void addStopAdditionalInfo(StopAdditionalInfoEntity stopAdditionalInfo){
    if(stopAdditionalInfo == null){
        this.additionalInfo = null;
        return;
    }
    stopAdditionalInfo.setStop(this);
    this.additionalInfo=stopAdditionalInfo;
}
}
