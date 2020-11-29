package com.meetup.archunit.domain.request;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Objeto de requisição para Aluno")
public class AlunoRequest {

    @ApiModelProperty(value = "nome do aluno", required = true)
    @NotNull
    private String nome;

    @ApiModelProperty(value = "idade do aluno")
    @NotNull
    private Integer idade;

    public AlunoRequest() {
    }

    public AlunoRequest(String nome, Integer idade) {
        this.nome = nome;
        this.idade = idade;
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
