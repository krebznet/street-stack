package com.dunkware.trade.net.data.server.stream.signals.beanlists;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.observable.ObservableBeanListConnector;
import com.dunkware.stream.cluster.proto.controller.blueprint.StreamBlueprintSignalDescriptor;
import com.dunkware.trade.net.data.server.stream.signals.StreamSignalProvider;
import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;
import com.dunkware.trade.service.data.model.signals.bean.StreamSignalTypeStatsBean;
import com.dunkware.trade.service.data.model.signals.comparator.StreamSignalDateTimeComparator;
import com.dunkware.trade.service.data.model.signals.predicates.StreamSignalQueryPredicateBuilder;
import com.dunkware.trade.service.data.model.signals.query.StreamSignalTypeStatsQuery;

import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;

public class StreamSignalTypeStatsList {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamSignalStatsList");
	
	private ObservableElementList<StreamSignalTypeStatsBean> beans;
	
	private StreamSignalTypeStatsQuery query; 
	private StreamSignalProvider signalProvider;
	
	private Predicate<StreamSignalBean> statPredicate;
	
	private StatsUpdater statsUpdater;

	
	public void start(StreamSignalTypeStatsQuery query, StreamSignalProvider provider) throws Exception { 
		beans = new ObservableElementList<StreamSignalTypeStatsBean>(GlazedLists.eventList(new ArrayList<StreamSignalTypeStatsBean>()),new ObservableBeanListConnector<>());
		if(query.getSignalTypes() != null && query.getSignalTypes().size() > 0) {
			for (Integer signalId : query.getSignalTypes()) {
				StreamSignalTypeStatsBean stats = new StreamSignalTypeStatsBean();
				stats.setSignalId(signalId);
				
				try {
					StreamBlueprintSignalDescriptor sigDescriptor = signalProvider.streamBlueprint().getSignalDescriptor(signalId);
					stats.setSignalGroup(sigDescriptor.getGroup());
					stats.setSignalName(sigDescriptor.getName());
					
				} catch (Exception e) {
					logger.error(marker, "Exception getting signal stats for signal id " + signalId);
					stats.setSignalGroup("N/A");
					stats.setSignalName("N/A");
				}
				beans.getReadWriteLock().writeLock().lock();
				beans.add(stats);
				beans.getReadWriteLock().writeLock().unlock();
			}
			
			try {
				statPredicate = StreamSignalQueryPredicateBuilder.build(query,signalProvider.streamDate());
				beans.clear(); 
				beans = null;
			} catch (Exception e) {
				throw new Exception("Internal Exception Creating Stat Query Predicate " + e.toString());
			}
			statsUpdater = new StatsUpdater();
			statsUpdater.start();
		}
		
	}
	
	public ObservableElementList<StreamSignalTypeStatsBean> getList() { 
		return beans; 
	}
	
	public void dispose() { 
		statsUpdater.interrupt();
		
	}
	
	
	public StreamSignalTypeStatsBean getStatsBean(int signalid) throws Exception { 
		try {
			beans.getReadWriteLock().readLock().lock();
			for (StreamSignalTypeStatsBean streamSignalTypeStatsBean : beans) {
				if(streamSignalTypeStatsBean.getSignalId() == signalid) { 
					return streamSignalTypeStatsBean;
				}
			}
			throw new Exception("Stats bean with signal id " + signalid + " not found");
		} catch (Exception e) {
			throw new Exception("Stats Bean with signal id " + signalid + " exceptoion " + e.toString());
		} finally { 
			beans.getReadWriteLock().readLock().unlock();
		}
	}
	
	private class StatsUpdater extends Thread { 
		
		public StatsUpdater() { 
			
		}
		
		
		public void run() { 
			while(!interrupted()) { 
				Map<Integer,List<StreamSignalBean>> resultMap = new HashMap<Integer,List<StreamSignalBean>>();
				List<StreamSignalBean> results = signalProvider.sessionSignalQuery(statPredicate);
				for (StreamSignalBean streamSignalBean : results) {
					List<StreamSignalBean> beans = resultMap.get(streamSignalBean.getSignalId());
					if(beans == null) { 
						beans = new ArrayList<StreamSignalBean>();
						beans.add(streamSignalBean);
						resultMap.put(streamSignalBean.getSignalId(), beans);
					} else { 
						beans.add(streamSignalBean);
						resultMap.put(streamSignalBean.getSignalId(), beans);
					}
				}
				
				try {
					
					for (StreamSignalBean streamSignalBean : results) {
						List<StreamSignalBean> resultList = resultMap.get(streamSignalBean.getSignalId());
						StreamSignalTypeStatsBean bean = null;
						try {
							bean = getStatsBean(streamSignalBean.getSignalId());
						} catch (Exception e) {
							logger.error("Problem, getting stats bean when predicate returned signal id ? ");;
							continue;
						}
						if(resultList == null) { 
							bean.setEntityCount(0);
							bean.notifyUpdate();
						} else { 
							 Collections.sort(resultList,StreamSignalDateTimeComparator.instance());
							 StreamSignalBean lastSignal = results.get(0);
							 bean.setLastSIgnalEntityId(lastSignal.getEntityId());
							 bean.setLastSignalEntityIdent(lastSignal.getEntityIdentifier());
							 bean.setLastSignalEntityName(lastSignal.getEntityName());
							 bean.setLastSignalPrice(lastSignal.getSignalPrice());
							 bean.notifyUpdate();
							 
							 
							// comparable mother fucker man. 
						}
						Thread.sleep(1000);
						
					}
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
