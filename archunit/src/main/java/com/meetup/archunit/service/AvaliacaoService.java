package com.meetup.archunit.service;

import com.meetup.archunit.domain.converter.AvaliacaoConverter;
import com.meetup.archunit.domain.dto.AvaliacaoDto;
import com.meetup.archunit.entity.Avaliacao;
import com.meetup.archunit.repository.AvaliacaoRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@Transactional
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public List<AvaliacaoDto> getAllAvaliacoesPorAlunoETurma(Long alunoId, Long turmaId) {
        return avaliacaoRepository.findAllByAlunoUniqueIDAndTurmaUniqueID(alunoId, turmaId).stream()
                .map(AvaliacaoConverter::toDto)
                .collect(Collectors.toList());
    }

    public List<AvaliacaoDto> getAllAvaliacoesPorTurma(Long turmaId) throws NotFoundException {
        List<Avaliacao> avaliacaoList = avaliacaoRepository.findAllByTurmaUniqueID(turmaId);

        if (isEmpty(avaliacaoList)){
            throw new NotFoundException("Nao existem avaliacoes para a turma "+turmaId);
        }
        return avaliacaoList
                .stream()
                .map(AvaliacaoConverter::toDto)
                .collect(Collectors.toList());
    }
}
