package com.dunkware.trade.net.data.server.stream.converters;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.trade.service.data.model.signals.query.SignalCountDataQuery;
import com.dunkware.trade.service.stream.descriptor.StreamDescriptor;
import com.dunkware.xstream.model.entity.StreamEntitySnapshot;
import com.dunkware.xstream.model.signal.StreamEntitySignal;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

public class MongoStreamConverter {

	/**
	 * don't worry about this. 
	 * @param snapshot
	 * @param timeZone
	 * @return
	 */
	public static Document snapshotToDocument(StreamEntitySnapshot snapshot, DTimeZone timeZone) {
		return null;
	}

	/**
	 * 
	 * @param signal
	 * @param timeZone
	 * @return
	 */
	public static Document signalToDocument(StreamEntitySignal signal) {
		Date date = Date.from(
				ZonedDateTime.of(signal.getDateTime(), signal.getZoneId()).toInstant()
		);
		Document signalDoc = new Document().append("id", signal.getId()).append("ent", signal.getEntity())
				.append("dateTime", date).append("zone", signal.getZoneId().toString());
		Document vars = new Document();
		for (Integer varId : signal.getVars().keySet()) {
			vars.append(String.valueOf(varId), signal.getVars().get(varId));
		}
		signalDoc.append("vars", vars);
		return signalDoc;
		
	}
	
	// responsible for putting stats in redddis cache 
	// then the queries can do that - 
	
	

	/**
	 * Mongo - Java 
	 * @param document
	 * @return
	 */
	public static StreamEntitySignal documentToSignal(Document document)  {
		StreamEntitySignal signal = new StreamEntitySignal();
		signal.setEntity(document.getInteger("ent", -1));
		signal.setId(document.getInteger("id", -1));
		signal.setZoneId(ZoneId.of(document.getString("zone")));
		Date date = document.getDate("dateTime");
		LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), signal.getZoneId());
		signal.setDateTime(dateTime);
		signal.setVars((Map<Integer, Number>) document.get("vars"));
		return signal;
		
	}

	

	/**
	 * We are not doign snapshots just yet
	 * @param document
	 * @return
	 */
	public StreamEntitySnapshot documentToSnapshot(Document document) {
		return null;
	}
	
	
	
	//SD21-GIFT-14 helper method to convert a signal document back into a model here we need to do take conversaion as ewll. 
	/**s the document in here and convdert back into a StreamEntitySignal model
	/**
	 * converts a mongo signal document back into its model
	 * @param document
	 * @return
	 * @throws Exception
	 */
	public StreamEntitySignal documentToSignalModel(Document document) throws Exception  { 
		
		
		return null;
	}
	
	public static final ZoneOffset toOffset(ZoneId zoneId) {
	    return Optional.ofNullable(zoneId)
	            .map(TimeZone::getTimeZone)
	            .map(TimeZone::getRawOffset)
	            .map(Duration::ofMillis)
	            .map(Duration::getSeconds)
	            .map(Number::intValue)
	            .map(ZoneOffset::ofTotalSeconds)
	            .orElse(null);
	}
	
	//SD21-GIFT-11 Start thinking how to build this query. 
	/**
	 * 		
	 * Converts to a MongoSearch, we pass in the StreamDescriptor as well 
	 * which has the time zone we need.to do search conversions. 
	 * @param
	 * @return
	 */
	public static Bson signalSearchToMongoQuery(List<Integer> signalTypes, List<Integer> entities, LocalDateTime date, LocalDateTime toDate, StreamDescriptor streamDescriptor) {
		ZoneId zoneId = DTimeZone.toZoneId(streamDescriptor.getTimeZone());

		Bson query = Filters.empty();

		if (toDate != null
					&& date != null) {
			// date range filter
			Date fromDateFinal = Date.from(
				
					date.toInstant(toOffset(zoneId))
			);
			Date toDateFinal = Date.from(
					toDate.toInstant(toOffset(zoneId))
			);
			Bson dateRangeFilter = Filters.and(
					Filters.gte("dateTime", fromDateFinal),
					Filters.lte("dateTime", toDateFinal)
			);
			query = Filters.and(query, dateRangeFilter);

		} else if (date != null) {
			// date exact match filter
			Bson dateExactMatchFilter = Filters.and(
					Filters.gte("dateTime", Date.from(
							date.toInstant(toOffset(zoneId)))),
					Filters.lte("dateTime", Date.from(
							date.toInstant(toOffset(zoneId))))
			);
			query = Filters.and(query, dateExactMatchFilter);
		}

		if (signalTypes != null
					&& signalTypes.size() > 0) {
			// in provided signal types filter
			Bson inSignalTypesFilter = Filters.in("id", signalTypes);
			query = Filters.and(query, inSignalTypesFilter);
		}

		if (entities != null
				&& entities.size() > 0) {
			// in provided entities filter
			Bson inEntitiesFilter = Filters.in("ent", entities);
			query = Filters.and(query, inEntitiesFilter);
		}


		return query;
		

	}

	
	public static List<Bson> signalCountToMongoQuery(Integer entityId, List<Integer> signalTypes, LocalDateTime searchRangeStart, LocalDateTime searchRangeStop, StreamDescriptor descriptor) {
		Bson filter = MongoStreamConverter.signalSearchToMongoQuery(signalTypes, entityId==null ? null: List.of(entityId), searchRangeStart, searchRangeStop, descriptor);
		Bson match = Aggregates.match(filter);
		Bson sort = Aggregates.sort(Sorts.descending("dateTime"));
		Bson group = Aggregates.group("$id",
				Accumulators.first("ent", "$ent"),
				Accumulators.first("id", "$id"),
				Accumulators.sum("count", 1),
				Accumulators.first("mostRecent", "$dateTime"),
				Accumulators.first("zone", "$zone")
		);

		return List.of(match, sort, group);
	}
	
	//SD-33-05 - method to build query like above
	public Bson entitySignalCountSearchToMongoQuery(SignalCountDataQuery req, StreamDescriptor streamDescriptor) {
		// if signal list is empty then we need to get all signals for this entity on req
		// iterate through the results here or in the service and from that
		// make a list of SignalEntitycCount objects 
		return null;
	}
	
	

}
