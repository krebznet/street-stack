package com.dunkware.trade.service.data.service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataStreamInstrumentEntityRepo extends CrudRepository<DataStreamInstrumentEntity, Long>{ 

}
