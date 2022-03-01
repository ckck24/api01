package org.zerock.api01.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.api01.domain.Todo;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {


    @EntityGraph(attributePaths = "imgSet")
    @Query("select t from Todo t where t.tno = :tno")
    Optional<Todo > getWithFiles(Long tno);

}
