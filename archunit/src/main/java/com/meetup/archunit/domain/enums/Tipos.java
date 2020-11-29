package com.meetup.archunit.domain.enums;

import java.util.Map;
import java.util.Optional;

import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public enum Tipos {
    PROVA_1("p1"),
    PROVA_2("p2");

    private static final Map<String, Tipos> LOOKUP_MAP;

    static {
        LOOKUP_MAP = stream(values()).collect(toMap(Tipos::getValue, identity()));
    }

    private String value;

    Tipos(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Tipos getTiposByValue(String value) {
        return Optional.ofNullable(LOOKUP_MAP.get(value))
                .orElseThrow(() -> new IllegalArgumentException("Tipo inexistente"));
    }
}
