package br.com.feltex.gerenciadortarefas.dao;

import br.com.feltex.gerenciadortarefas.modelo.Tarefa;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TarefaRepository extends CrudRepository<Tarefa, Integer> {

    List<Tarefa> findAll();
}
