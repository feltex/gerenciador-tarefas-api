package br.com.feltex.gerenciadortarefas.controller;

import br.com.feltex.gerenciadortarefas.dao.TarefaRepository;
import br.com.feltex.gerenciadortarefas.dto.TarefaDto;
import br.com.feltex.gerenciadortarefas.modelo.Tarefa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tarefas")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TarefaController {

    private final TarefaRepository tarefaRepository;

    public TarefaController(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        var lista = lerTarefas();
        return ResponseEntity.status(HttpStatus.CREATED).body(lista);
    }

    @PostMapping
    public void adicionar(@RequestBody String nome) {
        var novoId = gerarId();
        tarefaRepository.save(new Tarefa(novoId, nome, false, Instant.now(), Instant.now()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        tarefaRepository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void atualizarStatus(@PathVariable Integer id) {
        var tarefa = tarefaRepository.findById(id).get();
        tarefa.setConcluida(!tarefa.isConcluida());
        tarefa.setUltimaAtualizacao(Instant.now());
        tarefaRepository.save(tarefa);
    }

    private List<TarefaDto> lerTarefas() {
        return tarefaRepository.findAll()
                .stream()
                .map(t -> new TarefaDto(t.getId(), t.getNome(), t.isConcluida()))
                .collect(Collectors.toList());
    }

    private int gerarId() {
        var tarefas = tarefaRepository.findAll();
        return tarefas.isEmpty() ? 1 :
                tarefas
                        .stream()
                        .max(Comparator.comparing(Tarefa::getId))
                        .get().getId() + 1;
    }
}
