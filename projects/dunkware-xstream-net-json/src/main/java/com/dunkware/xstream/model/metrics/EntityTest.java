package com.dunkware.xstream.model.metrics;

import java.time.LocalDate;

import com.dunkware.common.util.json.DJson;

public class EntityTest {

	public static void main(String[] args) {
		EntitySessionMetricsDTO dto = new EntitySessionMetricsDTO();
		dto.setDate(LocalDate.now());
		try {
			String out = DJson.serializePretty(dto);
			System.out.println(out);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
