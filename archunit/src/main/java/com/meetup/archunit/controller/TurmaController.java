package com.meetup.archunit.controller;

import com.meetup.archunit.domain.converter.AlunoConverter;
import com.meetup.archunit.domain.response.AlunoResponse;
import com.meetup.archunit.domain.response.TurmaMediaResponse;
import com.meetup.archunit.entity.Aluno;
import com.meetup.archunit.entity.Avaliacao;
import com.meetup.archunit.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.util.CollectionUtils.isEmpty;

@RestController
public class TurmaController {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;


    @GetMapping(value = "/turma/media/maior/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TurmaMediaResponse> getMaiorMediaTurma(@PathVariable("id") Long uniqueId) {

        List<Avaliacao> avaliacaoList = avaliacaoRepository.findAllByTurmaUniqueID(uniqueId);

        if (isEmpty(avaliacaoList)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new TurmaMediaResponse(getAlunoComMaiorMedia(avaliacaoList)));
    }

    private AlunoResponse getAlunoComMaiorMedia(List<Avaliacao> avaliacaoList) {
        Map<Aluno, List<Avaliacao>> avaliacoes = avaliacaoList.stream().collect(Collectors.groupingBy(Avaliacao::getAluno));
        Aluno alunoMaiorNota = null;
        double maiorNota = 0.0;

        for (Aluno aluno : avaliacoes.keySet()) {
            List<Avaliacao> avaliacoesAluno = avaliacoes.get(aluno);

            double nota = calcularMediaDeNotasDeAvaliacao(avaliacoesAluno);

            if (nota > maiorNota) {
                maiorNota = nota;
                alunoMaiorNota = aluno;
            }
        }
        return AlunoConverter.toResponse(alunoMaiorNota);
    }

    private double calcularMediaDeNotasDeAvaliacao(List<Avaliacao> avaliacoes) {
        double nota = avaliacoes.stream()
                .map(Avaliacao::getNota)
                .reduce(0.0, Double::sum);
        return nota / avaliacoes.size();
    }
}
