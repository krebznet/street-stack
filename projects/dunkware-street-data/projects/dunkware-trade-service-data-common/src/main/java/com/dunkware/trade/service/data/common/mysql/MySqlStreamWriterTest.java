package com.dunkware.trade.service.data.common.mysql;

import java.time.LocalDate;
import java.util.List;

import com.dunkware.common.util.mysql.pool.MySqlConnectionPool;
import com.dunkware.trade.service.data.common.factory.EntityStatFactoryInput;
import com.dunkware.trade.service.data.common.factory.EntityStatFactory;
import com.dunkware.xstream.model.stats.entity.EntityStat;

public class MySqlStreamWriterTest {

	
	
	
	public static void main(String[] args) {
		MySqlConnectionPool pool = null;
		try {
			pool = new MySqlConnectionPool("testrock1.dunkware.net","streamstats",30307,"root","aFSn5bxShHd453bnrBNHEJWQirwrw",10);			
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		EntityStatFactoryInput b = new EntityStatFactoryInput();
		b.setDayCount(3);
		b.setStartDate(LocalDate.now().minusDays(3));
		b.setEntityCount(555);
		b.setSignalCount(45);
		b.setVarCount(40);
		b.setVarStatCount(3);
		
		List<EntityStat> stats = EntityStatFactory.build(b);
		System.out.println("stats size " + stats.size());
		try {
			MySqlStreamWriter writer = MySqlStreamWriter.newInstance(pool, 15, "Test1");			
			//writer.createTable(EntityStatsSQLHelper.sqlEntityStatsCreateTable("Test1"));
			
			double time = writer.write(stats);
			System.out.println("wrote " + stats.size() + " stats into table " + "Test1 in " + time);
			pool.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
		

		
	}
}
