package com.meetup.archunit.service;

import com.meetup.archunit.domain.dto.AvaliacaoDto;
import com.meetup.archunit.domain.response.AlunoResponse;
import com.meetup.archunit.domain.response.TurmaMediaResponse;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
@Transactional
public class TurmaService {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private AvaliacaoService avaliacaoService;


    public TurmaMediaResponse getAlunoComMaiorMedia(Long turmaId) throws NotFoundException {
        List<AvaliacaoDto> avaliacoesTurma = avaliacaoService.getAllAvaliacoesPorTurma(turmaId);
        Map<AlunoResponse, List<AvaliacaoDto>> avaliacoes = avaliacoesTurma.stream().collect(groupingBy(AvaliacaoDto::getAluno));

        AlunoResponse alunoMaiorNota = null;
        double maiorNota = 0.0;

        for (AlunoResponse aluno : avaliacoes.keySet()) {
            List<AvaliacaoDto> avaliacoesAluno = avaliacoes.get(aluno);

            double nota = alunoService.calcularMediaDeAvaliacoesDeAluno(avaliacoesAluno);

            if (nota > maiorNota) {
                maiorNota = nota;
                alunoMaiorNota = aluno;
            }
        }
        return new TurmaMediaResponse(alunoMaiorNota, maiorNota);
    }
}
