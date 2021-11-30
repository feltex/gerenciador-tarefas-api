package br.com.feltex.gerenciadortarefas.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "tarefa")
public class Tarefa {
    @Id
    private Integer id;
    private String nome;
    private boolean concluida;
    private Instant dataCriacao;
    private Instant ultimaAtualizacao;
}
