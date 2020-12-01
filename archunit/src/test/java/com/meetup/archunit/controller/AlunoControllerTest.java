package com.meetup.archunit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meetup.archunit.domain.request.AlunoRequest;
import com.meetup.archunit.entity.Aluno;
import com.meetup.archunit.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static com.meetup.archunit.domain.converter.AlunoConverter.toEntity;
import static com.meetup.archunit.mother.AlunoMother.getAluno;
import static com.meetup.archunit.mother.AlunoMother.getAlunoRequest;
import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = {AlunoController.class})
public class AlunoControllerTest {

    @Autowired
    private MockMvc mvc;

    @InjectMocks
    private AlunoController controller;

    @MockBean
    private AlunoRepository alunoRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetAll() throws Exception {
        String url = "/aluno";

        when(alunoRepository.findAll()).thenReturn(singletonList(getAluno()));

        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk());

    }

    @Test
    public void testGetById() throws Exception {
        String url = "/aluno/1";

        when(alunoRepository.findById(1L)).thenReturn(Optional.of(getAluno()));

        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }

    @Test
    public void testCreateAluno() throws Exception {
        String url = "/aluno";
        AlunoRequest request = getAlunoRequest();

        when(alunoRepository.save(toEntity(request))).thenReturn(getAluno());

        mvc.perform(post(url)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());

    }

    @Test
    public void testDeleteAluno() throws Exception {
        String url = "/aluno/1";

        doNothing().when(alunoRepository).delete(any(Aluno.class));

        mvc.perform(delete(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }
}