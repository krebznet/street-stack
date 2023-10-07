package com.dunkware.trade.tick.service.server.ticker.repsoitory;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface TickerListDoRepo extends PagingAndSortingRepository<TickerListDO, Long> {

	List<TickerListDO> findByName(String name);
}
