package com.dunkware.trade.service.stream.server.blueprint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dunkware.trade.service.stream.json.blueprint.WebBlueprintSignal;

@RestController
public class StreamBlueprintWebService {

	@Autowired
	private StreamBlueprintService blueprintService; 
	
	@GetMapping("/stream/v1/blueprint/signal/add")
	public void addSignalType(@RequestBody WebBlueprintSignal model, @RequestParam String streamIdent) { 
		StreamBlueprint blueprint = null;
		try {
			blueprint = blueprintService.getBlueprint(streamIdent);
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, "Stream Ident not found " + streamIdent);
		}
		
		try {
			blueprint.addSignal(model);
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, e.toString());

		}
	}
	
	
	
}
