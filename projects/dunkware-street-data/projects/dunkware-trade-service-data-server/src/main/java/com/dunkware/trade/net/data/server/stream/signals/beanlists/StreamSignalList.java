package com.dunkware.trade.net.data.server.stream.signals.beanlists;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.observable.ObservableBeanListConnector;
import com.dunkware.trade.net.data.server.stream.signals.StreamSignalProvider;
import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;
import com.dunkware.trade.service.data.model.signals.predicates.StreamSignalQueryPredicateBuilder;
import com.dunkware.trade.service.data.model.signals.query.StreamSignalQuery;

import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;

public class StreamSignalList {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamSignalStatsList");
	
	private ObservableElementList<StreamSignalBean> beans;
	
	private StreamSignalQuery query; 
	private StreamSignalProvider signalProvider;
	
	private Predicate<StreamSignalBean> statPredicate;
	
	private StatsUpdater statsUpdater;

	
	public void start(StreamSignalQuery query, StreamSignalProvider provider) throws Exception { 
			beans = new ObservableElementList<StreamSignalBean>(GlazedLists.eventList(new ArrayList<StreamSignalBean>()),new ObservableBeanListConnector<>()); 
 			try {
				statPredicate = StreamSignalQueryPredicateBuilder.build(query);
				beans.clear(); 
				beans = null;
			} catch (Exception e) {
				throw new Exception("Internal Exception Creating Stat Query Predicate " + e.toString());
			}
			statsUpdater = new StatsUpdater();
			statsUpdater.start();
	}
	
	public ObservableElementList<StreamSignalBean> getList() { 
		return beans; 
	}
	
	public void dispose() { 
		statsUpdater.interrupt();
		
	}
	
	
	
	private class StatsUpdater extends Thread { 
		
		public StatsUpdater() { 
			
		}
		
		
		public void run() { 
			while(!interrupted()) { 
				List<StreamSignalBean> results = signalProvider.sessionSignalQuery(statPredicate);
				try {
					if(results == null) { 
						continue;
					} else { 
						beans.getReadWriteLock().writeLock().lock();
						beans.addAll(results);
						beans.getReadWriteLock().writeLock().unlock();
					}
					
					Thread.sleep(1000);
				
				} catch (Exception e) {
					if (e instanceof InterruptedException) { 
						return;
					}
					logger.error(marker, "Exception updating stat beans " + e.toString(),e);
				}
				
				
				
			}
		}
	}

}
