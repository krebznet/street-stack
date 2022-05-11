package com.dunkware.net.cluster.node.metrics;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;

@Service()
public class MetricsService {

	@Autowired
	MeterRegistry registry; 
	
	
	public MetricsService(MeterRegistry registry) { 
		registry = this.registry;
		
	}
	
	public void setGauge(String name, long value) { 
		registry.gauge(name, value);
	}
	
	
	public void removeMeter(String name) {
        List<Meter> meterList = registry.getMeters();
        for(Meter meter : meterList){
            meter.getId().getName();
            if(meter.getId().getName().equalsIgnoreCase(name)){
                registry.remove(meter);
            }
        }
    }
}
