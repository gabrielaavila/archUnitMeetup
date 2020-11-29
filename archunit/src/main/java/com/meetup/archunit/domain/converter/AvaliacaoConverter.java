package com.meetup.archunit.domain.converter;

import com.meetup.archunit.domain.dto.AvaliacaoDto;
import com.meetup.archunit.entity.Avaliacao;

import static com.meetup.archunit.domain.converter.AlunoConverter.toResponse;

public class AvaliacaoConverter {

    public static AvaliacaoDto toDto(Avaliacao entity) {
        return new AvaliacaoDto(entity.getUniqueID(), entity.getTipo(), entity.getNota(),
                entity.getTurma().getUniqueID(), toResponse(entity.getAluno()));
    }
}
