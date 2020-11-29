package com.meetup.archunit.repository;

import com.meetup.archunit.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    List<Avaliacao> findAllByAlunoUniqueIDAndTurmaUniqueID(Long alunoUniqueID, Long turmaUniqueID);

    List<Avaliacao> findAllByTurmaUniqueID(Long turmaUniqueID);
}
