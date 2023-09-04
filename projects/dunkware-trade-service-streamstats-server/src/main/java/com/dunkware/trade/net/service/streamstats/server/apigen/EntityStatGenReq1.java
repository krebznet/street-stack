package com.dunkware.trade.net.service.streamstats.server.apigen;

import java.time.LocalDate;

import com.dunkware.common.util.json.DJson;
import com.dunkware.xstream.model.stats.proto.EntityStatReq;
import com.dunkware.xstream.model.stats.proto.EntityStatReqType;

public class EntityStatGenReq1 {
	
	public static void main(String[] args) {
		EntityStatReq req = new EntityStatReq();
		req.setStream("us_equity");
		req.setEntity("AAPL");
		req.setType(EntityStatReqType.VarHighRelative);
		req.setTarget("SmaRox1x2min");
		req.setRelativeDays(5);
		req.setDate(LocalDate.now().minusDays(1));
		try {
			System.out.println(DJson.serializePretty(req));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
