package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "vehicle")
public class VehicleEntity extends AbstractModifyEntity<Long> {
   @Embedded
    private CommonInfo commonInfo;
}
