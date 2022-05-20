package com.dunkware.trade.service.data.service.stream.container;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.trade.service.data.service.stream.DataStream;
import com.dunkware.trade.service.data.service.stream.DataStreamService;

@Service()
public class DataStreamContainerService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private DataStreamService streamService;
	
	@PostConstruct
	private void load() { 
		Collection<DataStream> dataStreams = streamService.getStreams();
		for (DataStream dataStream : dataStreams) {
			
		}
	}
	
	private class StreamContainerBuilder implements Runnable {

		
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		} 
		
		
	}
	
}
