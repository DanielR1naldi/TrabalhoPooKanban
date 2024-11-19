package com.example.kanbann.controller;

import com.example.kanbann.model.Tarefa;
import com.example.kanbann.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping()
    public Tarefa cadastrar(@RequestBody Tarefa tarefa){
           return tarefaService.cadastrar(tarefa);
    }

    @GetMapping()
    public List<Tarefa>listarPorStatus(){
        return tarefaService.listarPorStatus();
    }

    @PutMapping("/{id}/move}")
    public Tarefa mudarDeStatus(@PathVariable("id") Long id){
        return tarefaService.mudarDeStatus(id);
    }

    @PutMapping("/{id}")
    public Tarefa alterar(@RequestBody Tarefa tarefa, @PathVariable("id") Long id){
        return tarefaService.alterar(id,tarefa);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable("id") Long id){
        tarefaService.deletar(id);
    }



}

