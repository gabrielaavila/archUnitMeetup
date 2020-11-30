package com.meetup.archunit.mother;

import com.meetup.archunit.domain.request.AlunoRequest;
import com.meetup.archunit.domain.response.AlunoResponse;
import com.meetup.archunit.entity.Aluno;

public class AlunoMother {

    private AlunoMother() {
    }

    public static Aluno getAluno() {
        Aluno aluno = new Aluno("Francisco", 12);
        aluno.setUniqueID(1L);
        return aluno;
    }

    public static AlunoRequest getAlunoRequest() {
        return new AlunoRequest("Francisco", 12);
    }

    public static AlunoResponse getAlunoResponse() {
        return new AlunoResponse(1L, "Francisco", 12);
    }
}
