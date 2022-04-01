package com.dunkware.trade.service.data.service.search;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Service;

import com.dunkware.net.proto.stream.GEntitySignalQuery;

@Service
public class SearchService {

	// TODO: autowire in mongo connection? 
	
	
	public List<Document> entitySignalSearch(GEntitySignalQuery query) { 
		// call the concept query builder and run it; 
		return new ArrayList<Document>();
		
	}
	
}
