package com.dunkware.utils.core.eventlogger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventLog {
    private LocalDateTime timestamp;
    private List<Tag> tags = new ArrayList<>();
    private Map<String, Object> values = new ConcurrentHashMap<>();
    private String logger;
    private String type;
    private String message;

    public EventLog() {
    }

    public EventLog(LocalDateTime timestamp, List<Tag> tags, Map<String, Object> values, String logger, String type, String message) {
        this.timestamp = timestamp;
        this.tags = tags;
        this.values = values;
        this.logger = logger;
        this.type = type;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Map<String, Object> getValues() {
        return values;
    }

    public void setValues(Map<String, Object> values) {
        this.values = values;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
