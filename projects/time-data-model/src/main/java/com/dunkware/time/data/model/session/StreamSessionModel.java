package com.dunkware.time.data.model.session;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class StreamSessionModel {


    private int stream;
    private LocalDate date;
    private LocalTime start;
    private LocalTime stop;
    private List<Integer> entities = new ArrayList<Integer>();
    private List<Integer> vars = new ArrayList<Integer>();
    private List<Integer> signals = new ArrayList<Integer>();
    private List<Integer> stats = new ArrayList<Integer>();


    public StreamSessionModel() {
    }

    public StreamSessionModel(int stream, LocalDate date, LocalTime start, LocalTime stop, List<Integer> entities, List<Integer> vars, List<Integer> signals, List<Integer> stats) {
        this.stream = stream;
        this.date = date;
        this.start = start;
        this.stop = stop;
        this.entities = entities;
        this.vars = vars;
        this.signals = signals;
        this.stats = stats;
    }

    public int getStream() {
        return stream;
    }

    public void setStream(int stream) {
        this.stream = stream;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getStop() {
        return stop;
    }

    public void setStop(LocalTime stop) {
        this.stop = stop;
    }

    public List<Integer> getEntities() {
        return entities;
    }

    public void setEntities(List<Integer> entities) {
        this.entities = entities;
    }

    public List<Integer> getVars() {
        return vars;
    }

    public void setVars(List<Integer> vars) {
        this.vars = vars;
    }

    public List<Integer> getSignals() {
        return signals;
    }

    public void setSignals(List<Integer> signals) {
        this.signals = signals;
    }

    public List<Integer> getStats() {
        return stats;
    }

    public void setStats(List<Integer> stats) {
        this.stats = stats;
    }
}
