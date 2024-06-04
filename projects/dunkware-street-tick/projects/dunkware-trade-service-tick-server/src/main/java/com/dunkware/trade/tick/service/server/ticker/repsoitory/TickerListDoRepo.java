package com.dunkware.trade.tick.service.server.ticker.repsoitory;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TickerListDoRepo extends CrudRepository<TickerListDO, Long> {

	List<TickerListDO> findByName(String name);
}
