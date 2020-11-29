package com.meetup.archunit.entity.converters;

import com.meetup.archunit.domain.enums.Tipos;

import javax.persistence.AttributeConverter;

public class TiposConverter implements AttributeConverter<Tipos, String> {
    @Override
    public String convertToDatabaseColumn(Tipos attribute) {
        return attribute.getValue();
    }

    @Override
    public Tipos convertToEntityAttribute(String dbData) {
        return Tipos.getTiposByValue(dbData);
    }
}
