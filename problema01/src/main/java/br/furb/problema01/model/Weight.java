package br.furb.problema01.model;

public class Weight {
    private final int grams;

    public Weight(int grams) {
        if (grams < 0) {
            throw new IllegalArgumentException("Peso inválido");
        }

        this.grams = grams;
    }

    public int inGrams() {
        return grams;
    }
}
