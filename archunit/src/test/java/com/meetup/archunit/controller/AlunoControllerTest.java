package com.meetup.archunit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meetup.archunit.domain.request.AlunoRequest;
import com.meetup.archunit.service.AlunoService;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.meetup.archunit.mother.AlunoMother.getAlunoRequest;
import static com.meetup.archunit.mother.AlunoMother.getAlunoResponse;
import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = {AlunoController.class})
public class AlunoControllerTest {

    private static final String ALUNO_URL = "/aluno";

    @Autowired
    private MockMvc mvc;

    @InjectMocks
    private AlunoController controller;

    @MockBean
    private AlunoService alunoService;

    ObjectMapper objectMapper = new ObjectMapper();

    public AlunoControllerTest() {
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateAluno() throws Exception {
        AlunoRequest request = getAlunoRequest();

        when(alunoService.createAluno(request)).thenReturn(getAlunoResponse());

        mvc.perform(post(ALUNO_URL)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());

        verify(alunoService).createAluno(any(AlunoRequest.class));
    }

    @Test
    public void testGetById() throws Exception {
        when(alunoService.getById(1L)).thenReturn(getAlunoResponse());

        mvc.perform(get(ALUNO_URL+"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(alunoService).getById(anyLong());
    }

    @Test
    public void testGetByIdNotFound() throws Exception {
        when(alunoService.getById(1L)).thenThrow(NotFoundException.class);

        mvc.perform(get(ALUNO_URL+"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());

        verify(alunoService).getById(anyLong());
    }

    @Test
    public void testGetAll() throws Exception {
        when(alunoService.getAll()).thenReturn(singletonList(getAlunoResponse()));

        mvc.perform(get(ALUNO_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk());

        verify(alunoService).getAll();
    }

    @Test
    public void testDeleteAluno() throws Exception {
        doNothing().when(alunoService).deleteById(anyLong());

        mvc.perform(delete(ALUNO_URL+"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(alunoService).deleteById(anyLong());
    }

    @Test
    public void testGetMediasAlunoSuccess() throws Exception {
        when(alunoService.getMediadeNotasDeAvaliacaoPorTurma(1L, 7L))
                .thenReturn(10.0);

        mvc.perform(get(ALUNO_URL+"/media/1?turmaId=7")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(alunoService).getMediadeNotasDeAvaliacaoPorTurma(anyLong(), anyLong());

    }


    @Test
    public void testGetMediasAlunoNotFound() throws Exception {
        when(alunoService.getMediadeNotasDeAvaliacaoPorTurma(1L, 7L))
                .thenThrow(NotFoundException.class);

        mvc.perform(get(ALUNO_URL+"/media/1?turmaId=7")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());

        verify(alunoService).getMediadeNotasDeAvaliacaoPorTurma(anyLong(), anyLong());
    }
}