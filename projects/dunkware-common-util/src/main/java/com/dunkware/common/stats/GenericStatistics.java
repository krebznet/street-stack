package com.dunkware.common.stats;

import java.time.LocalTime;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import com.dunkware.common.util.calc.DCalc;
import com.dunkware.common.util.helpers.DNumberHelper;
import com.dunkware.common.util.stopwatch.DStopWatch;

public class GenericStatistics {

	private List<GenericNumber> data;
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
	
	


	public List<GenericNumber> getData() {
		return data;
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





	public void calculateStatistics(List<GenericNumber> data) {
	
		DescriptiveStatistics stats = new DescriptiveStatistics();
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

		variance = DCalc.castRoundTo3(stats.getVariance());

		mean = DCalc.castRoundTo3(stats.getMean());
		//median = DCalc.castRoundTo3(stats.getPercentile(50));
		stdDev = DCalc.castRoundTo3(stats.getStandardDeviation());
	}	
	


	


	public static void main(String[] args) {
		DStopWatch watch = DStopWatch.create();
		int i = (467 * 40);
		
		List<GenericNumber> fifyGrand = GenericNumberFactory.create(28000);
		
		Thread1 one = new Thread1(fifyGrand, 5000);
		Thread1 two = new Thread1(fifyGrand, 5000);
		Thread1 three = new Thread1(fifyGrand, 5000);
		Thread1 four = new Thread1(fifyGrand, 5000);
		Thread1 give = new Thread1(fifyGrand, 5000);
		one.start();
		two.start();
		three.start();
		four.start();
		give.start();
	}
	
	private static class Thread1 extends Thread { 
		
		private List<GenericNumber> list;
		private int times;
		GenericStatistics stats = new GenericStatistics();
		
		public Thread1(List<GenericNumber> list, int times) { 
			this.times = times;
			this.list = list;
			
		}
		
		public void run() { 
			DStopWatch watch = DStopWatch.create();
			watch.start();
			int i = 0;
			while(i < times) { 
				stats = new GenericStatistics();
				stats.calculateStatistics(list);
				i++;
			}
			watch.stop();
			System.out.println(watch.getCompletedSeconds());

		}
	}
}
