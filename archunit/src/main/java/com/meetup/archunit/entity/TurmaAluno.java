package com.meetup.archunit.entity;

import com.sun.istack.NotNull;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "TURMA_ALUNO")
public class TurmaAluno {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TURMA_ALUNO_UID")
    private Long uniqueID;

    @NotNull
    @JoinColumn(name = "TURMA_UID", referencedColumnName = "TURMA_UID")
    @ManyToOne
    private Turma turma;

    @NotNull
    @JoinColumn(name = "ALUNO_UID", referencedColumnName = "ALUNO_UID")
    @ManyToOne
    private Aluno aluno;

    public TurmaAluno() {
    }

    public TurmaAluno(Turma turma, Aluno aluno) {
        this.turma = turma;
        this.aluno = aluno;
    }

    public Long getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(Long uniqueID) {
        this.uniqueID = uniqueID;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
