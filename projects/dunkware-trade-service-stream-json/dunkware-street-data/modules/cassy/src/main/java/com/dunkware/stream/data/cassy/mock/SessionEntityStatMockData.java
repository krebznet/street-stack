package com.dunkware.stream.data.cassy.mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.stream.data.cassy.builders.SessionEntityStatRowBuilder;
import com.dunkware.stream.data.cassy.entity.stats.SessionEntityStatRow;

public class SessionEntityStatMockData {

	
	public static void main(String[] args) {
		List<SessionEntityStatRow> data = build(LocalDate.of(2024, 1, 2),1,5000,50,1,2);
		for (SessionEntityStatRow sessionEntityStatRow : data) {
			System.out.println(sessionEntityStatRow.toString());
		}
		System.out.println(data.size());
	}
	
	public static List<SessionEntityStatRow> build(LocalDate date, int stream, int entities, int elements, int... stats) {
		List<SessionEntityStatRow> rows = new ArrayList<SessionEntityStatRow>();
		for(int i = 0; i < entities; i++) { 
			for(int x = 0; x < elements; x++) { 
				for(int j = 0; j < stats.length;j++) { 
					rows.add(SessionEntityStatRowBuilder.newBuilder().date(date).streamEntityVarStatValue(stream,i,x,stats[j],i + 0.23).build());
				}
			}
		}
		return rows;
		
	}
}
