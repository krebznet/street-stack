package com.dunkwrae.trade.service.data.mock.stream;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.common.util.dtime.DTime;
import com.dunkware.trade.service.data.json.mock.DataMockStreamStats;
import com.dunkware.trade.service.data.json.mock.rest.DataMockStreamStopResponse;
import com.dunkwrae.trade.service.data.mock.stream.session.MockSession;

@RestController
public class MockStreamWebService {

	@Autowired
	private MockStreamService streamService;

	@PostConstruct
	public void load() {

	}

	@GetMapping(path = "/getstreams")
	public @ResponseBody() List<DataMockStreamStats> getStreams() {
		List<DataMockStreamStats> stats = new ArrayList<DataMockStreamStats>();
		for (MockStream stream : streamService.getStreams()) {
			DataMockStreamStats stat = new DataMockStreamStats();
			stat.setDays(stream.getInput().getSchedule().getDays().toString());
			stat.setIdentifier(stream.getInput().getStreamIdentifier());
			stat.setStartTime(DTime.from(stream.getInput().getSchedule().getStart()));
			stat.setStopTime(DTime.from(stream.getInput().getSchedule().getStop()));
			stat.setRunning(stream.isRunning());
			stats.add(stat);
		}

		return stats;
	}

	@GetMapping(path = "/startstream")
	public @ResponseBody DataMockStreamStopResponse startStream(@RequestParam() String stream) throws Exception { 
		DataMockStreamStopResponse resp = new DataMockStreamStopResponse();
		MockStream mockStream = null;
		MockSession mockSession = null;
		try {
			mockStream = streamService.getStream(stream);
			if(mockStream == null) { 
				resp.setError("Stream " + stream + " Not Found");
				return resp;
			}
		} catch (Exception e) {
			resp.setError(e.toString());
			return resp;
			
		}
		if(mockStream.isRunning()) { 
			resp.setError("Stream " + stream + " Is Already Running");
			return resp;
		}
		try {
			mockSession = mockStream.startSession();
		} catch (Exception e) {
			resp.setError("Exception starting session " + e.toString());
			return resp;
		}
		resp.setSessionId(mockSession.getSessionId());
		return resp;
	}

	@GetMapping(path = "/stopstream")
	public @ResponseBody DataMockStreamStopResponse stopStream(@RequestParam() String stream) throws Exception {
		
		try {
			MockStream strema = streamService.getStream(stream);
			strema.stopSession();
			DataMockStreamStopResponse resp = new DataMockStreamStopResponse();
			resp.setStatus("Stopped");
			return resp;
		} catch (Exception e) {
			DataMockStreamStopResponse resp = new DataMockStreamStopResponse();
			resp.setError(e.toString());;
			return resp;
		}

	}

}
