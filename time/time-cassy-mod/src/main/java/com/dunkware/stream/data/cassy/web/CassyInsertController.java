package com.dunkware.stream.data.cassy.web;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.stream.data.cassy.entity.EntityStats;
import com.dunkware.stream.data.cassy.entity.EntityStatsKey;
import com.dunkware.stream.data.cassy.entity.EntityVarStats;
import com.dunkware.stream.data.cassy.repository.EntityStatsRepository;
import com.dunkware.stream.data.cassy.writers.SignalWriter;
import com.dunkware.time.data.dto.entity.EntitySignalDTO;
import com.dunkware.time.data.dto.entity.SignalGenerator;

import jakarta.annotation.PostConstruct;

@RestController
public class CassyInsertController {

	
	private EntityStatsRepository statsRepo;
	
	private CqlSessionFactoryBean  factoryBean;
	
	public CassyInsertController(EntityStatsRepository statRepo, CqlSessionFactoryBean bean) { 
		statsRepo = statRepo;
		factoryBean = bean;
	}
	
	
	@PostConstruct
	public void init() {
		System.out.println("init");
	}
	@GetMapping(path = "/test/insert")
	public void testInsert() { 
		try {
			SignalWriter writer = new SignalWriter(factoryBean.getObject());
			try {
				List<EntitySignalDTO> signals = SignalGenerator.generateSignals(1, 101, 202, 50, LocalDate.of(2023, 7, 11), LocalTime.of(14, 55, 0), 1000000);
				System.out.println("start");
				writer.writeSignals(signals);				
				System.out.println("stop");
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

			
			
			EntityStats stats = new EntityStats();
			EntityStatsKey key = new EntityStatsKey(0, 1, LocalDate.now());
			stats.setKey(key);
			Map<Integer,Integer> fuck = new ConcurrentHashMap<Integer,Integer>();
			fuck.put(1, 1);
			stats.setSignals(fuck);
			Map<Integer,EntityVarStats> vars = new ConcurrentHashMap<Integer,EntityVarStats>();
			Map<Integer, Double> statMap = new ConcurrentHashMap<Integer, Double>();
			statMap.put(1, 33.3);
			EntityVarStats varStats = new EntityVarStats();
			varStats.setStats(statMap);
			varStats.setTimes(new ConcurrentHashMap<Integer, LocalTime>());
			vars.put(1, varStats);
			stats.setVars(vars);
			try {
				statsRepo.save(stats);
				System.out.println("say what?");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
