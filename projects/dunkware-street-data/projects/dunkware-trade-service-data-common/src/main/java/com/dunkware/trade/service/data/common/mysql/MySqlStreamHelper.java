package com.dunkware.trade.service.data.common.mysql;

public class MySqlStreamHelper {

	

	public static String sqlEntityStatsCreateTable(String tablename) { 
		return "CREATE TABLE" +  "`" + tablename + "`" +  "(\n"
				+ "  `id` int NOT NULL AUTO_INCREMENT,\n"
				+ "  `date` datetime NOT NULL,\n"
				+ "  `ent` int NOT NULL,\n"
				+ "  `element` int NOT NULL,\n"
				+ "  `stat` int NOT NULL,\n"
				+ "  `value` decimal(15,2) NOT NULL,\n"
				+ "  `time` time DEFAULT NULL,\n"
				+ "  PRIMARY KEY (`id`),\n"
				+ "  INDEX (`date`, `ent`, `stat`) )\n"
				+ "ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci	\n"
				+ "  PARTITION BY Key()\n"
				+ "PARTITIONS 5;\n"
				+ "  ";
		
		
	}
}
