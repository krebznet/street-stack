package com.dunkware.xstream.model.script.model;

import com.dunkware.xstream.model.script.update.XScriptUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class XScriptRelease {
    private String version;
    private XScriptUpdate update;
    private XScriptModel mergedModel;
}