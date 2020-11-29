package com.meetup.archunit.entity;

import com.meetup.archunit.domain.enums.Tipos;
import com.meetup.archunit.entity.converters.TiposConverter;
import com.sun.istack.NotNull;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "AVALIACAO")
public class Avaliacao {

    @NotNull
    @Id
    @Column(name = "AVALIACAO_UID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uniqueID;

    @NotNull
    @Column(name = "TIPO")
    @Convert(converter = TiposConverter.class)
    private Tipos tipo;

    @NotNull
    @Column(name = "NOTA")
    private double nota;

    @NotNull
    @JoinColumn(name = "TURMA_UID", referencedColumnName = "TURMA_UID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Turma turma;

    @NotNull
    @JoinColumn(name = "ALUNO_UID", referencedColumnName = "ALUNO_UID")
    @ManyToOne(fetch =FetchType.EAGER)
    private Aluno aluno;

    public Avaliacao() {
    }

    public Avaliacao(Tipos tipo, double nota, Turma turma, Aluno aluno) {
        this.tipo = tipo;
        this.nota = nota;
        this.turma = turma;
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
