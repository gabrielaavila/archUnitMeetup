package com.meetup.archunit.controller;

import com.meetup.archunit.domain.response.AlunoResponse;
import com.meetup.archunit.domain.response.TurmaMediaResponse;
import com.meetup.archunit.service.TurmaService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = {TurmaController.class})
public class TurmaControllerTest {

    private static final String TURMA_MEDIA_MAIOR_URL = "/turma/media/maior";

    @Autowired
    private MockMvc mvc;

    @InjectMocks
    private TurmaController controller;

    @MockBean
    private TurmaService turmaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetMaiorMediaTurmaSuccess() throws Exception {
        TurmaMediaResponse response = new TurmaMediaResponse(new AlunoResponse(), 10.0);
        when(turmaService.getAlunoComMaiorMedia(1L))
                .thenReturn(response);

        mvc.perform(get(TURMA_MEDIA_MAIOR_URL+"/1")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMaiorMediaTurmaNotFound() throws Exception {
        when(turmaService.getAlunoComMaiorMedia(1L))
                .thenThrow(NotFoundException.class);

        mvc.perform(get(TURMA_MEDIA_MAIOR_URL+"/1")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }


}