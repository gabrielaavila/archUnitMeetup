package com.meetup.archunit.service;

import com.meetup.archunit.domain.dto.AvaliacaoDto;
import com.meetup.archunit.entity.Avaliacao;
import com.meetup.archunit.repository.AvaliacaoRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static com.meetup.archunit.mother.AvaliacaoMother.getAvaliacao;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class AvaliacaoServiceTest {

    @InjectMocks
    private AvaliacaoService fixture;

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    public void testGetAllAvaliacoesPorAlunoETurmaSuccess() {
        Avaliacao avaliacao = getAvaliacao();
        when(avaliacaoRepository.findAllByAlunoUniqueIDAndTurmaUniqueID(1L, 7L))
                .thenReturn(Collections.singletonList(avaliacao));

        List<AvaliacaoDto> result = fixture.getAllAvaliacoesPorAlunoETurma(1L, 7L);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(avaliacao.getUniqueID(), result.get(0).getUniqueID());
        assertEquals(avaliacao.getTipo(), result.get(0).getTipo());
        assertEquals(avaliacao.getAluno().getUniqueID(), result.get(0).getAluno().getUniqueID());
        assertEquals(avaliacao.getTurma().getUniqueID(), result.get(0).getTurmaId());
        assertEquals(avaliacao.getNota(), result.get(0).getNota());
    }

    @Test
    public void testGetAllAvaliacoesPorAlunoETurmaEmptyResult() {
        when(avaliacaoRepository.findAllByAlunoUniqueIDAndTurmaUniqueID(1L, 7L))
                .thenReturn(Collections.emptyList());

        List<AvaliacaoDto> result = fixture.getAllAvaliacoesPorAlunoETurma(1L, 7L);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetAllAvaliacoesPorTurmaSuccess() throws NotFoundException {
        Avaliacao avaliacao = getAvaliacao();

        when(avaliacaoRepository.findAllByTurmaUniqueID(2L))
                .thenReturn(Collections.singletonList(avaliacao));

        List<AvaliacaoDto> result = fixture.getAllAvaliacoesPorTurma(2L);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(avaliacao.getUniqueID(), result.get(0).getUniqueID());
        assertEquals(avaliacao.getTipo(), result.get(0).getTipo());
        assertEquals(avaliacao.getAluno().getUniqueID(), result.get(0).getAluno().getUniqueID());
        assertEquals(avaliacao.getTurma().getUniqueID(), result.get(0).getTurmaId());
        assertEquals(avaliacao.getNota(), result.get(0).getNota());

        verify(avaliacaoRepository).findAllByTurmaUniqueID(anyLong());
    }

    @Test
    public void testGetAllAvaliacoesPorTurmaEmptyReturn() {
        when(avaliacaoRepository.findAllByTurmaUniqueID(2L))
                .thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class,
                () -> fixture.getAllAvaliacoesPorTurma(2L),
                "Nao existem avaliacoes para a turma 2");

        verify(avaliacaoRepository).findAllByTurmaUniqueID(anyLong());
    }

}