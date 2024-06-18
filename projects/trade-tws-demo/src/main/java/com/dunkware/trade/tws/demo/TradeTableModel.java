package com.dunkware.trade.tws.demo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

class TradeTableModel extends AbstractTableModel {
    private List<Trade> trades = new ArrayList<>();
    private final String[] columnNames = {"Symbol", "Status", "Size", "Fill Price"};

    public void addTrade(Trade trade) {
        trades.add(trade);
        fireTableRowsInserted(trades.size() - 1, trades.size() - 1);
    }

    @Override
    public int getRowCount() {
        return trades.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Trade trade = trades.get(rowIndex);
        switch (columnIndex) {
            case 0: return trade.getSymbol();
            case 1: return trade.getStatus();
            case 2: return trade.getSize();
            case 3: return trade.getFillPrice();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}

class Trade {
    private String symbol;
    private String status;
    private int size;
    private double fillPrice;

    public Trade(String symbol, String status, int size, double fillPrice) {
        this.symbol = symbol;
        this.status = status;
        this.size = size;
        this.fillPrice = fillPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getStatus() {
        return status;
    }

    public int getSize() {
        return size;
    }

    public double getFillPrice() {
        return fillPrice;
    }
}
