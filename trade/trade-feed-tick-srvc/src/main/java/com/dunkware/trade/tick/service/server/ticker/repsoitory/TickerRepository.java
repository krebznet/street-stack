package com.dunkware.trade.tick.service.server.ticker.repsoitory;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface TickerRepository extends CrudRepository<TickerDO, Long> {

	
	public List<TickerDO> findBySymbol(String symbol);
	
	

}
