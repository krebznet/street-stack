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
			XScriptBundle bundle = XscriptBundleHelper.createBundleFromFilePaths("/Users/duncankrebs/dunkware/street/xscript");
			
			try {
				String serializedBundle = DJson.serialize(bundle);
				System.out.println(serializedBundle);
				XScriptBundle bundle2 = DJson.getObjectMapper().readValue(serializedBundle, XScriptBundle.class);
				System.out.println(bundle2.getFiles().size());
				XStreamBundle streamBundle = new XStreamBundle();
				streamBundle.setScriptBundle(bundle2);
				streamBundle.setDate(DDate.now());
				streamBundle.setTimeZone(DTimeZone.NewYork);
				String streamSeralized = DJson.serialize(streamBundle);
				XStreamBundle backFromDead = DJson.getObjectMapper().readValue(streamSeralized, XStreamBundle.class);
				TimeUpdaterExtType extType = new TimeUpdaterExtType();
				extType.setTimeZone(DTimeZone.NewYork);
				backFromDead.getExtensions().add(extType);
				String deadSeralized = DJson.serialize(backFromDead);
				System.out.println(deadSeralized);
				XStreamBundle hopeBundle = DJson.getObjectMapper().readValue(deadSeralized, XStreamBundle.class);
				System.out.println(hopeBundle.getScriptBundle().getFiles().size());
				System.out.println(backFromDead.getScriptBundle().getFiles().size());
			} catch (Exception e) {
				e.printStackTrace();
				
				// TODO: handle exception
			}
			
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
