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
        Aluno aluno = new Aluno();
        aluno.setNome(request.getNome());
        aluno.setIdade(request.getIdade());
        return aluno;
    }

    public static AlunoResponse toResponse(Aluno aluno) {
        if (isNull(aluno)) {
            return new AlunoResponse();
        }
        AlunoResponse response = new AlunoResponse();
        response.setUniqueID(aluno.getUniqueID());
        response.setNome(aluno.getNome());
        response.setIdade(aluno.getIdade());
        return response;
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
