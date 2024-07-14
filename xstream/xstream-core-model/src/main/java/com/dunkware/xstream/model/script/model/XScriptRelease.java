package com.dunkware.xstream.model.script.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class XScriptRelease {
	private String name;
	private String type;
	private String version;
	private XScriptModel model;
    private XScriptUpdate update;
    private LocalDateTime timestamp;
    private String script;
}