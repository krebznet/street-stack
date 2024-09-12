package com.dunkware.time.entity.model.search;

public enum TimeEntitySearchOperator {
	
	EQUAL("="),
    NOT_EQUAL("!="),
    GREATER_THAN(">"),
    LESS_THAN("<"),
    GREATER_THAN_OR_EQUAL(">="),
    LESS_THAN_OR_EQUAL("<="),
    LIKE("LIKE"),
    IN("IN"),
    BETWEEN("BETWEEN");

    private final String symbol;

    TimeEntitySearchOperator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static TimeEntitySearchOperator fromSymbol(String symbol) {
        for (TimeEntitySearchOperator operation : TimeEntitySearchOperator.values()) {
            if (operation.getSymbol().equals(symbol)) {
                return operation;
            }
        }
        throw new IllegalArgumentException("Unknown symbol: " + symbol);
    }

}
