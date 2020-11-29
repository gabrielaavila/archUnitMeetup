package com.meetup.archunit.domain.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Objeto para resposta da media da turma")
public class TurmaMediaResponse {

    @ApiModelProperty("Aluno com a maior media")
    private AlunoResponse aluno;

    public TurmaMediaResponse(AlunoResponse alunoResponse){
        this.aluno = alunoResponse;
    }

    public AlunoResponse getAluno() {
        return aluno;
    }

    public void setAluno(AlunoResponse aluno) {
        this.aluno = aluno;
    }
}
