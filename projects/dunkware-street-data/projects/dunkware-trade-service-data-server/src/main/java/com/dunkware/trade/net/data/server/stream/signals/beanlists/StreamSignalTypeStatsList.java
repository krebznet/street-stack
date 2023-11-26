package com.dunkware.trade.net.data.server.stream.signals.beanlists;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.glazed.GlazedDataGrid;
import com.dunkware.common.util.glazed.GlazedDataGridListener;
import com.dunkware.common.util.observable.ObservableBeanListConnector;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.stream.cluster.proto.controller.blueprint.StreamBlueprintSignalDescriptor;
import com.dunkware.trade.net.data.server.stream.signals.StreamSignalProvider;
import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;
import com.dunkware.trade.service.data.model.signals.bean.StreamSignalTypeStatsBean;
import com.dunkware.trade.service.data.model.signals.comparator.StreamSignalDateTimeComparator;
import com.dunkware.trade.service.data.model.signals.predicates.StreamSignalQueryPredicateBuilder;
import com.dunkware.trade.service.data.model.signals.query.StreamSignalTypeStatsQuery;

import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;

public class StreamSignalTypeStatsList implements GlazedDataGridListener {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamSignalStatsList");
	
	private ObservableElementList<StreamSignalTypeStatsBean> beans;
	
	private StreamSignalTypeStatsQuery query; 
	private StreamSignalProvider signalProvider;
	
	private Predicate<StreamSignalBean> statPredicate;
	
	private StatsUpdater statsUpdater;

	private StreamSignalDateTimeComparator signalDateTimeComparator = new StreamSignalDateTimeComparator();
	
	public void start(StreamSignalTypeStatsQuery query, StreamSignalProvider provider) throws Exception {
		try {
			this.signalProvider = provider;
			statPredicate = StreamSignalQueryPredicateBuilder.build(query,signalProvider.streamDate());
		} catch (Exception e) {
			throw new Exception("Internal Exception Creating Stat Query Predicate " + e.toString());
		}
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
		} else { 
			for (StreamBlueprintSignalDescriptor sigDescriptor : signalProvider.streamBlueprint().signalDescriptors()) { 
				StreamSignalTypeStatsBean stats = new StreamSignalTypeStatsBean();
				stats.setSignalId(sigDescriptor.getId());
				
				try {
					stats.setSignalGroup(sigDescriptor.getGroup());
					stats.setSignalName(sigDescriptor.getName());					
				} catch (Exception e) {
					logger.error(marker, "Exception getting signal stats for signal id " + sigDescriptor.getId());
					stats.setSignalGroup("N/A");
					stats.setSignalName("N/A");
				}
				beans.getReadWriteLock().writeLock().lock();
				beans.add(stats);
				beans.getReadWriteLock().writeLock().unlock();
			}
		}
		
		statsUpdater = new StatsUpdater();
		statsUpdater.start();
		
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
	
	
	
	@Override
	public void onGridDispose(GlazedDataGrid dataGrid) {
		dispose();
	}

	@Override
	public void onGridInit(GlazedDataGrid dataGrid) {
		// TODO Auto-generated method stub
		
	}



	
	private class StatsUpdater extends Thread { 
		
		public StatsUpdater() { 
			
		}
		
		
		public void run() { 
			Set<Integer> uniqueEntities = new HashSet<Integer>();
			while(!interrupted()) { 
				Map<Integer,List<StreamSignalBean>> resultMap = new HashMap<Integer,List<StreamSignalBean>>();
				List<StreamSignalBean> results = signalProvider.sessionSignalQuery(statPredicate);
				for (StreamSignalBean streamSignalBean : results) {
					if(uniqueEntities.contains(streamSignalBean.getEntityId()) == false) { 
						uniqueEntities.add(streamSignalBean.getEntityId());
					}
					List<StreamSignalBean> signalTypeBeans = resultMap.get(streamSignalBean.getSignalId());
					if(signalTypeBeans == null) { 
						signalTypeBeans = new ArrayList<StreamSignalBean>();
						signalTypeBeans.add(streamSignalBean);
						resultMap.put(streamSignalBean.getSignalId(), signalTypeBeans);
					} else { 
						signalTypeBeans.add(streamSignalBean);
						resultMap.put(streamSignalBean.getSignalId(), signalTypeBeans);
					}
				}
				
				try {
					
					beans.getReadWriteLock().readLock().lock();
					StreamSignalTypeStatsBean[] beanCopies = new StreamSignalTypeStatsBean[beans.size()];
					int i = 0;
					for (StreamSignalTypeStatsBean streamSignalTypeStatsBean : beans) {
						beanCopies[i] = streamSignalTypeStatsBean;
						i++;
					}
					beans.getReadWriteLock().readLock().unlock();
					for (StreamSignalTypeStatsBean statsBean : beanCopies) {
						List<StreamSignalBean> resultList = resultMap.get(statsBean.getSignalId());
						
						if(resultList == null) { 
							statsBean.setEntityCount(0);
							statsBean.notifyUpdate();
						} else { 
							 Collections.sort(resultList,signalDateTimeComparator);
							 StreamSignalBean lastSignal = resultList.get(0);
							 statsBean.setLastSIgnalEntityId(lastSignal.getEntityId());
							 statsBean.setLastSignalEntityIdent(lastSignal.getEntityIdentifier());
							 statsBean.setLastSignalEntityName(lastSignal.getEntityName());
							 statsBean.setLastSignalPrice(lastSignal.getSignalPrice());
							 statsBean.setEntityCount(uniqueEntities.size());
							 statsBean.setSignalCount(resultList.size());
							 statsBean.setLastSignalTime(lastSignal.getDateTime().truncatedTo(ChronoUnit.SECONDS));
							 statsBean.setLastSignalTimeString(DunkTime.format(lastSignal.getDateTime(), DunkTime.YYYY_MM_DD_HH_MM_SS));
							 statsBean.notifyUpdate();
						}
						

						
						
					}
												
					
				} catch (Exception e) {
					if (e instanceof InterruptedException) { 
						return;
					}
					logger.error(marker, "Exception updating stat beans " + e.toString(),e);
				} finally { 
					
					
				}
				try {
					Thread.sleep(2000);	
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
			}
		}
	}

}
