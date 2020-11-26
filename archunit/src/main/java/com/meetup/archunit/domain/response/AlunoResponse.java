package com.meetup.archunit.domain.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Objeto de resposta para Aluno")
public class AlunoResponse {

    @ApiModelProperty(value = "ID do aluno cadastrado no banco de dados")
    private Long uniqueID;

    @ApiModelProperty(value = "Nome do aluno")
    private String nome;

    @ApiModelProperty(value = "Idade do aluno")
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
