package br.com.feltex.gerenciadortarefas;

import br.com.feltex.gerenciadortarefas.dao.TarefaRepository;
import br.com.feltex.gerenciadortarefas.modelo.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;

@SpringBootApplication
public class TodoListApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoListApplication.class, args);
    }


    @Autowired
    private TarefaRepository tarefaRepository;

    @Bean
    public CommandLineRunner carregarData() {

        return args -> {
            tarefaRepository.save(new Tarefa(1, "Tarefa1", true, Instant.now(), Instant.now()));
            tarefaRepository.save(new Tarefa(2, "Tarefa2", false, Instant.now(), Instant.now()));
            tarefaRepository.save(new Tarefa(3, "Tarefa3", true, Instant.now(), Instant.now()));
        };
    }
}
