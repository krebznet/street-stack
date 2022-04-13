package com.dunkware.trade.service.data.service.stream;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.trade.service.data.service.config.RuntimeConfig;
import com.dunkware.trade.service.data.service.message.StreamMessageHandler;
import com.dunkware.trade.service.data.service.message.StreamMessageService;
import com.dunkware.trade.service.data.service.repository.DataServiceRepository;
import com.dunkware.trade.service.data.service.repository.DataStreamEntity;
import com.dunkware.trade.service.stream.json.message.StreamSessionPing;
import com.dunkware.trade.service.stream.json.message.StreamSessionStart;
import com.dunkware.trade.service.stream.json.message.StreamSessionStop;

@Service
public class DataStreamService implements StreamMessageHandler {
	
	
	@Autowired
	private RuntimeConfig configService; 

	@Autowired
	private ApplicationContext ac;
	
	@Autowired
	private StreamMessageService messageService;

	@Autowired
	private DataServiceRepository dataRepo;
	
	private Map<String,DataStream> dataStreams = new ConcurrentHashMap<String, DataStream>();

	private Logger logger = LoggerFactory.getLogger(getClass());
	

	@PostConstruct
	public void load() {
		
		messageService.addHandler(this);
		try {
		
			for (DataStreamEntity ent : dataRepo.getDataStreamEntities()) {
				System.out.println(ent.getName());
			}
			
		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	@Override
	public void sessionPing(StreamSessionPing ping) {
		
	}
	
	@Override
	public void sessionStart(StreamSessionStart start) {
		
	}
	
	@Override
	public void sessionStop(StreamSessionStop stop) {
		
	}
	
	


	

}

