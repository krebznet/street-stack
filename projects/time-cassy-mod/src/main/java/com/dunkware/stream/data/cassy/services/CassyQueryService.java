package com.dunkware.stream.data.cassy.services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.stereotype.Service;

import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.dunkware.stream.data.lib.cassy.helpers.CassyQueries;
import com.dunkware.stream.data.model.stats.entity.EntityStatModel;

@Service
public class CassyQueryService {

	@Autowired
	private CqlSessionFactoryBean sessionFactory;

	public List<EntityStatModel> sessionStats(int stream, LocalDate date) {

		List<EntityStatModel> results = new ArrayList<EntityStatModel>();
		String query = CassyQueries.streamSession(stream, date);
		try {
			System.out.println(LocalTime.now());
			SimpleStatement statement =
				    SimpleStatement.builder(query).setFetchSize(500).setTimeout(Duration.ofSeconds(30)).build();
			ResultSet rs = sessionFactory.getObject().execute(statement);
			while(rs.iterator().hasNext()) {
				Row row = rs.iterator().next();
				EntityStatModel model = new EntityStatModel();
				model.setStream(row.getInt("stream"));
				model.setDate(row.getLocalDate("date"));
				model.setEntity(row.getInt("entity"));
				model.setStat(row.getInt("stat"));
				model.setElement(row.getInt("element"));
				model.setValue(row.getDouble("value"));
				results.add(model);
			}
			System.out.println(LocalTime.now());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return results;
	}

}
