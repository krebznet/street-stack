package com.dunkware.trade.net.data.server.services;


import org.springframework.stereotype.Service;

@Service
public class MongoSequenceService {

	/*
	 * @Autowired private MongoOperations mongoOperations;
	 * 
	 * public long generateSequence(String seqName) { MongoSequenceDoc counter =
	 * mongoOperations.findAndModify(query(where("_id").is(seqName)), new
	 * Update().inc("seq",1), options().returnNew(true).upsert(true),
	 * MongoSequenceDoc.class); return !Objects.isNull(counter) ? counter.getSeq() :
	 * 1; }
	 */
}
