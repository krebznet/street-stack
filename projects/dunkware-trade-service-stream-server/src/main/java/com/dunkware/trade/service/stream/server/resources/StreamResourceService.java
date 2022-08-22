package com.dunkware.trade.service.stream.server.resources;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.StreamControllerService;
import com.dunkware.trade.service.stream.server.repository.StreamScannerEntity;
import com.dunkware.trade.service.stream.server.repository.StreamScannerRepo;
import com.dunkware.xstream.model.scanner.SessionEntityScanner;

@Service()
public class StreamResourceService {

	@Autowired
	private StreamScannerRepo scannerRepo;

	@Autowired
	private StreamControllerService controllerService;
	
	/**
	 * Gets entity scanners
	 * @param identifier
	 * @return
	 * @throws Exception
	 */
	public List<SessionEntityScanner> getStreamEntityScanners(String identifier) throws Exception { 
		List<SessionEntityScanner> results = new ArrayList<SessionEntityScanner>();
		for (StreamScannerEntity entity : scannerRepo.findAll()) {
			if(entity.getStreamIdentifier().equals(identifier))	{ 
				try {
					SessionEntityScanner scanner = DJson.getObjectMapper().readValue(entity.getModel(), SessionEntityScanner.class);
					scanner.setId(entity.getId());
					results.add(scanner);
				} catch (Exception e) {
					throw new Exception("Exception deserializing scanner " + e.toString());
				}
			}
		}
		return results; 
		
	}
	
	public SessionEntityScanner saveOrInsertScanner(SessionEntityScanner scanner) throws Exception { 
		if(scanner.getId() == null) { 
			return insertScanner(scanner);
		}
		return saveScanner(scanner);
	}
	
	
	public void deleteScanner(long scannerId) throws Exception { 
		try {
			Optional<StreamScannerEntity> entity = scannerRepo.findById(scannerId); 
			if(entity.get() == null) { 
				throw new Exception("Scanner not found with id " + scannerId);
			}
			scannerRepo.delete(entity.get());
		} catch (Exception e) {
			throw new Exception("Exception deleting scanner " + e.toString());
		}
		
	}

	public SessionEntityScanner saveScanner(SessionEntityScanner scanner) throws Exception {
		
		StreamController stream = null;
		try {
			stream = controllerService.getStreamByName(scanner.getStreamIdentifier());
		} catch (Exception e) {
			throw new Exception("Stream identifier not found " + scanner.getStreamIdentifier());
		}
		
		StreamScannerEntity entity = getScannerEntityById(scanner.getId().longValue());
		entity.setModel(DJson.serialize(scanner));
		entity.setStreamVersion(stream.getCurrentVersion().getVersion());
		entity.setUpdated(LocalDateTime.now());
		
		try {
			scannerRepo.save(entity);
		} catch (Exception e) {
			throw new Exception("Exception saving scanner " + e.toString());
			// TODO: handle exception
		}
		
		return scanner; 
	}
	
	public SessionEntityScanner insertScanner(SessionEntityScanner scanner) throws Exception {
		StreamScannerEntity entity = null;
		StreamController stream = null;
		try {
			stream = controllerService.getStreamByName(scanner.getStreamIdentifier());
		} catch (Exception e) {
			throw new Exception("Stream identifier not found " + scanner.getStreamIdentifier());
		}
		try {
			entity = new StreamScannerEntity();
			entity.setStreamIdentifier(scanner.getStreamIdentifier());
			entity.setModel(DJson.serialize(scanner));
			entity.setUpdated(LocalDateTime.now());
			entity.setStreamVersion(stream.getCurrentVersion().getVersion());
		} catch (Exception e) {
			throw new Exception("Internal Exception creating entity " + e.toString());
		}
		
		try {
			scannerRepo.save(entity);
			scanner.setId(entity.getId());
		} catch (Exception e) {
			throw new Exception("Exception saving stream entity " + e.toString());
		}
		
		return scanner; 

	}
	
	
	public StreamScannerEntity getScannerEntityById(long id) { 
		for (StreamScannerEntity entity : scannerRepo.findAll()) {
			if(entity.getId() == id) { 
				return entity; 
			}
		}
		return null;
		
	}
	

	
	public void deleteEntityScanner(long id) throws Exception { 
		Optional<StreamScannerEntity> entity = scannerRepo.findById(id);
		if(entity.get() == null) { 
			throw new Exception("Scanner ID " + id + " Not Found");
		}
		scannerRepo.delete(entity.get());
	}

}
