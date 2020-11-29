package com.meetup.archunit.domain.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Objeto de resposta para media de notas de alunos")
public class AlunoMediaResponse {

    @ApiModelProperty("Media de notas de alunos")
    public Double media;

    public AlunoMediaResponse(Double media){
        this.media = media;
    }

}
