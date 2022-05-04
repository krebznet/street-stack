package com.dunkware.trade.service.stream.server.tests;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.dunkware.common.spec.locale.DCountry;
import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.stream.json.controller.AddStreamReq;
import com.dunkware.trade.service.stream.json.controller.spec.StreamSpec;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStartReq;
import com.dunkware.xstream.core.extensions.TimeUpdaterExtType;
import com.dunkware.xstream.xproject.XScriptProject;
import com.dunkware.xstream.xproject.bundle.XscriptBundleHelper;
import com.dunkware.xstream.xproject.model.XScriptBundle;
import com.dunkware.xstream.xproject.model.XStreamBundle;
import com.dunkware.xstream.xproject.model.XStreamExtensionType;

/**
 * Builds/serializes stream spec
 * @author dkrebs
 *
 */
@Component
@Profile("StreamTest3")
public class StreamTest3 {

	public static void main(String[] args) {
		StreamTest3 test3 = new StreamTest3();
		test3.load();
	}
	@PostConstruct
	private void load() { 
		
		try {
			StreamSpec spec = new StreamSpec();
			spec.setName("us_equity");
			XScriptBundle bundle = XscriptBundleHelper.createBundleFromFilePaths("/Users/duncankrebs/dunkware/street/script/main/dunkware-street-script");
		
			spec.setSchedule(true);
			
			spec.setCountry(DCountry.US);
			spec.setBundle(bundle);
			spec.setDays("1,2,3,4,5");
			spec.setStartTime(DTime.of(9, 29, 0));
			spec.setStopTime(DTime.of(18, 0, 0));
			spec.setTickers("Alpha3500");
			spec.setTimeZone(DTimeZone.NewYork);
			spec.setVersion(1);
			spec.setSchedule(false);
			AddStreamReq req = new AddStreamReq();
			req.setSpec(spec);;
			XScriptProject d = new XScriptProject(bundle);
			System.out.println(DJson.serialize(req));
			System.exit(-1);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
		
		
	}
	
}
