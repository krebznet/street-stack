package com.dunkware.trade.service.beach.stream.controllers;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.Data;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;


@RestController
public class StreamController1 {
	
	private List<Trade> stream = new ArrayList<Trade>();
	
	 public static void main(String[] args) {
		//new StreamController1();
	}
	 
	@PostConstruct
	public void setup() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	//	flux = sink.asFlux();
	//	flux = flux.subscribeOn(Schedulers.boundedElastic());
		//Subscriber2 sub = new Subscriber2();
		//flux.subscribe(sub);
		//flux.subscribe(body -> System.out.println(body));
		
	
		
	}
	
	@Data
	public class Trade { 
		private int size;
		private String symbol;
	}
	
	@RequestMapping( path="/test" , produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Trade> getMe() { 
		

		
		 Sinks.Many<Trade> sink = Sinks.many().multicast().onBackpressureBuffer();
		 final Updater updater = new Updater(sink);
		 Flux<Trade> flux = sink.asFlux().doOnCancel(() -> {
			System.out.println("on cancel mother fucker!"); 
			sink.tryEmitComplete();
			try {
				updater.interrupt();	
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			System.gc();
			
		 });
		 
		flux.subscribe(new Subscriber2());
		flux.onErrorStop();
		
		updater.start();	
		
		return flux;
		
	}
	
	public class Subscriber2 implements Subscriber<Trade> {

		private Subscription sub;
		
		@Override
		public void onSubscribe(Subscription s) {
			System.out.println("on subscbscirtion");
		
			s.request(1);
			this.sub = s;
			
		}

		@Override
		public void onNext(Trade t) {
			sub.request(1);;
		//	System.out.println("onNext " + t.toString());
			
		}

		@Override
		public void onError(Throwable t) {
			System.out.println("on error");
		}

		@Override
		public void onComplete() {
			System.out.println(" on complee");
			sub.cancel();
		} 
		
		
	}
	
	
private static ObjectMapper objectMapper = null;
	
	private static boolean initialized = false; 
	
	private static void init() { 
		if(!initialized) { 
			objectMapper = new ObjectMapper();
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			objectMapper.registerModule(new JavaTimeModule());
			initialized = true;
		}
	}
	public static String serialize(Object object) throws JsonMappingException,JsonGenerationException,IOException { 
		init();
		StringWriter writer = new StringWriter();
		objectMapper.writeValue(writer, object);
		

		return writer.toString();
	}

	public static String serializePretty(Object object) throws JsonMappingException,JsonGenerationException,IOException { 
		init();
		String output = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		return output;
	}

	
	

	public static ObjectMapper getObjectMapper() { 
		init();
		return objectMapper;
	}

	
	public class Updater extends Thread { 
		
		 Sinks.Many<Trade> sink;
		
		public Updater( Sinks.Many<Trade> sink) {
			this.sink = sink;
		}
		public void run() {
			int i = 0;
			try {
				while(!interrupted()) { 
					Thread.sleep(12);
					//System.out.println("fuck " + i);
					Trade trade = new Trade();
					trade.setSize(i);
					trade.setSymbol("Hi" + i);
					sink.tryEmitNext(trade);
					
					i++;	
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				return;
				// TODO: handle exception
			}
		}
	}

}
