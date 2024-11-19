package com.example.kanbann.service;

import com.example.kanbann.model.Tarefa;
import com.example.kanbann.model.enums.StatusTarefa;
import com.example.kanbann.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TarefaService {

    private final TarefaRepository repository;
    @Autowired
    StatusTarefa novoStatus;


    public Tarefa cadastrar(Tarefa novaTarefa) {
        LocalDate dataCriacao = LocalDate.now();
        novaTarefa.setDataCriacao(dataCriacao);
        novaTarefa.setStatus(StatusTarefa.A_FAZER);  // Aqui, 'A_FAZER' é o valor correto do enum
        return this.repository.save(novaTarefa);
    }


    public List<Tarefa> listarPorStatus() {
        return this.repository.listarPorStatus();
    }

    public Tarefa mudarDeStatus(Long id) {
        Tarefa tarefaDomain = consultarPorId(id);
        boolean alterar = false;


        if(novoStatus.equals(StatusTarefa.A_FAZER)
                && tarefaDomain.getStatus().equals(StatusTarefa.EM_PROGRESSO)) {
            alterar = true;
        } else if (novoStatus.equals(StatusTarefa.CONCLUIDO)
                && tarefaDomain.getStatus().equals(StatusTarefa.EM_PROGRESSO)){
            alterar = true;
        } else if (tarefaDomain.getStatus().equals(StatusTarefa.A_FAZER)) {
            alterar = true;
        }

        if(alterar){
            tarefaDomain.setStatus(novoStatus);
        }

        return this.repository.save(tarefaDomain);
    }

    public Tarefa alterar(Long id, Tarefa novosDados) {
        Tarefa tarefaExistente = consultarPorId(id);

        tarefaExistente.alterar(novosDados);

        return this.repository.save(tarefaExistente);
    }

    public void deletar(Long id) {
        this.repository.deleteById(id);
    }

    private Tarefa consultarPorId(Long id) {
        Optional<Tarefa> tarefaExistente = this.repository.findById(id);

        if(tarefaExistente.isEmpty()) {
            System.out.println("Não foi possível achar a tarefa");
        }

        return tarefaExistente.get();
    }

    public List<Tarefa> listarPorPrioridadeStatus() {
        return this.repository.listarPorPrioridadeStatus();
    }

    public List<Tarefa> consultarPorPrioridadeDataLimite() {
        return this.repository.listarPorPrioridadeDataLimite();
    }

    public List<Tarefa> getRelatorio() {
        List<Tarefa> tarefas = this.repository.listarPorStatus();

        return tarefas.stream()
                .sorted(Comparator
                        .comparing(Tarefa::getDataLimite)
                        .thenComparing(tarefa -> tarefa.getClass().equals(StatusTarefa.CONCLUIDO))
                )
                .toList();
    }

}
