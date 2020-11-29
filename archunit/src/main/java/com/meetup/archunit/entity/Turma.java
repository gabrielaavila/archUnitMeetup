package com.meetup.archunit.entity;

import com.sun.istack.NotNull;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "TURMA")
public class Turma {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TURMA_UID")
    private Long uniqueID;

    @NotNull
    @Column(name = "NOME")
    private String nome;

    @NotNull
    @Column(name = "PROFESSOR")
    private String professor;

    public Turma() {
    }

    public Turma(String nome, String professor) {
        this.nome = nome;
        this.professor = professor;
    }

    public Long getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(Long uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
}
