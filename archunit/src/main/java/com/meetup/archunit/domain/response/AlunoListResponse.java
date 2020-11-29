package com.meetup.archunit.domain.response;

import com.meetup.archunit.entity.Aluno;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("Objeto de resposta para lista de alunos")
public class AlunoListResponse {

    @ApiModelProperty(value = "lista de alunos")
    private List<AlunoResponse> alunos;

    public AlunoListResponse(List<AlunoResponse> alunos) {
        this.alunos = alunos;
    }

    public List<AlunoResponse> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<AlunoResponse> alunos) {
        this.alunos = alunos;
    }
}
