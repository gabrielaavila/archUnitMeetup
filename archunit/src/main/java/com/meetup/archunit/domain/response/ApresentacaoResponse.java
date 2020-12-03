package com.meetup.archunit.domain.response;

import com.meetup.archunit.entity.Aluno;
import com.meetup.archunit.entity.Avaliacao;
import com.meetup.archunit.entity.Turma;
import com.meetup.archunit.entity.TurmaAluno;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("Objeto de resposta para apresentacao")
public class ApresentacaoResponse {

    @ApiModelProperty("Lista de alunos criados")
    private List<Aluno> alunos;

    @ApiModelProperty("Turma criada")
    private Turma turma;

    @ApiModelProperty("Lista de avaliacoes criados")
    private List<Avaliacao> avaliacoes;

    @ApiModelProperty("Lista de relacionamentos Turma - Aluno")
    private List<TurmaAluno> turmaAlunoList;

    public ApresentacaoResponse() {
    }

    public ApresentacaoResponse(List<Aluno> alunos, Turma turma, List<Avaliacao> avaliacoes, List<TurmaAluno> turmaAlunoList) {
        this.alunos = alunos;
        this.turma = turma;
        this.avaliacoes = avaliacoes;
        this.turmaAlunoList = turmaAlunoList;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public List<TurmaAluno> getTurmaAlunoList() {
        return turmaAlunoList;
    }

    public void setTurmaAlunoList(List<TurmaAluno> turmaAlunoList) {
        this.turmaAlunoList = turmaAlunoList;
    }
}
