package com.dunkware.trade.net.service.streamstats.server.entity.impl.stream;

import java.time.LocalDate;
import java.util.List;

import com.dunkware.common.util.json.DJson;
import com.dunkware.xstream.model.stats.entity.EntityStat;

public class EntityStatMockTest {

	public static void main(String[] args) {
		EntityStatMockBean bean = new EntityStatMockBean();
		bean.setDayCount(1);
		bean.setStartDate(LocalDate.now());
		bean.setEntityCount(8000);
		bean.setVarCount(50);
		bean.setVarStatCount(2);
		bean.setSignalCount(15);
		List<EntityStat> stats = EntityStatMockFactory.build(bean);
		for (EntityStat entityStat : stats) {
			try {
			//	System.out.println(DJson.serializePretty(entityStat));
			} catch (Exception e) {
			//	e.printStackTrace();
				// TODO: handle exception
			}
		}
		System.out.println(stats.size());
	}
}
