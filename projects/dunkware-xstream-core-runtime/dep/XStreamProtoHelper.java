package com.dunkware.xstream.core.impl.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.helpers.DDateTimeHelper;
import com.dunkware.xstream.api.XStreamRow;
import com.dunkware.xstream.api.XStreamVar;
import com.dunkware.xstream.core.model.signal.XStreamSignal;
import com.dunkware.xstream.core.proto.capture.StreamCapture.Message;
import com.dunkware.xstream.core.proto.capture.StreamCapture.Signal;
import com.dunkware.xstream.core.proto.capture.StreamCapture.Snapshot;
import com.dunkware.xstream.core.proto.capture.StreamCapture.Var;
import com.dunkware.xstream.core.proto.capture.StreamCapture.Var.PayloadCase;
import com.dunkware.xstream.xScript.DataType;
import com.dunkware.xstream.xScript.VarType;
import com.google.protobuf.Timestamp;

public class XStreamProtoHelper {

	private static Logger logger = LoggerFactory.getLogger(XStreamProtoHelper.class);

	public static Snapshot rowSnapshot(XStreamRow row) {

		ZoneId timeZone = DTimeZone.toZoneId(row.getStream().getInput().getTimeZone());
		long time = DDateTimeHelper.convertLocalTimeToDate(row.getStream().getClock().getTime().get(), timeZone)
				.getTime();
		
		Snapshot.Builder rowBuilder = Snapshot.newBuilder().setEntity(row.getIdentifier());
		rowBuilder.setTimestamp(time);
		for (XStreamVar var : row.getVars()) {
			if(var.getSize() == 0) { 
				continue;
			}
			VarType varType = var.getVarType();
			Var.Builder builder = Var.newBuilder();
			// builder.setKey(var.getVarType().getName());
			builder.setId(varType.getCode());
			// builder.setSize(var.getSize());
			if (varType.getType() == DataType.BO_OL) {
				// builder.setType(RowVarType.BOOLEAN);
				if (var.getSize() > 0) {
					builder.setBooleanValue((Boolean) var.getValue(0));
				}
			}
			if (varType.getType() == DataType.LONG) {
				// builder.setType(RowVarType.LONG);
				
				if (var.getSize() > 0) {
					Object value = var.getValue(0);
					if (value instanceof Integer) {
						Integer intMan = (Integer) value;
						builder.setLongValue(new Long(intMan));
					} else {
						builder.setLongValue((Long) var.getValue(0));
					}

				}
			}
			if (varType.getType() == DataType.INT) {
				// builder.setType(RowVarType.INT);
				if (var.getSize() > 0) {
					builder.setIntegerValue((Integer) var.getValue(0));
				}
			}
			if (varType.getType() == DataType.DUB) {

				if (var.getSize() > 0) {
					builder.setDoubleValue((Double) var.getValue(0));
				}
			}
			if (varType.getType() == DataType.STR) {

				if (var.getSize() > 0) {
					builder.setStringValue((String) var.getValue(0));
				}
			}

			Var v = builder.build();
			// only add the variable if it has a value 
			if(v.getPayloadCase() != PayloadCase.PAYLOAD_NOT_SET) { 
				rowBuilder.addVars(v);
			}
			
		}
		return rowBuilder.build();
	}

	public static Message rowSnapshotMessage(XStreamRow row) {
		Snapshot rowSnapshot = rowSnapshot(row);
		Message mesage = Message.newBuilder().setSnapshot(rowSnapshot).build();
		try {
			byte[] serialzied = mesage.toByteArray();
			Message de = Message.parseFrom(serialzied);
		} catch (Exception e) {
			logger.error("Exception serializing/deserializaing row snapshot message " + e.toString());
		}
		return mesage;
	}

	public static Message rowSignalMessage(XStreamRow row, XStreamSignal signal) {
		Message message = Message.newBuilder().setSignal(rowSignal(row, signal)).build();
		try {
			byte[] serialzied = message.toByteArray();
			Message de = Message.parseFrom(serialzied);
		} catch (Exception e) {
			logger.error("Exception serializing/deserializaing signal message " + e.toString());
		}
		return message;
	}

	public static Signal rowSignal(XStreamRow row, XStreamSignal signal) {
		ZoneId timeZone = DTimeZone.toZoneId(row.getStream().getInput().getTimeZone());

		long time = DDateTimeHelper.convertLocalTimeToDate(row.getStream().getClock().getTime().get(), timeZone)
				.getTime();
		Signal.Builder sigBuilder = Signal.newBuilder().setId(signal.getId()).setTimestamp(time).setEntity(row.getIdentifier());
		for (XStreamVar var : row.getVars()) {
			if(var.getSize() == 0) { 
				continue;
			}
			VarType varType = var.getVarType();
			Var.Builder builder = Var.newBuilder();
			// builder.setKey(var.getVarType().getName());
			builder.setId(varType.getCode());
			// builder.setSize(var.getSize());
			if (varType.getType() == DataType.BO_OL) {
				// builder.setType(RowVarType.BOOLEAN);
				if (var.getSize() > 0) {
					builder.setBooleanValue((Boolean) var.getValue(0));
				}
			}
			if (varType.getType() == DataType.LONG) {
				// builder.setType(RowVarType.LONG);
				if (var.getSize() > 0) {
					Object value = var.getValue(0);
					if (value instanceof Integer) {
						Integer intMan = (Integer) value;
						builder.setLongValue(new Long(intMan));
					} else {
						builder.setLongValue((Long) var.getValue(0));
					}

				}
			}
			if (varType.getType() == DataType.INT) {
				// builder.setType(RowVarType.INT);
				if (var.getSize() > 0) {
					builder.setIntegerValue((Integer) var.getValue(0));
				}
			}
			if (varType.getType() == DataType.DUB) {
				// builder.setType(RowVarType.DOUBLE);
				if (var.getSize() > 0) {
					if(var.getVarType().getCode() == 2005) {
						
					}
					System.out.println(var.getValue(0));
					builder.setDoubleValue((Double) var.getValue(0));
				}
			}
			if (varType.getType() == DataType.STR) {
				// builder.setType(RowVarType.STRING);
				if (var.getSize() > 0) {
					Object v = var.getValue(0);
					if (v == null) {
						logger.error("Signal Capture Builder Var String Value is null");
						continue;
					}
					if (String.class.isInstance(v)) {
						String value = (String) v;
						try {
							byte[] utf8Bytes = value.getBytes("UTF8");
							String roundTrip = new String(utf8Bytes, "UTF8");
							builder.setStringValue(roundTrip);
						} catch (Exception e) {
							logger.error("Exception getting UTF8 bytes of string var " + e.toString());
							continue;
						}

					} else {
						logger.error(
								"String var in signal builder is not string type but is " + v.getClass().getName());
						continue;
					}

					builder.setStringValue((String) var.getValue(0));
				}
			}
			Var v = builder.build();
			// only add the variable if it has a value 
			if(v.getPayloadCase() != PayloadCase.PAYLOAD_NOT_SET) { 
				sigBuilder.addVars(v);
			}
			
		}
		Signal sig = sigBuilder.build();
		byte[] bytes = sig.toByteArray();
		try {
			Signal de = Signal.parseFrom(bytes);
		} catch (Exception e) {
			logger.error("Exception deserializaing signal wtf " + e.toString());
			System.err.println("Siganl Deserialization Exception " + e.toString());
		}
		return sig;
	}

	public static Timestamp convertLocalDateTimeToGoogleTimestamp(LocalDateTime localDateTime) {
		Instant instant = localDateTime.toInstant(ZoneOffset.UTC);

		Timestamp result = Timestamp.newBuilder().setSeconds(instant.getEpochSecond()).setNanos(instant.getNano())
				.build();

		return result;
	}

}
