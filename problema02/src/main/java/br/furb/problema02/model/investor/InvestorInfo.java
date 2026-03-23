package br.furb.problema02.model.investor;

class InvestorInfo {
    private final String name;

    public InvestorInfo(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome do investidor inválido");
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
