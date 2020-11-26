package com.meetup.archunit.mother;

import com.meetup.archunit.domain.request.AlunoRequest;
import com.meetup.archunit.entity.Aluno;

public class AlunoMother {

    private AlunoMother() {
    }

    public static Aluno getAluno() {
        Aluno aluno = new Aluno();
        aluno.setUniqueID(1L);
        aluno.setNome("Francisco");
        aluno.setIdade(12);
        return aluno;
    }

    public static AlunoRequest getAlunoRequest() {
        AlunoRequest aluno = new AlunoRequest();
        aluno.setNome("Francisco");
        aluno.setIdade(12);
        return aluno;
    }

}
