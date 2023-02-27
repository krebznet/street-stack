package com.dunkware.trade.service.beach.server.gen;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.beach.protocol.controller.BeachBotAddReq;

import comm.dunkware.trade.service.beach.web.examples.PaperBot1;

public class AddPaperBotGen {
	
	public static void main(String[] args) {
		try {
			BeachBotAddReq req = new BeachBotAddReq();
			req.setAccount("PAPER1");
			req.setBroker("Paper");
			req.setModel(PaperBot1.get());
			req.setName("Bot Man");
			String out = DJson.serializePretty(req);
			System.out.println(out);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
