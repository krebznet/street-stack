package com.dunkware.stream.data.test.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dunkware.stream.data.cassy.entity.stats.EntityStatsRow;
import com.dunkware.stream.data.cassy.helpers.EntityStatsRowHelper;
import com.dunkware.stream.data.cassy.repository.stats.EntityStatsRepo;
import com.dunkware.stream.data.model.stats.entity.EntityStatsModel;

@RestController
public class QueryController {

	
	private EntityStatsRepo statsRepo;
	
	public QueryController(EntityStatsRepo statsRepo) {
		this.statsRepo = statsRepo; 
	}
	
	@GetMapping(path = "/query/stats/entity/date")
	public @ResponseBody EntityStatsModel entityDateStats(@RequestParam int stream, @RequestParam int entity, @RequestParam String date) throws Exception  {
		try {
			LocalDate dateParsed = LocalDate.parse(date);
			List<EntityStatsRow> results = statsRepo.findByKeyStreamAndKeyEntityAndKeyDate	(stream, entity, dateParsed);
			if(results.size() == 0) { 
				throw new ResponseStatusException(HttpStatus.NOT_EXTENDED, "Query Returned 0 Results");
			}
			return EntityStatsRowHelper.toModel(results.get(0));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Exception Querying " + e.toString());
		}
		
		
	}
}
