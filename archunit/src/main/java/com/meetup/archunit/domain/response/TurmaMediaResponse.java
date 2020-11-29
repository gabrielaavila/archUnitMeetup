package com.meetup.archunit.domain.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Objeto para resposta da media da turma")
public class TurmaMediaResponse {

    @ApiModelProperty("Aluno com a maior media")
    private AlunoResponse aluno;

    @ApiModelProperty("Media de todas as avaliacoes do aluno na turma")
    private double media;

    public TurmaMediaResponse(AlunoResponse aluno, double media) {
        this.aluno = aluno;
        this.media = media;
    }

    public AlunoResponse getAluno() {
        return aluno;
    }

    public void setAluno(AlunoResponse aluno) {
        this.aluno = aluno;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }
}
