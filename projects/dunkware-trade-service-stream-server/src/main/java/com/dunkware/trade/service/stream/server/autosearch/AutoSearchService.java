package com.dunkware.trade.service.stream.server.autosearch;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.stream.server.autosearch.engine.json.JsonSearchCategory;
import com.dunkware.trade.service.stream.server.autosearch.engine.json.JsonSearchElement;
import com.dunkware.trade.service.stream.server.autosearch.engine.json.JsonSearchResults;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.StreamControllerService;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.xstream.xScript.SignalType;
import com.dunkware.xstream.xproject.XScriptProject;

import ch.qos.logback.classic.Logger;

@Service
@Profile("AutoSearch")
public class AutoSearchService {

	@Autowired
	private ApplicationContext ac; 
	
	@Autowired
	private StreamControllerService streamController; 
	
	private List<JsonSearchCategory> categories = new ArrayList<JsonSearchCategory>();
	
	
	public AutoSearchService() { 
		
	}
	
	
	@PostConstruct
	public void start() throws Exception { 
		// keep it simple
		// do all securities on single stream 
		// create instrument category 
		JsonSearchCategory cat = new JsonSearchCategory();
		cat.setCategoryName("Instruments");
		cat.setCategoryType("instruments");
		for (StreamController controller : streamController.getStreams()) {
			for (TradeTickerSpec spec :controller.getTickers()) {
				JsonSearchElement el = new JsonSearchElement();
				el.setId(spec.getId());
				el.setIdentifier(spec.getSymbol());
				el.setName(spec.getName());
				cat.getElements().add(el);
			}
		}
		categories.add(cat);
		
		JsonSearchCategory signalCat = new JsonSearchCategory();
		for (StreamController controller : streamController.getStreams()) {
			XScriptProject project = controller.getScriptProject();
			for (SignalType sigType : project.getStreamSignals()) {
				JsonSearchElement el = new JsonSearchElement();
				el.setId(sigType.getId());
				el.setIdentifier(sigType.getName());
				el.setName(sigType.getName());
				signalCat.getElements().add(el);
			}
		}
	
		categories.add(signalCat);
	}
	
	
	
	public synchronized String response(String input) throws Exception  { 
		JsonSearchResults results = new JsonSearchResults();
		if(input.length() < 1) { 
			// empty string 
			try {
				return DJson.serializePretty(results);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		for (JsonSearchCategory cats : categories) {
			JsonSearchCategory resultCat = new JsonSearchCategory();
			resultCat.setCategoryName(cats.getCategoryName());
			resultCat.setCategoryType(cats.getCategoryType());
			for (JsonSearchElement element : cats.getElements()) {
				boolean added = false; 
				if(element.getIdentifier().startsWith(input) || element.getIdentifier().toLowerCase().startsWith(input)|| element.getIdentifier().toUpperCase().startsWith(input) == true) {
						resultCat.getElements().add(element);
						added = true; 
				}
				if(element.getName().startsWith(input) == true || element.getName().toLowerCase().startsWith(input) == true || element.getName().toUpperCase().startsWith(input) == true) { 
					if(!added) { 
						resultCat.getElements().add(element);
						added = true;
					}
				}
			}
			if(resultCat.getElements().size() > 0) { 
				results.getCategories().add(resultCat);
			}
		}
		try {
			String resultString = DJson.serializePretty(results);
			System.out.println(resultString);
			return resultString;
		} catch (Exception e) {
			return e.toString();
		}
		
		
		
	}
}
