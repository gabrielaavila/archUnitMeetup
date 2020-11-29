package com.meetup.archunit.controller;

import com.meetup.archunit.domain.request.AlunoRequest;
import com.meetup.archunit.domain.response.AlunoListResponse;
import com.meetup.archunit.domain.response.AlunoMediaResponse;
import com.meetup.archunit.domain.response.AlunoResponse;
import com.meetup.archunit.entity.Aluno;
import com.meetup.archunit.entity.Avaliacao;
import com.meetup.archunit.repository.AlunoRepository;
import com.meetup.archunit.repository.AvaliacaoRepository;
import com.meetup.archunit.repository.TurmaAlunoRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.meetup.archunit.domain.converter.AlunoConverter.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private TurmaAlunoRepository turmaAlunoRepository;

    @ApiOperation(value = "Cria um novo aluno no banco de dados")
    @PostMapping(value = "/aluno", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AlunoResponse> createAluno(@RequestBody AlunoRequest alunoRequest) {
        Aluno savedAluno = alunoRepository.save(toEntity(alunoRequest));

        return new ResponseEntity<>(toResponse(savedAluno), CREATED);
    }

    @ApiOperation(value = "Retorna dados do aluno cadastrado com o id informado")
    @GetMapping(value = "/aluno/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AlunoResponse> getAlunoById(@PathVariable("id") Long id) {
        Optional<Aluno> retrievedAluno = alunoRepository.findById(id);

        return retrievedAluno
                .map(aluno -> new ResponseEntity<>(toResponse(aluno), OK))
                .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
    }

    @ApiOperation(value = "Retorna a lista de alunos cadastrados no banco de dados")
    @GetMapping(value = "/aluno", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AlunoListResponse> getAlunos() {
        List<Aluno> retrievedAluno = alunoRepository.findAll();
        AlunoListResponse response = new AlunoListResponse();
        response.setAlunos(toResponseList(retrievedAluno));

        return new ResponseEntity<>(response, OK);
    }

    @ApiOperation(value = "Deleta o aluno cadastrado com o id informado")
    @DeleteMapping(value = "/aluno/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AlunoResponse> deleteAluno(@PathVariable("id") Long id) {

        alunoRepository.deleteById(id);

        return new ResponseEntity<>(OK);
    }

    @GetMapping(value = "/aluno/media/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AlunoMediaResponse> getMediasAluno(@PathVariable("id") Long alunoId, @Param("turmaId") Long turmaId){

        List<Avaliacao> avaliacao = avaliacaoRepository.findAllByAlunoUniqueIDAndTurmaUniqueID(alunoId, turmaId);

        Double nota = avaliacao.stream()
                .map(Avaliacao::getNota)
                .reduce(0.0, Double::sum);
        nota = nota / avaliacao.size();
        return ResponseEntity.ok(new AlunoMediaResponse(nota));
    }
}
