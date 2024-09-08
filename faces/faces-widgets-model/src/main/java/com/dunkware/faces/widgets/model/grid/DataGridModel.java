package com.dunkware.faces.widgets.model.grid;

import java.util.ArrayList;
import java.util.List;

public class DataGridModel {

    private String entity;
    private List<DataGridColumn> columns;

    public DataGridModel(String entity) {
        this.entity = entity;
        this.columns = new ArrayList<>();
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public List<DataGridColumn> getColumns() {
        return columns;
    }

    public void addColumn(DataGridColumn column) {
        this.columns.add(column);
    }

    public static MetaGridBuilder builder() {
        return new MetaGridBuilder();
    }

    public static class MetaGridBuilder {
        private String entity;
        private List<DataGridColumn> columns = new ArrayList<>();

        public MetaGridBuilder entity(String entity) {
            this.entity = entity;
            return this;
        }

        public MetaGridBuilder addColumn(DataGridColumn column) {
            this.columns.add(column);
            return this;
        }

        public DataGridModel build() {
            DataGridModel grid = new DataGridModel(entity);
            grid.columns.addAll(columns);
            return grid;
        }
    }
}