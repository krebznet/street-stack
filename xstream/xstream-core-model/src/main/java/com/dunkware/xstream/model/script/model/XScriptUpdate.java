package com.dunkware.xstream.model.script.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class XScriptUpdate {

    private List<XScriptChange> changes = new ArrayList<>();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class XScriptChange {

        private String elementName;
        private String elementLabel;
        private int elementId;
        private XScriptElementType elementType;
        private XScriptChangeType changeType;
    }

    public enum XScriptChangeType {
        Update, Insert, Delete
    }

    public enum XScriptElementType {
        Variable, Signal, Bot
    }
}
