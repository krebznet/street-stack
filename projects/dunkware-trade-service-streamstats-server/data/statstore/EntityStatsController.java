package com.dunkware.trade.net.service.streamstats.server.statstore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.DateFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.common.util.time.DunkTime;
import com.dunkware.xstream.model.stats.entity.EntityStats;

@Profile("StreamStore")
@RestController
public class EntityStatsController {

	@Autowired
	StreamEntityStats store;

	@GetMapping(path = "/streamstats/v1/store/delete")
	public double deleteVarStats() throws Exception {
		return store.deleteEntityStats();

	}

	@GetMapping(path = "/streamstats/v1/store/querytest")
	public double queryTest() throws Exception {
		return store.getVarStatesForDate(LocalDate.parse("230801", DateTimeFormatter.ofPattern(DunkTime.YYMMDD)));

	}

	@GetMapping(path = "/streamstats/v1/store/bulktest")
	public double testBulkInsert(@RequestParam() int entities, @RequestParam() int vars, @RequestParam() int stats,
			@RequestParam() String from, @RequestParam() String to) throws Exception {

		LocalDate start = LocalDate.parse(from, DateTimeFormatter.ofPattern(DunkTime.YYMMDD));
		LocalDate end = LocalDate.parse(from, DateTimeFormatter.ofPattern(DunkTime.YYMMDD));

		List<Integer> enttityList = new ArrayList<Integer>();
		int i = 0;
		while (i < entities) {
			enttityList.add(Integer.valueOf(i));
			i++;
		}
		i = 0;
		List<Integer> varList = new ArrayList<Integer>();
		while (i < vars) {
			varList.add(Integer.valueOf(i));
			i++;
		}
		List<Integer> statList = new ArrayList<Integer>();
		i = 0;
		while (i < stats) {
			statList.add(Integer.valueOf(i));
			i++;
		}
		List<EntityStats> results = EntityStatsHelper.creatMockVarStats(start, end, enttityList, varList, statList);

		try {

			return store.insertVarStats(results);

		} catch (Exception e) {
			throw e;
		}

	}
}
