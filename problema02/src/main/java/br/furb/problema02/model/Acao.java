package br.furb.problema02.model;

import java.util.List;

public class Acao {
    private String nome;
    private double valor;
    private List<Ordem> ordens;

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public List<Ordem> getOrdens() {
        return ordens;
    }

    public Acao(String nome, double valor, List<Ordem> ordens) {
        this.nome = nome;
        this.valor = valor;
        this.ordens = ordens;
    }
}
