package com.dunkware.trade.service.stream.worker.session.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.stream.data.lib.cache.live.LiveEntity;
import com.dunkware.trade.service.stream.worker.session.StreamWorker;
import com.dunkware.trade.service.stream.worker.session.StreamWorkerExtension;
import com.dunkware.trade.service.stream.worker.session.anot.AStreamWorkerExtension;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.api.XStreamListener;

@AStreamWorkerExtension
public class LiveEntityCacheService implements StreamWorkerExtension, XStreamListener {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("LiveEntity");
	private StreamWorker worker;
	private XStream stream;
	private RedissonClient redissonClient;
	private AtomicInteger updateCountValue = new AtomicInteger();
	private AtomicInteger errorCountValue = new AtomicInteger();
	//private Stat updateCount;
	//private Stat errorCount;
	private List<LiveEntityController> controllers = new ArrayList<LiveEntityController>();
	
	

	@Override
	public void init(StreamWorker worker) throws Exception {
		this.worker = worker;
		///updateCount = worker.getStatRegistry().createStat("Live Entity", "Entity Update Count", FormatType.NUMERIC);
		//errorCount = worker.getStatRegistry().createStat("Live Entity", "Error Count", FormatType.NUMERIC);
		//updateCount.setValue(0);
		//errorCount.setValue(0);
		this.redissonClient = worker.getRedissonClient();
	}

	@Override
	public void start() throws Exception {
		this.stream = worker.getStream();
		this.stream.addStreamListener(this);
	}

	@Override
	public void stop() throws Exception {
		for (LiveEntityController liveEntityController : controllers) {
			stream.getClock().unscheduleRunnable(liveEntityController);
			liveEntityController.detatch();
		}
	}

	@Override
	public void rowInsert(XStreamEntity row) {
		row.getStream().getExecutor().execute(new Runnable() {
			
			@Override
			public void run() {
				LiveEntityController controller = new  LiveEntityController(row);
				controllers.add(controller);
				row.getStream().getClock().scheduleRunnable(controller, 1);		
			}
		});
		
	}

	public class LiveEntityController implements Runnable {

		private XStreamEntity entity;
		private LiveEntity liveEntity;
		
		
		public LiveEntityController(XStreamEntity entity) {
			this.entity = entity;

					liveEntity = new LiveEntity();
					liveEntity.setIdent(entity.getId());
					liveEntity.setId(entity.getIdentifier());
					Map<String,Number> varSnapshots = new ConcurrentHashMap<String,Number>();
					for (XStreamEntityVar xStreamEntityVar : entity.getNumericVars()) {
						if(xStreamEntityVar.getSize() > 0) { 
							varSnapshots.put(xStreamEntityVar.getVarType().getName(), xStreamEntityVar.getNumber(0));
						}
					}
					liveEntity.setVariables(varSnapshots);
					liveEntity = redissonClient.getLiveObjectService().merge(liveEntity);
					logger.info(marker, "Live Entity {} {}",liveEntity.getIdent(),liveEntity.getIdent());
					
			
		}
		
		public void detatch() { 
			redissonClient.getLiveObjectService().detach(liveEntity);
		}
		
		@Override
		public void run() {
			//updateCount.setValue(updateCountValue.incrementAndGet());
			List<XStreamEntityVar> vars = new ArrayList<XStreamEntityVar>();
			Map<String,Number> varSnapshots = new ConcurrentHashMap<String,Number>();
			for (XStreamEntityVar xStreamEntityVar : entity.getNumericVars()) {
				if(xStreamEntityVar.getSize() > 0) { 
					varSnapshots.put(xStreamEntityVar.getVarType().getName(), xStreamEntityVar.getNumber(0));
				}
			}
			liveEntity.setVariables(varSnapshots);
			
		} 
	}


}
