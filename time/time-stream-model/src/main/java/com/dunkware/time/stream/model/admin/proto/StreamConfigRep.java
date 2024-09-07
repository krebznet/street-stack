package com.dunkware.time.stream.model.admin.proto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StreamConfigRep {

	private boolean error;
	private String errorMessage;
	private List<String> deltas = new ArrayList<String>();
	private int revision;

}
