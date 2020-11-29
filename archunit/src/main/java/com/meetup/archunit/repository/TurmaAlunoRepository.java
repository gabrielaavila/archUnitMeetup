package com.meetup.archunit.repository;

import com.meetup.archunit.entity.TurmaAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaAlunoRepository extends JpaRepository<TurmaAluno, Long> {
}
