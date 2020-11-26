package com.meetup.archunit.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "ALUNO")
public class Aluno {

    @Id
    @Column(name = "ALUNO_UID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uniqueID;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "IDADE")
    private Integer idade;

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
