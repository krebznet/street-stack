package com.dunkware.trade.service.stream.server.tests;

import java.time.LocalTime;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.dunkware.trade.service.stream.json.controller.AddStreamReq;
import com.dunkware.trade.service.stream.json.controller.spec.StreamControllerSpec;
import com.dunkware.utils.core.json.DunkJson;
import com.dunkware.utils.core.time.DunkTimeZones;
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

	public static void main(String[] args) {
		StreamTest3 test3 = new StreamTest3();
		test3.load();
	}
	@PostConstruct
	private void load() { 
		
		try {
			StreamControllerSpec spec = new StreamControllerSpec();
			spec.setName("us_equity");
			XScriptBundle bundle = XscriptBundleHelper.createBundleFromFilePaths("/Users/duncankrebs/dunkware/street/script/main/dunkware-street-script");
		
			spec.setSchedule(true);
			
			
			spec.setBundle(bundle);
			spec.setDays("1,2,3,4,5");
			spec.setStartTime(LocalTime.of(9, 29, 0));
			spec.setStopTime(LocalTime.of(18, 0, 0));
			spec.setTickers("Alpha3500");
			spec.setTimeZone(DunkTimeZones.ZoneNewYork);
			spec.setVersion(4);
			spec.setSchedule(false);
			AddStreamReq req = new AddStreamReq();
			req.setSpec(spec);;
			XScriptProject d = new XScriptProject(bundle);
			
			System.out.println(DunkJson.serialize(req));
			System.out.println(DunkJson.serialize(bundle));
			System.exit(-1);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
		
		
	}
	
}
