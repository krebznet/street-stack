package com.dunkware.common.util.json;

import java.util.Arrays;
import java.util.List;

import com.dunkware.common.util.calc.DCalc;

public class DJsonException extends Exception {

	private static final long serialVersionUID = -2020292658599298555L;

	public DJsonException(String s) {
		super(s);
	}
	
	public DJsonException(String s, Throwable t) { 
		super(s,t);
	}
	
	public static void main(String[] args) throws Exception {
		List<Integer> wow = Arrays.asList(10,20);
		double average = DCalc.getMean(wow.toArray());
		System.out.println(average);
		int sum = 150;
		int count = 2; 
		
		int next = 50;
		average = ((15 * 2) + 5) / 3;
		System.out.println(average);
		wow = Arrays.asList(10,20,5);
		average = DCalc.getMean(wow.toArray());
		
		System.out.println(average);
		
	}
}
