package com.dunkware.time.stream.model.admin.proto;

import com.dunkware.time.stream.model.admin.config.StreamConfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StreamConfigReq {
	
	
	private StreamConfig config;

}
