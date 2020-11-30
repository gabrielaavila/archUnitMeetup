package com.meetup.archunit.service;

import com.meetup.archunit.domain.dto.AvaliacaoDto;
import com.meetup.archunit.domain.enums.Tipos;
import com.meetup.archunit.domain.response.AlunoResponse;
import com.meetup.archunit.domain.response.TurmaMediaResponse;
import com.meetup.archunit.entity.Avaliacao;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.Arrays;
import java.util.List;

import static com.meetup.archunit.domain.enums.Tipos.PROVA_1;
import static com.meetup.archunit.domain.enums.Tipos.PROVA_2;
import static com.meetup.archunit.mother.AlunoMother.getAluno;
import static com.meetup.archunit.mother.AvaliacaoMother.getAvaliacaoDto;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class TurmaServiceTest {

    @InjectMocks
    private TurmaService fixture;

    @Mock
    private AlunoService alunoService;

    @Mock
    private AvaliacaoService avaliacaoService;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    public void testGetAlunoComMaiorMediaSuccess() throws NotFoundException {
        AlunoResponse aluno1 = new AlunoResponse(1L, "Francisco", 12);
        AlunoResponse aluno2 = new AlunoResponse(2L, "Joana", 10);

        List<AvaliacaoDto> avaliacoes = asList(getAvaliacaoDto(PROVA_1, aluno1),
                getAvaliacaoDto(PROVA_2, aluno1),
                getAvaliacaoDto(PROVA_1, aluno2),
                getAvaliacaoDto(PROVA_2, aluno2));

        when(avaliacaoService.getAllAvaliacoesPorTurma(7L))
                .thenReturn(avaliacoes);
        when(alunoService.calcularMediaDeAvaliacoesDeAluno(asList(avaliacoes.get(0), avaliacoes.get(1))))
                .thenReturn(5.0);
        when(alunoService.calcularMediaDeAvaliacoesDeAluno(asList(avaliacoes.get(2), avaliacoes.get(3))))
                .thenReturn(4.0);

        TurmaMediaResponse response = fixture.getAlunoComMaiorMedia(7L);

        assertNotNull(response);
        assertEquals(5.0, response.getMedia());
        assertEquals(aluno1, response.getAluno());
    }

    @Test
    public void testGetAlunoComMaiorMediaAvaliacaoServiceThrowsNotFound() throws NotFoundException {
        when(avaliacaoService.getAllAvaliacoesPorTurma(7L))
                .thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class,
                () -> fixture.getAlunoComMaiorMedia(7L));
    }
}