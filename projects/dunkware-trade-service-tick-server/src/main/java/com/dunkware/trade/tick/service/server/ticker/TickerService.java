package com.dunkware.trade.tick.service.server.ticker;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.dunkware.net.cluster.node.Cluster;
import com.dunkware.trade.tick.api.provider.TradeSymbolService;
import com.dunkware.trade.tick.service.server.spring.DatabaseService;
import com.dunkware.trade.tick.service.server.ticker.repsoitory.TickerDO;
import com.dunkware.trade.tick.service.server.ticker.repsoitory.TickerListDO;
import com.dunkware.trade.tick.service.server.ticker.repsoitory.TickerListDoRepo;
import com.dunkware.trade.tick.service.server.ticker.repsoitory.TickerListTickerDO;
import com.dunkware.trade.tick.service.server.ticker.repsoitory.TickerRepository;

@Service
@Profile("TickerService")
public class TickerService implements TradeSymbolService {

	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private DatabaseService dbService; 
	
	@Autowired
	private TickerRepository repo; 
	
	@Autowired
	private TickerListDoRepo tickerListRepo; 
	
	@Autowired
	private Cluster cluster;
	
	private Map<String,Integer> symbolIds = new ConcurrentHashMap<String,Integer>();
	
	@PostConstruct
	private void load() { 
			
		Iterable<TickerDO> tickers = repo.findAll();
		for (TickerDO ticker : tickers) {
			Long m = new Long(ticker.getId());
			symbolIds.put(ticker.getSymbol(),m.intValue()); 
		}
			
	}
	
	public int getSymbolId(String symbol) { 
		if(symbolIds.containsKey(symbol) == false) { 
			return -1;
		}
		return symbolIds.get(symbol);
	}

	public TickerListDO insertList(String name, String query, boolean override) throws Exception { 
		Connection cn = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			//Optional<TickerListDO> existing = tickerListRepo.findBy
			cn = dbService.getServiceConnectionPool().getConnectionFromPool();
			// create a query
			st = cn.createStatement();
			rs = st.executeQuery(query);
			// create the model while we insert it
			TickerListDO list = new TickerListDO();
			//TODO: validate ticker list name
			list.setName(name);
			while(rs.next()) { 
				String symbol = rs.getString("symbol");
				long symbolId = rs.getLong("id");
				try {
					Optional<TickerDO> ticker = repo.findById(symbolId);
					if(ticker.isPresent() == false) { 
						throw new Exception("Ticker query by symbol name " + symbol + " id " + symbolId + "returned nothing");
					}
					TickerListTickerDO listTicker = new TickerListTickerDO();
					listTicker.setTicker(ticker.get());
					list.getTickers().add(listTicker);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			list.setSize(list.getTickers().size());
			tickerListRepo.save(list);
			// TODO: ticker:service-list id not set on insert
			rs.close();
			st.close();
			return list;
		} catch (Exception e) {
			logger.error("Exception Loading Tickers " + e.toString());;
			throw e;
		} finally { 
			if(cn != null) { 
				dbService.getServiceConnectionPool().returnConnectionToPool(cn);
			}
		}
		// MySqlConn
	}
	
	
	public List<TickerListDO> findListByName(String name) throws Exception { 
		return tickerListRepo.findByName(name);
		
	}
	
	
	
}


