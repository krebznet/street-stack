package com.dunkware.stream.data.test.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;


public class ReactController {
	

	  @GetMapping("/poop")
	  public Flux<String> test() {
		  
	    return Flux.create(this::process);
	  }

	  private void process(final FluxSink<String> sink) {
		  
	    sink.onRequest(i -> {
	      
	    	for (int j = 0; j < i; j++) {
	        sink.next("Hello world" + j);
	      }
	    });

	    sink.onCancel(() -> System.out.println("Flux Canceled"));

	    sink.onDispose(() -> System.out.println("Flux dispose"));
	  }

}
