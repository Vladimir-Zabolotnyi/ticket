package org.hillel.persistence.entity;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hillel.persistence.entity.util.YesNoConverter;

@Getter
@Setter
@MappedSuperclass

public abstract class AbstractModifyEntity<ID> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    @Column(name = "create_date")
    @CreationTimestamp
    private Instant createdDate;

    @Column(name = "active")
//    @Type(type = "yes_no")
    @Convert(converter = YesNoConverter.class)
    private boolean active = true;


}
