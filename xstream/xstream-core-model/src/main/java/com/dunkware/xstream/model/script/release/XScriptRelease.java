package com.dunkware.xstream.model.script.release;

import java.time.LocalDateTime;

import com.dunkware.xstream.model.script.descriptor.XScriptDescriptor;

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
	private XScriptDescriptor model;
    private XScriptUpdate update;
    private LocalDateTime timestamp;
    private String script;
}