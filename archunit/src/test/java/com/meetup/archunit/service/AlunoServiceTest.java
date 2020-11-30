package com.meetup.archunit.service;

import com.meetup.archunit.domain.dto.AvaliacaoDto;
import com.meetup.archunit.domain.request.AlunoRequest;
import com.meetup.archunit.domain.response.AlunoResponse;
import com.meetup.archunit.entity.Aluno;
import com.meetup.archunit.repository.AlunoRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.meetup.archunit.domain.converter.AlunoConverter.toEntity;
import static com.meetup.archunit.mother.AlunoMother.getAluno;
import static com.meetup.archunit.mother.AlunoMother.getAlunoRequest;
import static com.meetup.archunit.mother.AvaliacaoMother.getAvaliacaoDtoP1;
import static com.meetup.archunit.mother.AvaliacaoMother.getAvaliacaoDtoP2;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


class AlunoServiceTest {

    @InjectMocks
    private AlunoService fixture;

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private AvaliacaoService avaliacaoService;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    public void testCreateAlunoSuccess() {
        AlunoRequest request = getAlunoRequest();
        Aluno aluno = toEntity(request);
        aluno.setUniqueID(1L);

        when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);

        AlunoResponse response = fixture.createAluno(request);

        assertEquals(aluno.getUniqueID(), response.getUniqueID());
        assertEquals(aluno.getNome(), response.getNome());
        assertEquals(aluno.getIdade(), response.getIdade());

        verify(alunoRepository).save(any(Aluno.class));
    }

    @Test
    public void testGetByIdSuccess() throws NotFoundException {
        Aluno aluno = getAluno();

        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));

        AlunoResponse alunoResponse = fixture.getById(1L);

        assertEquals(aluno.getUniqueID(), alunoResponse.getUniqueID());
        assertEquals(aluno.getNome(), alunoResponse.getNome());
        assertEquals(aluno.getIdade(), alunoResponse.getIdade());

        verify(alunoRepository).findById(anyLong());
    }

    @Test
    public void testGetByIdNotFound() throws NotFoundException {

        when(alunoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> fixture.getById(1L),
                "Aluno nao encontrado no banco de dados");

        verify(alunoRepository).findById(anyLong());
    }

    @Test
    public void testGetAllSuccess() {
        Aluno aluno = getAluno();

        when(alunoRepository.findAll()).thenReturn(Collections.singletonList(aluno));

        List<AlunoResponse> alunoResponse = fixture.getAll();

        assertFalse(alunoResponse.isEmpty());
        assertEquals(1, alunoResponse.size());
        assertEquals(aluno.getUniqueID(), alunoResponse.get(0).getUniqueID());
        assertEquals(aluno.getNome(), alunoResponse.get(0).getNome());
        assertEquals(aluno.getIdade(), alunoResponse.get(0).getIdade());

        verify(alunoRepository).findAll();
    }

    @Test
    public void testGetAllEmptyList() {

        when(alunoRepository.findAll()).thenReturn(emptyList());

        List<AlunoResponse> alunoResponse = fixture.getAll();

        assertTrue(alunoResponse.isEmpty());

        verify(alunoRepository).findAll();
    }

    @Test
    public void testDelete() {
        doNothing().when(alunoRepository).deleteById(1L);

        fixture.deleteById(1L);

        verify(alunoRepository).deleteById(anyLong());
    }

    @Test
    public void testGetMediadeNotasDeAvaliacaoPorTurmaSuccess() throws NotFoundException {
        Long turmaId = 10L;
        Long alunoId = 99L;

        List<AvaliacaoDto> avaliacoes = Arrays.asList(getAvaliacaoDtoP1(1L, turmaId, alunoId),
                getAvaliacaoDtoP2(2L, turmaId, alunoId));

        when(avaliacaoService.getAllAvaliacoesPorAlunoETurma(alunoId, turmaId))
                .thenReturn(avaliacoes);

        double result = fixture.getMediadeNotasDeAvaliacaoPorTurma(alunoId, turmaId);

        double expectedResult = (avaliacoes.get(0).getNota() + avaliacoes.get(1).getNota())/2;

        assertEquals(expectedResult, result);

        verify(avaliacaoService).getAllAvaliacoesPorAlunoETurma(alunoId, turmaId);

    }

    @Test
    public void testGetMediadeNotasDeAvaliacaoPorTurmaEmptyList() throws NotFoundException {
        Long turmaId = 10L;
        Long alunoId = 99L;

        when(avaliacaoService.getAllAvaliacoesPorAlunoETurma(alunoId, turmaId))
                .thenReturn(emptyList());

        assertThrows(NotFoundException.class,
                () -> fixture.getMediadeNotasDeAvaliacaoPorTurma(alunoId, turmaId),
                "Nao existem avaliacoes para o aluno [99] e a turma [10]");

        verify(avaliacaoService).getAllAvaliacoesPorAlunoETurma(alunoId, turmaId);
    }

}