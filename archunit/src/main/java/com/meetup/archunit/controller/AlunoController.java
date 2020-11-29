package com.meetup.archunit.controller;

import com.meetup.archunit.domain.request.AlunoRequest;
import com.meetup.archunit.domain.response.AlunoListResponse;
import com.meetup.archunit.domain.response.AlunoMediaResponse;
import com.meetup.archunit.domain.response.AlunoResponse;
import com.meetup.archunit.service.AlunoService;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @ApiOperation(value = "Cria um novo aluno no banco de dados")
    @PostMapping(value = "/aluno", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AlunoResponse> createAluno(@RequestBody AlunoRequest alunoRequest) {
        return new ResponseEntity<>(alunoService.createAluno(alunoRequest), CREATED);
    }

    @ApiOperation(value = "Retorna dados do aluno cadastrado com o id informado")
    @GetMapping(value = "/aluno/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AlunoResponse> getAlunoById(@PathVariable("id") Long id) {

        try {
            return new ResponseEntity<>(alunoService.getById(id), OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Retorna a lista de alunos cadastrados no banco de dados")
    @GetMapping(value = "/aluno", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AlunoListResponse> getAlunos() {

        return new ResponseEntity<>(new AlunoListResponse(alunoService.getAll()), OK);
    }

    @ApiOperation(value = "Deleta o aluno cadastrado com o id informado")
    @DeleteMapping(value = "/aluno/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AlunoResponse> deleteAluno(@PathVariable("id") Long id) {

        alunoService.deleteById(id);
        return new ResponseEntity<>(OK);
    }

    @GetMapping(value = "/aluno/media/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AlunoMediaResponse> getMediasAluno(@PathVariable("id") Long alunoId,
                                                             @Param("turmaId") Long turmaId){

        return ResponseEntity.ok(new AlunoMediaResponse(alunoService.getMediadeNotasDeAvaliacaoPorTurma(alunoId, turmaId)));
    }
}
