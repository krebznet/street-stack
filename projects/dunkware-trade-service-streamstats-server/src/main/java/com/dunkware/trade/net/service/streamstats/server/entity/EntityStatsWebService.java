package com.dunkware.trade.net.service.streamstats.server.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.trade.net.service.streamstats.server.entity.impl.stream.EntityStatMockBean;
import com.dunkware.trade.net.service.streamstats.server.entity.impl.stream.EntityStatMockFactory;
import com.dunkware.xstream.model.stats.entity.EntityStat;

@RestController()
public class EntityStatsWebService {

	@Autowired
	private EntityStatsService service; 
	
	@GetMapping(path = "/streamstats/v1/entity/stats/bean")
	public @ResponseBody EntityStreamStatsBean getStreamStatsBean(@RequestParam() String stream) throws Exception { 
		return service.getStream(stream).getBean();
	}
	
	@GetMapping(path = "/streamstats/v1/entity/stats/mock/insert")
	public @ResponseBody() int mockInsert(@RequestParam() String stream, @RequestParam() String date, @RequestParam() int days, @RequestParam() int entities, @RequestParam() int vars, @RequestParam() int varStats, @RequestParam() int signals) throws Exception  { 
		EntityStreamStats stats = service.getStream(stream);
		EntityStatMockBean bean = new EntityStatMockBean();
		LocalDate start = LocalDate.parse(date, DateTimeFormatter.ofPattern(DunkTime.YYMMDD));
		bean.setStartDate(start);
		bean.setEntityCount(entities);
		bean.setVarCount(vars);
		bean.setVarStatCount(varStats);
		bean.setSignalCount(signals);
		bean.setDayCount(days);
		List<EntityStat> stat = EntityStatMockFactory.build(bean);
		stats.insert(stat);
		return stat.size();
		
	}
	
	@GetMapping(path = "/streamstats/v1/entity/stats/delete")
	public @ResponseBody double delete(@RequestParam() String stream) throws Exception {
		return service.getStream(stream).deleteAll();
		
		
		
	}
	
}
