package com.dunkware.time.entity.model.search;

public enum EntityOperator {
	
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

    EntityOperator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static EntityOperator fromSymbol(String symbol) {
        for (EntityOperator operation : EntityOperator.values()) {
            if (operation.getSymbol().equals(symbol)) {
                return operation;
            }
        }
        throw new IllegalArgumentException("Unknown symbol: " + symbol);
    }

}
