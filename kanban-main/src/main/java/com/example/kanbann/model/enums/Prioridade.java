package com.example.kanbann.model.enums;

public enum Prioridade {
    baixa(3),
    media(2),
    alta(1);

    private int numero;

    Prioridade(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }
}
