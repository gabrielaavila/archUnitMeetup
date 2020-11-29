package com.meetup.archunit.domain.dto;

import com.meetup.archunit.domain.enums.Tipos;
import com.meetup.archunit.domain.response.AlunoResponse;

public class AvaliacaoDto {

    private Long uniqueID;
    private Tipos tipo;
    private double nota;
    private Long turmaId;
    private AlunoResponse aluno;

    public AvaliacaoDto(Long uniqueID, Tipos tipo, double nota, Long turmaId, AlunoResponse aluno) {
        this.uniqueID = uniqueID;
        this.tipo = tipo;
        this.nota = nota;
        this.turmaId = turmaId;
        this.aluno = aluno;
    }

    public Long getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(Long uniqueID) {
        this.uniqueID = uniqueID;
    }

    public Tipos getTipo() {
        return tipo;
    }

    public void setTipo(Tipos tipo) {
        this.tipo = tipo;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public Long getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(Long turmaId) {
        this.turmaId = turmaId;
    }

    public AlunoResponse getAluno() {
        return aluno;
    }

    public void setAluno(AlunoResponse aluno) {
        this.aluno = aluno;
    }
}
