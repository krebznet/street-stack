package com.dunkware.trade.service.stream.server.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.stream.server.repository.StreamScannerEntity;
import com.dunkware.trade.service.stream.server.repository.StreamScannerRepo;
import com.dunkware.trade.service.stream.web.workspace.SavedSessionEntitySearch;

/**
 * Used for the stream workspace in angular 
 * @author duncankrebs
 *
 */
@Service()
public class StreamWorkspaceService {
	
	
	@Autowired
	private StreamControllerService streamService; 
	
	@Autowired
	private StreamScannerRepo entitySearchRepo;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	
	public List<SavedSessionEntitySearch> getSavedEntitySearches(String streamIdentifier) throws Exception { 
		List<SavedSessionEntitySearch> results = new ArrayList<SavedSessionEntitySearch>();
		
		for (StreamScannerEntity search : entitySearchRepo.findAll()) {
			if(search.getStreamIdentifier().equals(streamIdentifier)) { 
				try {
					
					SavedSessionEntitySearch savedSearch = DJson.getObjectMapper().readValue(search.getModel(), SavedSessionEntitySearch.class);
					results.add(savedSearch);
					
				} catch (Exception e) {
					logger.error("Exception deserializing saved entity search " + e.toString());
				}
			}
		}
		
		return results;
	}
	
	
	public SavedSessionEntitySearch saveEntitySearch(SavedSessionEntitySearch search) throws Exception { 
		
		Optional<StreamScannerEntity> went = entitySearchRepo.findById(search.getId());
		if(went.get() == null) { 
			throw new Exception("Saved Search ID " + search.getId() + " not found");
		}
		went.get().setModel(DJson.serialize(search));
		went.get().setStreamIdentifier(search.getStreamIdentifier());
		double v = streamService.getStreamByName(search.getStreamIdentifier()).getCurrentVersion().getVersion();
		went.get().setStreamVersion(v);
		entitySearchRepo.save(went.get());
		return search;
	}
	
	public SavedSessionEntitySearch getSavedEntitySearch(String streamIdent, long id) throws Exception  {
		return null;
	}
	
	public SavedSessionEntitySearch insertEntitySearch(SavedSessionEntitySearch search) throws Exception { 
		StreamScannerEntity went = new StreamScannerEntity();
		went.setModel(DJson.serialize(search));
		went.setStreamIdentifier(search.getStreamIdentifier());
		double v = streamService.getStreamByName(search.getStreamIdentifier()).getCurrentVersion().getVersion();
		went.setStreamVersion(v);
		entitySearchRepo.save(went);
		search.setId(went.getId());
		return search;
	}
	
	public void deleteEntitySearch(String streamIdentifier, long searchId) throws Exception { 
		
	}

}
