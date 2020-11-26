package com.meetup.archunit.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "CLASSE")
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CLASSE_UID")
    private Long uniqueID;

    @JoinColumn(name = "DISCIPLINA_UID", referencedColumnName = "DISCIPLINA_UID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Disciplina disciplina;

    @JoinColumn(name = "ALUNO_UID", referencedColumnName = "ALUNO_UID")
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Aluno> aluno;

    public Long getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(Long uniqueID) {
        this.uniqueID = uniqueID;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public List<Aluno> getAluno() {
        return aluno;
    }

    public void setAluno(List<Aluno> aluno) {
        this.aluno = aluno;
    }
}
