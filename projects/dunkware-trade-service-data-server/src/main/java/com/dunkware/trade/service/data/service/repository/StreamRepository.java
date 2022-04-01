package com.dunkware.trade.service.data.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dunkware.trade.service.data.service.domain.StreamDO;

public interface StreamRepository extends MongoRepository<StreamDO,String> {

}
