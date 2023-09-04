package com.dunkware.trade.net.service.streamstats.server.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StreamStatsConfig {

	@Value("${config.stats.streams.idents}")
	private String streamIdents;
	@Value("${config.stats.streams.ids}")
	private String streamIds;
	
	private List<StreamStatsConfigStream> configStreams = new ArrayList<StreamStatsConfigStream>();

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("streamstatsconfig");
	
	@PostConstruct
	private void init() { 
		String[] idents = streamIdents.split(",");
		String[] ids = streamIds.split(",");
		int i = 0;
		try {
			while(i < idents.length) { 
				StreamStatsConfigStream cs = new StreamStatsConfigStream();
				cs.setIdenttifier(idents[i]);
				cs.setId(Integer.valueOf(ids[i]));
				configStreams.add(cs);
			}	
		} catch (RuntimeException e) {
			logger.error(marker, "Exception parsing config streams " + e.toString());
			System.exit(1);
			
		} 	
		
	}
	
	public List<StreamStatsConfigStream> configStreams() { 
		return configStreams;
	}
	
	
	
}
