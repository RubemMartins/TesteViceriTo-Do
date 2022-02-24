package br.com.testeviceritodo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.testeviceritodo.entities.Tarefa;


@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
	public List<Tarefa> findAllByDescricaoContainingIgnoreCase ( String descricao);
}


