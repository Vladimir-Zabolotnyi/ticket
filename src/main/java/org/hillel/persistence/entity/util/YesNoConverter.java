package org.hillel.persistence.entity.util;

import java.util.Objects;
import javax.persistence.AttributeConverter;
import org.springframework.util.StringUtils;

//@Converter(autoApply = true)
public class YesNoConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean entityValue) {
        return YesNoType.getEntityValue(entityValue).dbValue;
    }

    @Override
    public Boolean convertToEntityAttribute(String dbValue) {
        return YesNoType.getByDBValue(dbValue).entityValue;
    }

    private enum YesNoType {
        YES("yes", true), NO("no", false);
        private final String dbValue;
        private final boolean entityValue;

        YesNoType(String dbValue, boolean entityValue) {
            this.dbValue = dbValue;
            this.entityValue = entityValue;
        }

        private static final YesNoType getByDBValue(String value) {
            if (StringUtils.isEmpty(value)) return NO;
            for (YesNoType type : values()) {
                if (Objects.equals(type.dbValue, value)) return type;
            }
            return NO;
        }

        private static final YesNoType getEntityValue(Boolean value) {
            if (Objects.isNull(value)) return NO;
            for (YesNoType type : values()) {
                if (Objects.equals(type.entityValue, value)) return type;
            }
            return NO;
        }
    }
}
