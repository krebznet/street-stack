package com.dunkware.trade.service.stream.server.blueprint;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.observable.ObservableBeanListConnector;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.trade.service.stream.json.blueprint.WebStreamSignaltype;
import com.dunkware.trade.service.stream.server.blueprint.event.EStreamBlueprintSignalCreated;
import com.dunkware.trade.service.stream.server.repository.StreamBlueprintSignalEntity;
import com.dunkware.trade.service.stream.server.repository.StreamBlueprintSignalRepo;
import com.dunkware.trade.service.stream.server.repository.StreamBlueprintSignalStatus;
import com.dunkware.trade.service.stream.server.repository.StreamEntity;
import com.dunkware.xstream.model.stats.EntityStatBulkReq;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;


public class StreamBlueprint {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private List<StreamBlueprintSignal> signals;
	private Semaphore signalLock = new Semaphore(1);
	
	@Autowired
	private StreamBlueprintSignalRepo signalRepo; 
	
	@Autowired
	private ApplicationContext ac;
	
	private DEventNode eventNode; 
	
	private StreamEntity streamEntity; 
	
	private ObservableElementList<StreamBlueprintSignalBean> signalBeans = null;
	
	private WebStreamSignaltype model;
	// Thing
		// symbol
			// variable
			// 
	// version --> Blueprint Version; 
	
	// also have the tickers --> 
	
	public void init(StreamEntity stream, StreamBlueprintService service) throws Exception  { 
		signalBeans = new ObservableElementList<StreamBlueprintSignalBean>(
				GlazedLists.threadSafeList(new BasicEventList<StreamBlueprintSignalBean>()),
				new ObservableBeanListConnector<StreamBlueprintSignalBean>());
		this.streamEntity = stream; 
		eventNode = service.getEventNode().createChild(this);
		eventNode.addEventHandler(this);
		
		
		for (StreamBlueprintSignalEntity entity : signalRepo.findAll()) {
			if(entity.getStreamId() == stream.getId()) { 
				StreamBlueprintSignal signal = new StreamBlueprintSignal();
				try {
					model = DJson.getObjectMapper().readValue(entity.getModel(), WebStreamSignaltype.class);
				} catch (Exception e) {
					logger.error("Exception parsing signal blueprint mode");
				}
				ac.getAutowireCapableBeanFactory().autowireBean(signal);
				try {
					signal.init(entity, this);
					signals.add(signal);
					StreamBlueprintSignalBean tb = new StreamBlueprintSignalBean();
					
					tb.setCreated(DunkTime.format(entity.getCreated(), DunkTime.YYYY_MM_DD_HH_MM_SS));
					tb.setName(model.getName());
					tb.setDescription(model.getDescription());
					tb.setGroup("Default");
					tb.setStatus("Active");
					tb.setId(entity.getId());
					signalBeans.add(tb);
				} catch (Exception e) {
					logger.error("Exception init stream blueprint signal " + e.toString());

				}
			}
		}
		
	}
	
	
	
	public ObservableElementList<StreamBlueprintSignalBean> getSignalBeans() {
		return signalBeans;
	}

	
	public StreamBlueprintSignal getSignal(long id) throws Exception { 
		for (StreamBlueprintSignal sig : signals) {
			try {
				signalLock.acquire();
				if(sig.getEntity().getId()== id) { 
					return sig;
				}
				
			} catch (Exception e) {
				throw new Exception("internal " + e.toString());
			} finally { 
				signalLock.release();
			}
			
		}
		throw new Exception("String signal id " + id + " not found on stream");
		
	}


	public void addSignal(WebStreamSignaltype model) throws Exception { 
		for (StreamBlueprintSignal signal : signals) {
			if(signal.getModel().getName().equalsIgnoreCase(model.getName())) { 
				throw new Exception("Signal name " + model.getName() + " already exists");
			}
		}
		StreamBlueprintSignalEntity fuck = new StreamBlueprintSignalEntity();
		fuck.setCreated(LocalDateTime.now(DTimeZone.toZoneId(DTimeZone.NewYork)));
		LocalDateTime effect = fuck.getCreated().plusDays(1);
		LocalDate effectDate = effect.toLocalDate();
		fuck.setEffective(effectDate);
		fuck.setStatus(StreamBlueprintSignalStatus.ACTIVE);
		try {
			fuck.setModel(DJson.serialize(model));
		} catch (Exception e) {
			throw new Exception("Serialize Exception " + e.toString());
		}
		fuck.setStreamId(streamEntity.getId());
		signalRepo.save(fuck);
		StreamBlueprintSignal signal = new StreamBlueprintSignal();
		ac.getAutowireCapableBeanFactory().autowireBean(signal);
		try {
			signal.init(fuck, this);
			signals.add(signal);
			
			signals.add(signal);
			StreamBlueprintSignalBean tb = new StreamBlueprintSignalBean();
			
			tb.setCreated(DunkTime.format(fuck.getCreated(), DunkTime.YYYY_MM_DD_HH_MM_SS));
			tb.setName(model.getName());
			tb.setDescription(model.getDescription());
			tb.setGroup("Default");
			tb.setStatus("Active");
			tb.setId(fuck.getId());
			signalBeans.add(tb);
			eventNode.event(new EStreamBlueprintSignalCreated(this, signal));
		} catch (Exception e) {
			logger.error("Exception init stream blueprint signal " + e.toString());

		}
		
	}
	
	public DEventNode getEventNode() {
		return eventNode;
	}
	
	
	List<StreamBlueprintSignal> getSignals() throws Exception { 
		try {
			signalLock.acquire();
			return signals; 
		} catch (Exception e) {
			logger.error("Exception getting signals " + e.toString());
			throw e;
		}finally { 
			signalLock.release();
		}
		
	}

}
