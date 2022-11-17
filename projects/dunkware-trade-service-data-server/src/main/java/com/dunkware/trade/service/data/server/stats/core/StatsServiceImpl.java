package com.dunkware.trade.service.data.server.stats.core;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.trade.service.data.server.stats.StatsService;
import com.dunkware.trade.service.data.server.stats.entity.EntityStatsDO;
import com.dunkware.trade.service.data.server.stats.entity.EntityStatsRepo;
import com.dunkware.trade.service.data.server.stats.entity.EntityVarStatsDO;
import com.ibm.icu.math.BigDecimal;

@Service
public class StatsServiceImpl implements StatsService {

	@Autowired
	private EntityStatsRepo statsRepo;

	@PostConstruct
	public void test() {
		
		Thread runner = new Thread() { 
			
			public void run() { 
				
				try {
					System.out.println(LocalTime.now().toString());
					int i = 0;
					sleep(2000);
					while(i < 250000) { 
						EntityStatsDO ent = new EntityStatsDO();
						ent.setDate(LocalDate.now());
						ent.setEntId(39);
						ent.setIdent("HOLA");
						EntityVarStatsDO vstat = new EntityVarStatsDO();
						vstat.setIdent("Sma10sec");
						vstat.setHigh(new BigDecimal(232.3));
						vstat.setHighTime(LocalTime.now());
						vstat.setLow(new BigDecimal(93.3));
						vstat.setLowTime(LocalTime.now().minusHours(3));
						ent.getVars().add(vstat);
						statsRepo.save(ent);		
						
						i++;
					}
					System.out.println(LocalTime.now().toString());
				
				
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}			
				
			}
		};
		runner.start();

	

	}

}
