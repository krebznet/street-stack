package com.dunkware.faces.widgets.model.grid;

public class DataGridUpdate {

    private String json; // JSON representation of the row data as a string
    private Number id;   // Unique ID for the row
    private String type; // Type of update: INSERT, UPDATE, or DELETE

    public DataGridUpdate() { 
    	
    }
    public DataGridUpdate(String json, Number id, String type) {
        this.json = json;
        this.id = id;
        this.type = type;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static DataGridUpdateBuilder builder() {
        return new DataGridUpdateBuilder();
    }

    public static class DataGridUpdateBuilder {
        private String json;
        private Number id;
        private String type;

        public DataGridUpdateBuilder json(String json) {
            this.json = json;
            return this;
        }

        public DataGridUpdateBuilder id(Number id) {
            this.id = id;
            return this;
        }

        public DataGridUpdateBuilder type(String type) {
            this.type = type;
            return this;
        }

        public DataGridUpdate build() {
            return new DataGridUpdate(json, id, type);
        }
    }
}