package com.meetup.archunit.entity;

import com.sun.istack.NotNull;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "ALUNO")
public class Aluno extends BaseEntity{

    @NotNull
    @Id
    @Column(name = "ALUNO_UID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uniqueID;

    @NotNull
    @Column(name = "NOME")
    private String nome;

    @NotNull
    @Column(name = "IDADE")
    private Integer idade;

    public Aluno() {
    }

    public Aluno(String nome, Integer idade) {
        this.nome = nome;
        this.idade = idade;
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

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
}
