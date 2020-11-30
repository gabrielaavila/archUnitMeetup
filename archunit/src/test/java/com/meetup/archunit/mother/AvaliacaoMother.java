package com.meetup.archunit.mother;

import com.meetup.archunit.domain.dto.AvaliacaoDto;
import com.meetup.archunit.domain.enums.Tipos;
import com.meetup.archunit.domain.response.AlunoResponse;
import com.meetup.archunit.entity.Avaliacao;
import com.meetup.archunit.entity.Turma;

import static com.meetup.archunit.mother.AlunoMother.getAluno;

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

    public static AvaliacaoDto getAvaliacaoDto(Tipos tipo, AlunoResponse alunoResponse) {
        return new AvaliacaoDto(null, tipo, 10.0, null, alunoResponse);
    }
    public static Avaliacao getAvaliacao() {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setUniqueID(1L);
        avaliacao.setTipo(Tipos.PROVA_1);
        avaliacao.setNota(10.0);
        avaliacao.setAluno(getAluno());
        avaliacao.setTurma(new Turma(7L, "turma1", "Alexandre"));
        return avaliacao;
    }



}
