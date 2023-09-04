package com.dunkware.xstream.stats.builders;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.data.DataHelper;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.api.XStreamEntityVarListener;
import com.dunkware.xstream.model.stats.EntityStatsSessionVar;
import com.dunkware.xstream.stats.StreamSessionStatsHelper;

public class EntityVarStatsBuilder implements XStreamEntityVarListener {

	private Logger logger = LoggerFactory.getLogger(getClass());

	// Low
	private Number minValue = null;
	private LocalTime minTime = null;

	// High
	private Number maxValue = null;
	private LocalTime maxTime = null;

	private AtomicInteger valueCounter = new AtomicInteger();

	private XStreamEntityVar var;
	
	private LocalDateTime fromTime;

	public static EntityVarStatsBuilder newInstance(XStreamEntityVar var) {
		return new EntityVarStatsBuilder(var);
	}

	private EntityVarStatsBuilder(XStreamEntityVar var) {
		this.fromTime = var.getRow().getLocalDateTime();
		this.var = var;
		this.var.addVarListener(this);
	}

	/**
	 * Handle update right now its simple, max/min
	 */
	@Override
	public void varUpdate(XStreamEntityVar var) {
		if (!StreamSessionStatsHelper.isNumeric(var)) {
			return;
		}
		if (var.getSize() == 0) {
			return;
		}
		Number currentValue = null;
		try {
			currentValue = var.getNumber(0);

		} catch (Exception e) {
			logger.error("Exception calling getNumber in var Update " + e.toString());
			return;
		}
		if (currentValue == null) {
			logger.error("Bad logic, var.getSize > 0 but value is null");
			return;
		}

		// Compute Min Value
		if (minValue == null) {
			try {
				minValue = var.getNumber(0);
				minTime = var.getRow().getStream().getClock().getLocalTime();
			} catch (Exception e) {
				logger.error("Exception handling min value stat when initial min value is null " + e.toString());
			}

			// converter
		} else {
			try {
				int testMe = DataHelper.compareData(currentValue, minValue);
				if (testMe < 0) {
					// then currentValue is less than minValue
					minValue = currentValue;
					minTime = var.getRow().getStream().getClock().getLocalTime();
				}
			} catch (Exception e) {
				logger.error("Exception converting min in var stat builder " + e.toString());
				;
			}

		}
		// Compute Max Value
		if (maxValue == null) {
			try {
				maxValue = currentValue;
				maxTime = var.getRow().getStream().getClock().getLocalTime();
			} catch (Exception e) {
				logger.error("Exception testing max null val;ue " + e.toString());
				;
			}
		} else {
			try {
				if (DataHelper.compareData(currentValue, maxValue) > 0) {
					maxTime = var.getRow().getStream().getClock().getLocalTime();
					maxValue = currentValue;
				}
			} catch (Exception e) {
				logger.error("Exception comparing double max value " + e.toString());
				;
			}
		}
		valueCounter.incrementAndGet();
	}

	/**
	 * Dispose resources for computing var stats
	 */
	public EntityStatsSessionVar dispose() {
		this.var.removeVarListener(this);
		EntityStatsSessionVar varStats = getStats();
		return varStats;
	}

	/**
	 * Build the VarStats for the entity
	 * 
	 * @param stopTime
	 * @return
	 */
	public EntityStatsSessionVar getStats() {
		EntityStatsSessionVar stats = new EntityStatsSessionVar();
		stats.setId(var.getVarType().getCode());
		stats.setIdent(var.getVarType().getName());
		stats.setLow(minValue);
		if(minTime!= null)
			stats.setLowDateTime(LocalDateTime.of(var.getRow().getStream().getInput().getDate().get(), minTime));
			stats.setLow(minValue);
		stats.setHigh(maxValue);
		if(maxTime!=null)
			stats.setHighDateTime(LocalDateTime.of(var.getRow().getStream().getInput().getDate().get(), maxTime));
		stats.setValueCount(this.valueCounter.get());
		return stats;
	}

}
