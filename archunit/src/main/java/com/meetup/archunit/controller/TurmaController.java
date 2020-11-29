package com.meetup.archunit.controller;

import com.meetup.archunit.domain.response.TurmaMediaResponse;
import com.meetup.archunit.service.TurmaService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class TurmaController {

    @Autowired
    private TurmaService turmaService;


    @GetMapping(value = "/turma/media/maior/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TurmaMediaResponse> getMaiorMediaTurma(@PathVariable("id") Long uniqueId) {
        try {
            return ResponseEntity.ok((turmaService.getAlunoComMaiorMedia(uniqueId)));
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
