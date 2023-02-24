package com.dunkware.xstream.core.stats.builders;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.data.DataHelper;
import com.dunkware.xstream.api.XStreamVar;
import com.dunkware.xstream.api.XStreamVarListener;
import com.dunkware.xstream.core.stats.StreamStatsHelper;
import com.dunkware.xstream.model.stats.StreamVariableStats;

public class EntityVarStatsBuilder implements XStreamVarListener {

	private Logger logger = LoggerFactory.getLogger(getClass());

	// Low
	private Number minValue = null;
	private LocalDateTime minTime = null;

	// High
	private Number maxValue = null;
	private LocalDateTime maxTime = null;

	private AtomicInteger updateCounter = new AtomicInteger();

	private XStreamVar var;
	
	private LocalDateTime fromTime;

	public static EntityVarStatsBuilder newInstance(XStreamVar var) {
		return new EntityVarStatsBuilder(var);
	}

	private EntityVarStatsBuilder(XStreamVar var) {
		this.fromTime = var.getRow().getLocalDateTime();
		this.var = var;
		this.var.addVarListener(this);
	}

	/**
	 * Handle update right now its simple, max/min
	 */
	@Override
	public void varUpdate(XStreamVar var) {
		if (!StreamStatsHelper.isNumeric(var)) {
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
				minTime = var.getRow().getStream().getClock().getLocalDateTime();
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
					minTime = var.getRow().getStream().getClock().getLocalDateTime();
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
				maxTime = var.getRow().getStream().getClock().getLocalDateTime();
			} catch (Exception e) {
				logger.error("Exception testing max null val;ue " + e.toString());
				;
			}
		} else {
			try {
				if (DataHelper.compareData(currentValue, maxValue) > 0) {
					maxTime = var.getRow().getStream().getClock().getLocalDateTime();
					maxValue = currentValue;
				}
			} catch (Exception e) {
				logger.error("Exception comparing double max value " + e.toString());
				;
			}
		}
		updateCounter.incrementAndGet();
	}

	/**
	 * Dispose resources for computing var stats
	 */
	public StreamVariableStats dispose() {
		this.var.removeVarListener(this);
		StreamVariableStats varStats = getStats();
		return varStats;
	}

	/**
	 * Build the VarStats for the entity
	 * 
	 * @param stopTime
	 * @return
	 */
	public StreamVariableStats getStats() {
		StreamVariableStats stats = new StreamVariableStats();
		stats.setVarId(var.getVarType().getCode());
		stats.setLow(minValue);
		stats.setLowT(this.minTime);
		stats.setHigh(maxValue);
		stats.setHighT(maxTime);
		stats.setValues(this.updateCounter.get());
		return stats;
	}

}
