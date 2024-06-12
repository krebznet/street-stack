package com.dunkware.xstream.model.stats.gen;

import java.time.LocalDate;

import com.dunkware.utils.core.json.DunkJson;
import com.dunkware.xstream.model.stats.proto.EntityStatReqBuilder;

public class EntityStatReqGen {

	
	public static void main(String[] args) {
		try {
			System.out.println(DunkJson.serializePretty(EntityStatReqBuilder.builder().realitveVarHigh("us_equity", "AAPL", "Sma1min", LocalDate.now().minusDays(5), 3).build()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
