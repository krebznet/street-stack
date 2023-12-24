package com.dunkware.trade.service.data.common.stats.entity.sql;

import java.time.LocalDate;
import java.util.List;

import com.dunkware.common.util.mysql.pool.MySqlConnectionPool;
import com.dunkware.trade.service.data.common.stats.entity.sql.gen.EntityStatGenBean;
import com.dunkware.trade.service.data.common.stats.entity.sql.gen.EntityStatGenFactory;
import com.dunkware.xstream.model.stats.entity.EntityStat;

public class EntityStatsSQLWriterTest {

	
	
	
	public static void main(String[] args) {
		MySqlConnectionPool pool = null;
		try {
			pool = new MySqlConnectionPool("testrock1.dunkware.net","streamstats",30307,"root","aFSn5bxShHd453bnrBNHEJWQirwrw",10);			
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		EntityStatGenBean b = new EntityStatGenBean();
		b.setDayCount(3);
		b.setStartDate(LocalDate.now().minusDays(3));
		b.setEntityCount(555);
		b.setSignalCount(45);
		b.setVarCount(40);
		b.setVarStatCount(3);
		
		List<EntityStat> stats = EntityStatGenFactory.build(b);
		System.out.println("stats size " + stats.size());
		try {
			EntityStatsSQLWriter writer = EntityStatsSQLWriter.newInstance(pool, 15, "Test1");			
			//writer.createTable(EntityStatsSQLHelper.sqlEntityStatsCreateTable("Test1"));
			
			double time = writer.write(stats);
			System.out.println("wrote " + stats.size() + " stats into table " + "Test1 in " + time);
			pool.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
		

		
	}
}
