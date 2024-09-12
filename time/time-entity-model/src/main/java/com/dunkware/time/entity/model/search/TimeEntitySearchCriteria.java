package com.dunkware.time.entity.model.search;

import com.dunkware.utils.core.data.DataType;

public class TimeEntitySearchCriteria {
	
	 private String attributeName;
	    private DataType dataType;
	    private TimeEntitySearchOperator operator; // Using the renamed enum here
	    private String value; // Value as a string, converted based on dataType

	    // Constructors, getters, and setters

	    public TimeEntitySearchCriteria() {}

	    public TimeEntitySearchCriteria(String attributeName, DataType dataType, TimeEntitySearchOperator operator, String value) {
	        this.attributeName = attributeName;
	        this.dataType = dataType;
	        this.operator = operator;
	        this.value = value;
	    }

	    public String getAttributeName() {
	        return attributeName;
	    }

	    public void setAttributeName(String attributeName) {
	        this.attributeName = attributeName;
	    }

	    public DataType getDataType() {
	        return dataType;
	    }

	    public void setDataType(DataType dataType) {
	        this.dataType = dataType;
	    }

	    public TimeEntitySearchOperator getOperator() {
	        return operator;
	    }

	    public void setOperator(TimeEntitySearchOperator operator) {
	        this.operator = operator;
	    }

	    public String getValue() {
	        return value;
	    }

	    public void setValue(String value) {
	        this.value = value;
	    }

}
