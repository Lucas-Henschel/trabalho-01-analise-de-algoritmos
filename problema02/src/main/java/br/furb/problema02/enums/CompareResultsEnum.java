package br.furb.problema02.enums;

public enum CompareResultsEnum {
    EQUALITY_RESULT(0);

    private final int value;

    CompareResultsEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
