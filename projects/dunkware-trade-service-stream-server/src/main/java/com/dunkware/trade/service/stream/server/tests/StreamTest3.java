package com.dunkware.trade.service.stream.server.tests;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.dunkware.common.spec.locale.DCountry;
import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.stream.protocol.controller.AddStreamReq;
import com.dunkware.trade.service.stream.protocol.controller.spec.StreamSpec;
import com.dunkware.xstream.xproject.XScriptProject;
import com.dunkware.xstream.xproject.bundle.XscriptBundleHelper;
import com.dunkware.xstream.xproject.model.XScriptBundle;

/**
 * Builds/serializes stream spec
 * @author dkrebs
 *
 */
@Component
@Profile("StreamTest3")
public class StreamTest3 {

	@PostConstruct
	private void load() { 
	
		try {
			StreamSpec spec = new StreamSpec();
			spec.setName("us_equity");
			XScriptBundle bundle = XscriptBundleHelper.createBundleFromFilePaths("/Users/dkrebs/dunkware/dunkware-trade-xstream-equity");
		//	XScriptBundle bundle = XscriptBundleHelper.createBundleFromFilePaths("/Users/dkrebs/dunkdev/workspaces/release-major/data/dunkware-trade-xstream-smoke");
		
			spec.setSchedule(true);
			
			spec.setCountry(DCountry.US);
			spec.setBundle(bundle);
			spec.setDays("1,2,3,4,5");
			spec.setStartTime(DTime.of(9, 29, 0));
			spec.setStopTime(DTime.of(18, 0, 0));
			spec.setTickers("Test5");
			spec.setTimeZone(DTimeZone.NewYork);
			spec.setVersion(1.0);
		
			AddStreamReq req = new AddStreamReq();
			
			req.setSpec(spec);;
			XScriptProject d = new XScriptProject(bundle);
			System.out.println(DJson.serializePretty(req));
			System.exit(-1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
			
		}
		
		
	}
}
