package com.dunkwrae.trade.service.data.mock.streams;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.net.proto.core.GDataType;
import com.dunkwrae.trade.service.data.mock.input.MockInput;
import com.dunkwrae.trade.service.data.mock.input.MockInputBuilder;
import com.dunkwrae.trade.service.data.mock.stream.MockStreamService;
import com.dunkwrae.trade.service.data.mock.toolkit.signal.TimeIntervalSignal;
import com.dunkwrae.trade.service.data.mock.toolkit.var.IntegerSequenceVar;
import com.dunkwrae.trade.service.data.mock.util.MockMarker;

@Service
@Profile("Stream1")
public class Stream1 {
	
	@Autowired
	private MockStreamService streamService; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@PostConstruct
	public void init() { 
		MockInputBuilder builder = MockInputBuilder.newBuilder("Stream1", 2500, 1000, 1.0);
		builder.schedule(DTimeZone.NewYork, LocalTime.of(9, 30, 0), LocalTime.of(14, 0,0), "1","2","3","4","5","6","7");
		builder.var("var1", "Variable 1", 1, GDataType.INT_TYPE, new IntegerSequenceVar(1,2,3));
		builder.var("var2", "Variable 2", 2, GDataType.INT_TYPE, new IntegerSequenceVar(5,10,14,30));
		builder.signalFactory("Sec1", "1 Second Signal",1,new TimeIntervalSignal(1, TimeUnit.SECONDS));
		//builder.signalFactory("stream3min", "Stream 3 Min", 2, new TimeIntervalSignal(3, TimeUnit.MINUTES));
		try {
			MockInput input = builder.build();
			streamService.newStream(input);
		} catch (Exception e) {
			logger.error(MockMarker.getMarker(), "Crashed in Creating Stream 1" + e.toString(),e	);
			System.err.println("Crashed in creating stream 1 " + e.toString());
			e.printStackTrace();
			System.exit(-1);;
			// TODO: handle exception
		}
	
		
	}

}
