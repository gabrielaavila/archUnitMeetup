package com.meetup.archunit.service;

import com.meetup.archunit.domain.converter.AlunoConverter;
import com.meetup.archunit.domain.dto.AvaliacaoDto;
import com.meetup.archunit.domain.request.AlunoRequest;
import com.meetup.archunit.domain.response.AlunoResponse;
import com.meetup.archunit.entity.Aluno;
import com.meetup.archunit.repository.AlunoRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.meetup.archunit.domain.converter.AlunoConverter.*;
import static java.lang.String.format;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@Transactional
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AvaliacaoService avaliacaoService;

    public AlunoResponse createAluno(AlunoRequest request) {
        Aluno savedAluno = alunoRepository.save(toEntity(request));

        return toResponse(savedAluno);
    }

    public AlunoResponse getById(Long id) throws NotFoundException {
        Optional<Aluno> retrievedAluno = alunoRepository.findById(id);

        return retrievedAluno
                .map(AlunoConverter::toResponse)
                .orElseThrow(() -> new NotFoundException("Aluno nao encontrado no banco de dados"));
    }

    public List<AlunoResponse> getAll() {
        List<Aluno> retrievedAluno = alunoRepository.findAll();
        return toResponseList(retrievedAluno);
    }

    public Aluno getAllAlunos(){
        return alunoRepository.findAll().get(0);
    }

    public void deleteById(Long id) {
        alunoRepository.deleteById(id);
    }

    public double getMediadeNotasDeAvaliacaoPorTurma(Long alunoId, Long turmaId) throws NotFoundException {
        List<AvaliacaoDto> avaliacoes = avaliacaoService.getAllAvaliacoesPorAlunoETurma(alunoId, turmaId);

        if (isEmpty(avaliacoes)){
            throw new NotFoundException(format("Nao existem avaliacoes para o aluno [%s] e a turma [%s]", alunoId, turmaId));
        }

        return calcularMediaDeAvaliacoesDeAluno(avaliacoes);
    }

    public double calcularMediaDeAvaliacoesDeAluno(List<AvaliacaoDto> avaliacoes) {
        double nota = avaliacoes.stream()
                .map(AvaliacaoDto::getNota)
                .reduce(0.0, Double::sum);
        return nota / avaliacoes.size();
    }
}
