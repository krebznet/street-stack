package com.dunkware.trade.service.stream.worker.session.stats.builder;

import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.common.util.helpers.DNumberHelper;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.api.XStreamEntityVarListener;
import com.dunkware.xstream.model.stats.entity.EntityStat;
import com.dunkware.xstream.model.stats.entity.EntityStatType;
import com.dunkware.xstream.model.stats.entity.EntityStats;

public class EntityVarStatCollector implements XStreamEntityVarListener {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("StreamWorker");
	
	private XStreamEntityVar var; 
	
	private LocalTime highTime = null;
	private Number highValue = null;
	private boolean highInit = false; 
	
	private LocalTime lowTime = null;
	private Number lowValue = null; 
	private boolean lowInit = false; 
	
	
	
	public void init(XStreamEntityVar var) { 
		this.var = var;
		this.var.addVarListener(this);
	}

	@Override
	public void varUpdate(XStreamEntityVar var) {
		computeHigh(var);
		computeLow(var);
	}
	
	
	private void computeHigh(XStreamEntityVar var) { 
		if(!highInit) { 
			highTime = var.getRow().getStream().getClock().getLocalTime();
			highValue = var.getNumber(0);
			highInit = true;
			return;
		}
		try {
			Number value = var.getNumber(0);
			if(DNumberHelper.isFirstGreater(value, highValue)) { 
				highTime = var.getRow().getStream().getClock().getLocalTime();
				highValue = var.getNumber(0);
			}
		} catch (Exception e) {
			logger.error(marker, "Uncaught exception computing var low " + e.toString());
		}
	}
	
	private void computeLow(XStreamEntityVar var) { 
		if(!lowInit) { 
			lowTime = var.getRow().getStream().getClock().getLocalTime();
			lowValue = var.getNumber(0);
			lowInit = true;
			return;
		}
		try {
			Number value = var.getNumber(0);
			if(DNumberHelper.isFirstGreater(lowValue, value)) { 
				lowTime = var.getRow().getStream().getClock().getLocalTime();
				lowValue = var.getNumber(0);
			}
		} catch (Exception e) {
			logger.error(marker, "Uncaught exception computing var low " + e.toString());
		}
	}
	
	public void collectStats(EntityStats stats) { 
		EntityStat stat = new EntityStat();
		stat.setDate(var.getRow().getStream().getInput().getDate().get());
		stat.setTarget(var.getVarType().getCode());
		stat.setTime(highTime);
		stat.setValue(highValue);
		stat.setType(EntityStatType.VAR_HIGH);
		stats.getStats().add(stat);
		EntityStat low = new EntityStat();
		low.setDate(var.getRow().getStream().getInput().getDate().get());
		low.setTarget(var.getVarType().getCode());
		low.setTime(lowTime);
		low.setValue(lowValue);
		low.setType(EntityStatType.VAR_LOW);
		stats.getStats().add(low);
	}
}
