package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hillel.persistence.entity.util.YesNoConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

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
