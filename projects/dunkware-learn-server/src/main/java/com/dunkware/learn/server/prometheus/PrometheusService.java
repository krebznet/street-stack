package com.dunkware.learn.server.prometheus;

import java.util.List;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.AtomicDouble;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.prometheus.client.Gauge;

@Component()
public class PrometheusService {

	private MeterRegistry registry; 
	
	private double vlaue = 0;
	Gauge gauge = null;
	public PrometheusService(MeterRegistry registry) { 
		this.registry = registry;
		removeMicroMeterGuage("stream.poop");
		removeMicroMeterGuage("stream.poop");
		registry.gauge("stream.poop",32);
		Thread runner = new Thread() { 
			
			public void run() { 
				try {
					while(true) { 
						Thread.sleep(1000);
						removeMicroMeterGuage("stream.poop");
						PrometheusService.this.registry.gauge("stream.poop", vlaue);
						vlaue++;
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		runner.start();
	}
	
	public void removeMicroMeterGuage(String guageName) {
        List<Meter> meterList = registry.getMeters();
        for(Meter meter : meterList){
            meter.getId().getName();
            if(meter.getId().getName().equalsIgnoreCase(guageName)){
                System.out.println(guageName+" guage removed");
                registry.remove(meter);
            }
        }
    }
	@Scheduled(cron = "1 * * * * *")
	public void random() { 
		vlaue++;
		gauge.set(vlaue);
		
	}
	
	@PostConstruct
	public void load() { 
	}
	
	public class Target { 
		
		public double value;
	}
	
	public class AtomicDoubleFunction implements Function<AtomicDouble, Double> {

		@Override
		public Double apply(AtomicDouble t) {
			return t.doubleValue();
		}

	
	
		
	}

	
}
