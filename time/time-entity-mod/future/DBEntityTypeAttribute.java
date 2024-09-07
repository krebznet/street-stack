package com.dunkware.time.entity.mod.entity;

import com.dunkware.utils.core.data.DataType;
import com.dunkware.utils.core.format.FormatType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "entity_type_attribute")
public class DBEntityTypeAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String label;

    @Enumerated(EnumType.STRING)
    private DataType dataType;

    @Enumerated(EnumType.STRING)
    private FormatType formatType;

    private int version;

    @ManyToOne
    @JoinColumn(name = "entity_type_id", nullable = false)
    private DBEntityType entityType;

    // Constructors, getters, and setters
    public DBEntityTypeAttribute() {}

    public DBEntityTypeAttribute(String name, String label, DataType dataType, FormatType formatType, int version) {
        this.name = name;
        this.label = label;
        this.dataType = dataType;
        this.formatType = formatType;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public FormatType getFormatType() {
        return formatType;
    }

    public void setFormatType(FormatType formatType) {
        this.formatType = formatType;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public DBEntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(DBEntityType entityType) {
        this.entityType = entityType;
    }
}