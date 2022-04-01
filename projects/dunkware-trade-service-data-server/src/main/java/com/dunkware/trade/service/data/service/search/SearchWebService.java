package com.dunkware.trade.service.data.service.search;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.net.proto.stream.GEntitySignalQuery;
import com.dunkware.trade.service.data.json.search.EntitySignalSearchQuery;
import com.dunkware.trade.service.data.json.search.EntitySignalSearchResult;
import com.dunkware.trade.service.data.service.util.ProtoPojoUtil;

@RestController
public class SearchWebService {

	@Autowired
	private SearchService searchService; 
	
	@PostMapping(path = "/search/signals")
	public @ResponseBody List<EntitySignalSearchResult> signalSearch(@RequestBody() EntitySignalSearchQuery query) { 
		List<EntitySignalSearchResult> results = new ArrayList<EntitySignalSearchResult>();
		GEntitySignalQuery gQuery = ProtoPojoUtil.signalQueryPojoToProto(query);
		List<Document> docs  = searchService.entitySignalSearch(gQuery);
		if(docs.size() == 0) { 
			EntitySignalSearchResult res = new EntitySignalSearchResult();
			res.setEntityIdentifier("test");
			res.setIdentifier("sig1");
			res.setTime(DDateTime.now());
			results.add(res);
		}
		
		return results;
	}
}
