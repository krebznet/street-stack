package com.dunkware.trade.service.data.service.stream;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.trade.service.data.service.config.RuntimeConfig;
import com.dunkware.trade.service.data.service.message.StreamMessageService;
import com.dunkware.trade.service.data.service.repository.DataServiceRepository;
import com.dunkware.trade.service.data.service.repository.DataStreamEntity;

@Service
public class DataStreamService {
	
	
	@Autowired
	private RuntimeConfig configService; 

	@Autowired
	private ApplicationContext ac;
	
	@Autowired
	private StreamMessageService messageService;

	@Autowired
	private DataServiceRepository dataRepo;

	private Logger logger = LoggerFactory.getLogger(getClass());
	

	@PostConstruct
	public void test() { 
		try {
			int i = 0;
			while(i < 100) { 
				DataStreamEntity ent = new DataStreamEntity();
				ent.setCreated(LocalDateTime.now());
				ent.setName("Test name");
				dataRepo.persist(ent);	
				i++;
			}
			
		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	


	

}

