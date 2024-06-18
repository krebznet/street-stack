package com.dunkware.tiime.data.util.mock;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.stream.data.model.stats.entity.EntityStatModel;

public class EntityStatMockData {



	/**
	 * Hello World
	 * @param date
	 * @param stream
	 * @param entities
	 * @param elements
	 * @param stats
	 * @return
	 */
	public static List<EntityStatModel> build(LocalDate date, int stream, int entities, int elements, int... stats) {
		List<EntityStatModel> models = new ArrayList<EntityStatModel>();
		LocalTime time = LocalTime.of(1, 1,1);
		for(int i = 0; i < entities; i++) { 
			for(int x = 0; x < elements; x++) { 
				for(int j = 0; j < stats.length;j++) {
					EntityStatModel m = new EntityStatModel();
					m.setStream(stream);
					m.setEntity(i);
					m.setDate(date);
					m.setElement(x);
					m.setStat(stats[j]);
					m.setValue(i + 32);
					time = time.plusSeconds(1);
					m.setTime(time);
					models.add(m);
				
				}
			}
		}
		return models;
	}
}
