package com.dunkware.trade.service.stream.server.apigen;

import java.io.StringWriter;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.dtime.DTime;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.stream.json.controller.AddStreamReq;
import com.dunkware.trade.service.stream.json.controller.spec.StreamControllerSpec;
import com.dunkware.xstream.xproject.XScriptProject;
import com.dunkware.xstream.xproject.bundle.XscriptBundleHelper;
import com.dunkware.xstream.xproject.model.XScriptBundle;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Profile("AddStreamReqGen")
public class AddStreamReqGen {

	public static final String DEV1 = "/Users/duncankrebs/dunkware/street/dunkware-stream-us-equity";

	@PostConstruct
	void gen() {
		try {
			XScriptBundle bundle = XscriptBundleHelper.createBundleFromFilePaths(DEV1);
			ObjectMapper mapper = new ObjectMapper();
			StringWriter writer = new StringWriter();
			mapper.writeValue(writer, bundle);

			//XScriptProject project = XscriptBundleHelper.loadProject(bundle);
			
			AddStreamReq req = new AddStreamReq();
			StreamControllerSpec spec = new StreamControllerSpec();
			spec.setBundle(bundle);
			spec.setDays("1,2,3,4,5");
			
			spec.setStartTime(DTime.of(8, 0, 0));
			spec.setStopTime(DTime.of(17, 0, 0));
			spec.setSchedule(true);
			spec.setTimeZone(DTimeZone.NewYork);
			spec.setVersion(1);
			spec.setTickers("Alpha3500");
			spec.setName("us_equity");;
			req.setSpec(spec);
			String out = DJson.serialize(req);
			System.out.println(out);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
