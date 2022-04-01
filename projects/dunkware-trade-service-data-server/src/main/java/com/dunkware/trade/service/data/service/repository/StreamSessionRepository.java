package com.dunkware.trade.service.data.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dunkware.trade.service.data.service.domain.StreamDO;

@Repository
public interface StreamSessionRepository extends MongoRepository<StreamDO,String> {

}

