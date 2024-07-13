package com.dunkware.street.street.smart.ui.viewers;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.dunkware.street.stream.model.trade.Trade;

public class TradeTableModel extends AbstractTableModel {
    private List<Trade> trades;
    private String[] columnNames = {"Trade ID", "Symbol", "State", "Entry Price", "Exit Price", "Volume"};

    public TradeTableModel(List<Trade> trades) {
        this.trades = trades;
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
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Trade trade = trades.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return trade.getId();
            case 1:
                return trade.getSymbol();
            case 2:
                return trade.getState();
            case 3:
                return trade.getEntryPrice();
            case 4:
                return trade.getExitPrice();
            case 5:
                return trade.getVolume();
            default:
                return null;
        }
    }

    public void setTrades(List<Trade> trades) {
        this.trades = trades;
        fireTableDataChanged();
    }
}