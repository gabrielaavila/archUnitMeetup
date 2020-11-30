package com.meetup.archunit.mother;

import com.meetup.archunit.domain.dto.AvaliacaoDto;
import com.meetup.archunit.domain.enums.Tipos;
import com.meetup.archunit.domain.response.AlunoResponse;

public class AvaliacaoMother {

    private AvaliacaoMother() {

    }

    public static AvaliacaoDto getAvaliacaoDtoP1(Long uniqueId, Long turmaId, Long alunoId) {
        return getAvaliacaoDto(uniqueId, Tipos.PROVA_1, 8.00, turmaId, alunoId);
    }

    public static AvaliacaoDto getAvaliacaoDtoP2(Long uniqueId, Long turmaId, Long alunoId) {
        return getAvaliacaoDto(uniqueId, Tipos.PROVA_2, 8.00, turmaId, alunoId);
    }

    public static AvaliacaoDto getAvaliacaoDto(Long uniqueId, Tipos tipo, double nota, Long turmaId, Long alunoId) {
        AlunoResponse aluno = new AlunoResponse();
        aluno.setUniqueID(alunoId);
        return new AvaliacaoDto(uniqueId, tipo, nota, turmaId, aluno);
    }
}
