package com.meetup.archunit.controller;

import com.meetup.archunit.domain.request.AlunoRequest;
import com.meetup.archunit.domain.response.ApresentacaoResponse;
import com.meetup.archunit.entity.Aluno;
import com.meetup.archunit.entity.Avaliacao;
import com.meetup.archunit.entity.Turma;
import com.meetup.archunit.entity.TurmaAluno;
import com.meetup.archunit.repository.AlunoRepository;
import com.meetup.archunit.repository.AvaliacaoRepository;
import com.meetup.archunit.repository.TurmaAlunoRepository;
import com.meetup.archunit.repository.TurmaRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.meetup.archunit.domain.enums.Tipos.PROVA_1;
import static com.meetup.archunit.domain.enums.Tipos.PROVA_2;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ApresentacaoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private TurmaAlunoRepository turmaAlunoRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @ApiOperation(value = "Popula o banco de dados para apresentacao")
    @PostMapping(value = "/apresentacao", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApresentacaoResponse> createAluno(@RequestBody AlunoRequest alunoRequest) {

        List<Aluno> alunosCriados = criaAluno();
        Turma turmaCriada = criaTurma();
        List<TurmaAluno> turmaAlunoList = populaTurmaComAlunos(alunosCriados, turmaCriada);
        List<Avaliacao> avaliacoes = criaAvaliacoes(alunosCriados, turmaCriada);

        return new ResponseEntity<>(new ApresentacaoResponse(alunosCriados, turmaCriada, avaliacoes, turmaAlunoList),
                CREATED);
    }

    private List<Aluno> criaAluno() {
        List<Aluno> alunos = new ArrayList<>();

        alunos.add(alunoRepository.save(new Aluno("Maria", 10)));
        alunos.add(alunoRepository.save(new Aluno("João", 10)));
        alunos.add(alunoRepository.save(new Aluno("Antonia", 9)));
        alunos.add(alunoRepository.save(new Aluno("José", 11)));
        alunos.add(alunoRepository.save(new Aluno("Camila", 11)));
        alunos.add(alunoRepository.save(new Aluno("Fabio", 12)));
        return alunos;
    }

    private Turma criaTurma() {
        return turmaRepository.save(new Turma("turma1", "Alexandre"));
    }

    private List<TurmaAluno> populaTurmaComAlunos(List<Aluno> alunosCriados, Turma turmaCriada) {
        List<TurmaAluno> turmaAlunoList = new ArrayList<>();

        for(Aluno aluno : alunosCriados) {
            turmaAlunoList.add(turmaAlunoRepository.save(new TurmaAluno(turmaCriada, aluno)));
        }
        return turmaAlunoList;
    }

    private List<Avaliacao> criaAvaliacoes(List<Aluno> alunosCriados, Turma turma) {
        Random random = new Random();
        List<Avaliacao> avaliacoes = new ArrayList<>();

        for (Aluno aluno : alunosCriados) {
            avaliacoes.add(avaliacaoRepository.save(new Avaliacao(PROVA_1, getRandomValue(random), turma, aluno)));
            avaliacoes.add(avaliacaoRepository.save(new Avaliacao(PROVA_2, getRandomValue(random), turma, aluno)));
        }
        return avaliacoes;
    }

    private double getRandomValue(Random random) {
        return (random.nextInt(1000)/100);
    }
}
