package com.dunkware.common.stats;

import java.time.LocalTime;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.calc.DCalc;
import com.dunkware.common.util.helpers.DNumberHelper;
import com.dunkware.common.util.stopwatch.DStopWatch;

import ca.odell.glazedlists.EventList;

public class GenericStatistics {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private LocalTime highTime;
	private LocalTime lowTime;
	private Number high = null;
	private Number low = null;
	private boolean highLowInit = false;
	private double variance = 0;
	private double mean = 0;
	private double median;
	private double stdDev;

	public GenericStatistics() {

	}

	

	public LocalTime getHighTime() {
		return highTime;
	}

	public LocalTime getLowTime() {
		return lowTime;
	}

	public Number getHigh() {
		return high;
	}

	public Number getLow() {
		return low;
	}

	public boolean isHighLowInit() {
		return highLowInit;
	}

	public double getVariance() {
		return variance;
	}

	public double getMean() {
		return mean;
	}

	public double getMedian() {
		return median;
	}

	public double getStdDev() {
		return stdDev;
	}

	public void calculateStatistics(EventList<GenericNumber> data) {

		DescriptiveStatistics stats = new DescriptiveStatistics();

		try {
			data.getReadWriteLock().readLock().lock();
			for (GenericNumber value : data) {

				stats.addValue(value.getValue().doubleValue());

				if (!highLowInit) {
					high = value.getValue();
					highTime = value.getTime();
					low = value.getValue();
					lowTime = value.getTime();
					highLowInit = true;
				} else {
					if (DNumberHelper.isFirstGreater(value.getValue(), high)) {
						high = value.getValue();
						highTime = value.getTime();
					}
					if (DNumberHelper.isFirstGreater(low, value.getValue())) {
						low = value.getValue();
						lowTime = value.getTime();
					}
				}
			}

		} catch (Exception e) {
			logger.error("Exception iterating generic number and computing high / low value " + e.toString());
		} finally { 
			data.getReadWriteLock().readLock().unlock();
		}

		variance = DCalc.castRoundTo3(stats.getVariance());

		mean = DCalc.castRoundTo3(stats.getMean());
		// median = DCalc.castRoundTo3(stats.getPercentile(50));
		stdDev = DCalc.castRoundTo3(stats.getStandardDeviation());

	}

	
}
