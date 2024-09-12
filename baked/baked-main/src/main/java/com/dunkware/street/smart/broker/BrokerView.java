package com.dunkware.street.smart.broker;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;



public class BrokerView {

    private StringProperty name;
    private StringProperty status;
    private StringProperty type;
    private IntegerProperty acccounts;

    private IntegerProperty disconnects;

    private StringProperty connectionDuration;

    private IntegerProperty errors;

    public String getName() {

        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public int getAcccounts() {
        return acccounts.get();
    }

    public IntegerProperty acccountsProperty() {
        return acccounts;
    }

    public void setAcccounts(int acccounts) {
        this.acccounts.set(acccounts);
    }

    public int getDisconnects() {
        return disconnects.get();
    }

    public IntegerProperty disconnectsProperty() {
        return disconnects;
    }

    public void setDisconnects(int disconnects) {
        this.disconnects.set(disconnects);
    }

    public String getConnectionDuration() {
        return connectionDuration.get();
    }

    public StringProperty connectionDurationProperty() {
        return connectionDuration;
    }

    public void setConnectionDuration(String connectionDuration) {
        this.connectionDuration.set(connectionDuration);
    }

    public int getErrors() {
        return errors.get();
    }

    public IntegerProperty errorsProperty() {
        return errors;
    }

    public void setErrors(int errors) {
        this.errors.set(errors);
    }
}

