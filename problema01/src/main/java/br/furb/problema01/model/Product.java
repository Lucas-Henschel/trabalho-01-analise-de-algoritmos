package br.furb.problema01.model;

import java.math.BigDecimal;

public class Product {
    private String name;
    // TODO: criar uma outra class para as informacoes gerais (vou fazer depois da praia, aguarde uns momentos Lucas)
    private BigDecimal price;
    // TODO: alterar o nome do weight para weightInGrams (mesma coisa que falei la em cima, pf não faça voce)
    private Weight weight;

    public Product(String name, BigDecimal price, int weight) {
        this.name = name;
        this.price = price;
        this.weight = new Weight(weight);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getWeightInGrams() {
        return weight.inGrams();
    }
}
