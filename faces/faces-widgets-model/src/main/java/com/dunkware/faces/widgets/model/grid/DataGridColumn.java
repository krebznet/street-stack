package com.dunkware.faces.widgets.model.grid;

public class DataGridColumn {

    private String field;    
    private String header;   
    private String formatType; 
    private String dataType;  

    public DataGridColumn(String field, String header, String formatType, String dataType) {
        this.field = field;
        this.header = header;
        this.formatType = formatType;
        this.dataType = dataType;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFormatType() {
        return formatType;
    }

    public void setFormatType(String formatType) {
        this.formatType = formatType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public static MetaColumnBuilder builder() {
        return new MetaColumnBuilder();
    }

    public static class MetaColumnBuilder {
        private String field;
        private String header;
        private String formatType;
        private String dataType;

        public MetaColumnBuilder field(String field) {
            this.field = field;
            return this;
        }

        public MetaColumnBuilder header(String header) {
            this.header = header;
            return this;
        }

        public MetaColumnBuilder formatType(String formatType) {
            this.formatType = formatType;
            return this;
        }

        public MetaColumnBuilder dataType(String dataType) {
            this.dataType = dataType;
            return this;
        }

        public DataGridColumn build() {
            return new DataGridColumn(field, header, formatType, dataType);
        }
    }
}