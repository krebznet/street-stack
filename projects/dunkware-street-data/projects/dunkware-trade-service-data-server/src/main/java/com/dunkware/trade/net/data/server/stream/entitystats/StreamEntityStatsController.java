package com.dunkware.trade.net.data.server.stream.entitystats;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.common.util.time.DunkTime;
import com.dunkware.trade.net.data.server.stream.entitystats.statgen.EntityStatGenBean;
import com.dunkware.trade.net.data.server.stream.entitystats.statgen.EntityStatGenFactory;
import com.dunkware.trade.service.data.model.entitystats.EntityStatAggRequest;
import com.dunkware.trade.service.data.model.entitystats.EntityStatAggResponse;
import com.dunkware.trade.service.data.model.entitystats.EntityStatRequest;
import com.dunkware.trade.service.data.model.entitystats.EntityStatResponse;
import com.dunkware.xstream.model.stats.entity.EntityStat;

@RestController()
public class StreamEntityStatsController {

	@Autowired
	private StreamEntityStatsProvider service; 
	
	@PostConstruct
	public void fuckMan() { 
		System.out.println("stop here");
	}
	
	@GetMapping(path = "/data/v1/stream/entity/stats/bean")
	public @ResponseBody StreamEntityStatsBean getStreamStatsBean(@RequestParam() String stream) throws Exception { 
		return service.getStream(stream).getBean();
	}
	
	@GetMapping(path = "/data/v1/stream/entity/stats/gen")
	public @ResponseBody() int statsGen(@RequestParam() String stream, @RequestParam() String date, @RequestParam() int days, @RequestParam() int entities, @RequestParam() int vars, @RequestParam() int varStats, @RequestParam() int signals) throws Exception  { 
		StreamEntityStats stats = service.getStream(stream);
		EntityStatGenBean bean = new EntityStatGenBean();
		LocalDate start = LocalDate.parse(date, DateTimeFormatter.ofPattern(DunkTime.YYMMDD));
		bean.setStartDate(start);
		bean.setEntityCount(entities);
		bean.setVarCount(vars);
		bean.setVarStatCount(varStats);
		bean.setSignalCount(signals);
		bean.setDayCount(days);
		List<EntityStat> stat = EntityStatGenFactory.build(bean);
		stats.persist(stat);
		return stat.size();	
	}
	
	
	/**
	 * Entity Stat Agg Request = "Get me the highest value of the moving 30 second trade count
	 * variable on this stock Apple Corporation from the last 10 days of this Date. A day is
	 * better said as a "Session" the market is not open on the weekend, we will make life easy
	 * and just get the last 10 sessions, if we do not have enough historicla data then we return
	 * the response as unresolved. 
	 * @param stream
	 * @param date
	 * @param sessionsBack
	 * @param entity
	 * @param stat
	 * @param element
	 * @return
	 */
	@GetMapping(path = "/data/v1/stream/entity/agg/request")
	public @ResponseBody() EntityStatAggResponse statAggRequest(@RequestParam() String stream, @RequestParam() String date, @RequestParam() int lookback,
			@RequestParam() int entity, @RequestParam() int stat, @RequestParam int element) throws Exception { 
		// create the request from the params 
		EntityStatAggRequest request = new EntityStatAggRequest();
		LocalDate dateParsed = LocalDate.parse(date, DateTimeFormatter.ofPattern(DunkTime.YYMMDD));
		request.setDate(dateParsed);
		request.setEntity(entity);
		request.setElement(element);
		request.setStat(stat);
		request.setDaysBack(lookback);
		
		// now the service to do this for us.... 
		return this.service.getStream(stream).statAgg(request);
		
		
	}
	
	/**
	 * Query the database mostly for testing here. 
	 * @param stream
	 * @param date
	 * @param entity
	 * @param element
	 * @param stat
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path = "/data/v1/stream/entity/stat/request")
	public @ResponseBody() EntityStatResponse statRequest(@RequestParam() String stream, @RequestParam() String date, @RequestParam() int entity, @RequestParam() int element, @RequestParam() int stat) throws Exception { 
		EntityStatRequest req = new EntityStatRequest();
		LocalDate dateParsed = LocalDate.parse(date, DateTimeFormatter.ofPattern(DunkTime.YYMMDD));
		req.setDate(dateParsed);
		req.setEntity(entity);
		req.setElement(element);
		req.setStat(stat);
		return this.service.getStream(stream).statRequest(req);
		
	}
	
	
	
}
