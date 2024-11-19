package com.example.kanbann.model;

import com.example.kanbann.model.enums.Prioridade;
import com.example.kanbann.model.enums.StatusTarefa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String titulo;

    private String descricao;

    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    @Enumerated(EnumType.STRING)
    private StatusTarefa status;

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @Column(name = "data_limite")
    private LocalDate dataLimite;

    public Long getId() {
        return id;
    }

    public @NotNull String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public StatusTarefa getStatus() {
        return status;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public LocalDate getDataLimite() {
        return dataLimite;
    }

    public void alterar(Tarefa novosDados) {
        this.titulo = novosDados.getTitulo();
        this.descricao = novosDados.getDescricao();
        this.status = novosDados.getStatus();
        this.prioridade = novosDados.getPrioridade();
        this.dataLimite = novosDados.getDataLimite();
    }
}
