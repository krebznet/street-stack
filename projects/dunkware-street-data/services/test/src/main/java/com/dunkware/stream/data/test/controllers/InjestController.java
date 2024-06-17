package com.dunkware.stream.data.test.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.dunkware.stream.data.cassy.entity.stats.DBSessionEntityStatRow;
import com.dunkware.stream.data.cassy.loaders.SessionEntityStatLoader;
import com.dunkware.stream.data.cassy.mock.SessionEntityStatMockData;
import com.dunkware.stream.data.cassy.repository.stats.SessionEntityStatRepo;
import com.dunkware.stream.data.model.stats.entity.EntityStatModel;
import com.dunkware.stream.data.util.mock.EntityStatMockData;
import com.dunkware.utils.core.stopwatch.StopWatch;

@RestController
public class InjestController {

	private SessionEntityStatRepo statRepo;
	
	private CqlSessionBuilder builder;
	
	public InjestController(SessionEntityStatRepo statRepo, CqlSessionBuilder builder) { 
		this.statRepo = statRepo;
		this.builder = builder;
	}
	
	public void init() { 
		
	}
	
	@GetMapping(path = "/test/loader")
	public String testLoader(@RequestParam int entities, @RequestParam int elements) { 
		SessionEntityStatLoader loader = new SessionEntityStatLoader(builder.build(),5,1500);
		List<EntityStatModel> data = EntityStatMockData.build(LocalDate.of(2024, 1, 16),1, entities,elements,1,2,3,4);
		System.out.println("inserting " + data.size());
		StopWatch stop = StopWatch.newInstance();
	
		stop.start();
		loader.injest(data);
		while(loader.queueSize() > 0) { 
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		stop.stop();
		return String.valueOf(stop.seconds());
	
	}
	
	@GetMapping(path = "/injest/session/stat")
	public String injestSessionEntityStatData(@RequestParam String date, @RequestParam int entities, @RequestParam int elements, @RequestParam int stats) { 
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy").withLocale(Locale.ENGLISH);

		LocalDate parseLocalDate = LocalDate.parse(date);
	//	List<SessionEntityStatRow> data = SessionEntityStatMockData.build(parseLocalDate, 1, elements, entities, stats, 1,2);
		//List<SessionEntityStatRow> data = SessionEntityStatMockData.build(LocalDate.of(2024, 1, 1),1,5000,1,1,2);
		List<DBSessionEntityStatRow> data = SessionEntityStatMockData.build(LocalDate.of(2024, 1, 22),1,entities,elements,1,2);
		
		System.out.println(data.size());
		try {
			StopWatch watch = StopWatch.newInstance();
			System.out.println("inserting " + data.size() + " rows");
			watch.start();
			statRepo.ingest(data);
			watch.stop();
			return "Inserted " + data.size() + " in " + watch.seconds() + " seconds";

		} catch (Exception e) {
			return e.toString();
		}
		
	
		//150,0000
	}
}
