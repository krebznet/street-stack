package com.dunkware.trade.service.data.service.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.enums.DOperator;
import com.dunkware.common.util.json.DJson;
import com.dunkware.net.proto.core.GCalendarRange;
import com.dunkware.net.proto.core.GCalendarRangeType;
import com.dunkware.net.proto.core.GDate;
import com.dunkware.net.proto.core.GDateRange;
import com.dunkware.net.proto.core.GOperator;
import com.dunkware.net.proto.stream.GEntitySignalQuery;
import com.dunkware.net.proto.stream.GEntityVarCriteria;
import com.dunkware.trade.service.data.json.search.DateRange;
import com.dunkware.trade.service.data.json.search.EntitySignalSearchQuery;
import com.dunkware.trade.service.data.json.search.EntitySignalSearchQueryBuilder;
import com.dunkware.trade.service.data.json.search.EntityVarCriteria;

public class ProtoPojoUtil {

	public static void main(String[] args) {
		EntitySignalSearchQuery query = EntitySignalSearchQueryBuilder.newBuilder().addEntities("ident1","ident2").addSignalTyps("type1")
		.addVarCriteria("var1", DOperator.GT, 30.0).setDateRange(LocalDate.of(2022, 3, 1), LocalDate.of(2022, 3, 15)).setStream("stream1").build();
		try {
			System.out.println(DJson.serializePretty(query));
			GEntitySignalQuery gQuery = signalQueryPojoToProto(query);
			System.out.println(gQuery.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}

	public static GEntitySignalQuery signalQueryPojoToProto(EntitySignalSearchQuery q) {
		GEntitySignalQuery.Builder builder = GEntitySignalQuery.newBuilder();
		builder.setStreamId(q.getStreamIdentifier());
		builder.addAllEntities(q.getEntities());
		builder.addAllSignalTypes(q.getSignalTypes());

		GCalendarRange.Builder rangeBuilder = GCalendarRange.newBuilder();
		if (q.getCalendarRange() instanceof DateRange) {
			DateRange dr = (DateRange) q.getCalendarRange();
			GDateRange.Builder trb = GDateRange.newBuilder();
			trb.setStartDate(GDate.newBuilder().setDay(dr.getStart().get().getDayOfMonth())
					.setMonth(dr.getStart().get().getMonthValue()).setYear(dr.getStart().get().getYear()).build());
			trb.setStopDate(GDate.newBuilder().setDay(dr.getStop().get().getDayOfMonth())
					.setMonth(dr.getStop().get().getMonthValue()).setYear(dr.getStop().get().getYear()).build());
			GDateRange gdr = trb.build();
			rangeBuilder.setDateRange(gdr);
			rangeBuilder.setType(GCalendarRangeType.DATE_RANGE);
		}
		// todo add more range types

		List<GEntityVarCriteria> varCriterias = new ArrayList<GEntityVarCriteria>();
		for (EntityVarCriteria criteria : q.getVarCriterias()) {
			GEntityVarCriteria.Builder vb = GEntityVarCriteria.newBuilder();
			vb.setIdentifier(criteria.getIdentifier());
			vb.setValue(criteria.getValue());
			if (criteria.getOperator() == DOperator.EQ) {
				vb.setOperator(GOperator.EQ);
			}
			if (criteria.getOperator() == DOperator.NQ) {
				vb.setOperator(GOperator.NQ);
			}
			if (criteria.getOperator() == DOperator.GT) {
				vb.setOperator(GOperator.GT);
			}
			if (criteria.getOperator() == DOperator.LT) {
				vb.setOperator(GOperator.LT);
			}
			if (criteria.getOperator() == DOperator.LTE) {
				vb.setOperator(GOperator.LTE);
			}
			if (criteria.getOperator() == DOperator.GTE) {
				vb.setOperator(GOperator.GTE);
			}
			varCriterias.add(vb.build());
		}
		builder.addAllVarCriterias(varCriterias);
		return builder.build();
	}
}
