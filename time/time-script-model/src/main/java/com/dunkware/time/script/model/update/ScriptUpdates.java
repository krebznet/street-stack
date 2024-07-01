package com.dunkware.time.script.model.update;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScriptUpdates {

	private List<ScriptUpdate> deltas = new ArrayList<ScriptUpdate>();
	private double oldVersion;
	private double newVersion;
}
