package com.meetup.archunit.domain.converter;

import com.meetup.archunit.domain.request.AlunoRequest;
import com.meetup.archunit.domain.response.AlunoResponse;
import com.meetup.archunit.entity.Aluno;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class AlunoConverter {

    public static Aluno toEntity(AlunoRequest request) {
        if (isNull(request)) {
            return new Aluno();
        }
        return new Aluno(request.getNome(), request.getIdade());
    }

    public static AlunoResponse toResponse(Aluno aluno) {
        if (isNull(aluno)) {
            return new AlunoResponse();
        }
        return new AlunoResponse(aluno.getUniqueID(), aluno.getNome(), aluno.getIdade());
    }


    public static List<AlunoResponse> toResponseList(List<Aluno> retrievedAluno) {
        if (isNull(retrievedAluno)) {
            return Collections.emptyList();
        }
        return retrievedAluno.stream()
                .map(AlunoConverter::toResponse)
                .collect(Collectors.toList());
    }
}
