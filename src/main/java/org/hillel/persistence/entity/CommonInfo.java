package org.hillel.persistence.entity;

import java.util.StringJoiner;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class CommonInfo {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 10000)
    private String description;

    @Override
    public String toString() {
        return new StringJoiner(", ", CommonInfo.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("description='" + description + "'")
                .toString();
    }
}
