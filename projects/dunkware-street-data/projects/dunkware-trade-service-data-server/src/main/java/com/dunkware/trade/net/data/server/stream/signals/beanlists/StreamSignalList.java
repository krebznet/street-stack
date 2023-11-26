package com.dunkware.trade.net.data.server.stream.signals.beanlists;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.glazed.GlazedDataGrid;
import com.dunkware.common.util.glazed.GlazedDataGridListener;
import com.dunkware.common.util.observable.ObservableBeanListConnector;
import com.dunkware.trade.net.data.server.stream.signals.StreamSignalProvider;
import com.dunkware.trade.service.data.model.signals.bean.StreamSignalBean;
import com.dunkware.trade.service.data.model.signals.predicates.StreamSignalQueryPredicateBuilder;
import com.dunkware.trade.service.data.model.signals.query.StreamSignalQuery;

import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;

public class StreamSignalList implements GlazedDataGridListener {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamSignalStatsList");
	
	private ObservableElementList<StreamSignalBean> beans;
	
	private StreamSignalQuery query; 
	private StreamSignalProvider signalProvider;
	
	private Predicate<StreamSignalBean> statPredicate;
	
	private StatsUpdater statsUpdater;

	
	public void start(StreamSignalQuery query, StreamSignalProvider provider) throws Exception { 
		this.signalProvider = provider;
		beans = new ObservableElementList<StreamSignalBean>(GlazedLists.eventList(new ArrayList<StreamSignalBean>()),new ObservableBeanListConnector<>()); 
 			try {
				statPredicate = StreamSignalQueryPredicateBuilder.build(query);
			
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
			while(!interrupted()) { 
				List<StreamSignalBean> results = signalProvider.sessionSignalQuery(statPredicate);
				try {
					if(results == null) { 
						continue;
					} else { 
						List<StreamSignalBean> updates = new ArrayList<StreamSignalBean>();
						List<StreamSignalBean> inserts = new ArrayList<StreamSignalBean>();
						List<StreamSignalBean> deletes = new ArrayList<StreamSignalBean>();
						try {
							beans.getReadWriteLock().writeLock().lock();
							for (StreamSignalBean result : results) {
								if(beans.contains(result)) { 
									updates.add(result);
									continue;
								} else { 
									inserts.add(result);
								}
								
							}
							for (StreamSignalBean bean : beans) {
								if(results.contains(bean) == false) { 
									deletes.add(bean);
								}
							}
							for (StreamSignalBean insert : inserts) {
								beans.add(insert);
								
							}
							for (StreamSignalBean delete : deletes) {
								beans.remove(delete);
							}
							
						} catch (Exception e) {
							logger.error("Exception updating signal grid " + e.toString());
							// TODO: handle exception
						} finally { 
							beans.getReadWriteLock().writeLock().unlock();
						}
						
					}
					
					Thread.sleep(1500);
				
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
