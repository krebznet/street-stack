package com.dunkware.common.util.json;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.tick.TickBuilder;
import com.dunkware.common.tick.proto.TickProto.Tick;
import com.dunkware.common.tick.proto.TickProto.Tick.TickField;
import com.dunkware.common.tick.proto.TickProto.Tick.TickFieldType;
import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.uuid.DUUID;

public class DJsonPackTester {

	
	public static List<Person> buildPeople(int count) { 
		List<Person> wow = new ArrayList<Person>();
		
		int i = 0;
		while(i < count) { 
			Person p = new Person();
			p.setAge(DRandom.getRandom(2, 3232));
			p.setFirstName(DUUID.randomUUID(19));
			p.setLastName(DUUID.randomUUID(19));
			wow.add(p);
			i++;
		}
		return wow;
	}
	

	public static void tickSerializeDeserialize(List<Person> people) {
		List<byte[]> serialized = new ArrayList<byte[]>(people.size());
		DStopWatch t = DStopWatch.create();
		t.start();
		for (Person p : people) {
			try {
				
byte[] se = Tick.newBuilder().setType(1).addTickFields(TickField.newBuilder().setId(1).setIntValue(p.getAge()).setType(TickFieldType.INT))
				.addTickFields(TickField.newBuilder().setId(2).setStringValue(p.getFirstName()).setType(TickFieldType.STRING))
				.addTickFields(TickField.newBuilder().setId(3).setStringValue(p.getLastName()).setType(TickFieldType.STRING)).build().toByteArray();
			System.out.println(se.length);
			serialized.add(se);
				
			
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
		t.stop();
		System.out.println("tick Serialized " + people.size() + " people in " + t.getCompletedSeconds());
		List<Tick> decoded = new ArrayList<Tick>();
		t.start();
		for (byte[] ser : serialized) {
			try {
				decoded.add(Tick.parseFrom(ser));
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		t.stop();
		System.out.println("tick deseraizlied " + people.size() + " in " + t.getCompletedSeconds());
		
	}
	
	public static void jsonSerializeDeserialize(List<Person> people) {
		List<byte[]> serialized = new ArrayList<byte[]>(people.size());
		DStopWatch t = DStopWatch.create();
		t.start();
		for (Person p : people) {
			try {
				byte[] b = DJson.getObjectMapper().writeValueAsBytes(p);
				System.out.println("json byte size is " + b.length);
				serialized.add(b);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
		t.stop();
		System.out.println("Json Serialized " + people.size() + " people in " + t.getCompletedSeconds());
		List<Person> decoded = new ArrayList<Person>();
		t.start();
		for (byte[] ser : serialized) {
			try {
				decoded.add(DJson.getObjectMapper().readValue(ser, Person.class));
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		t.stop();
		System.out.println("Json deseraizlied " + people.size() + " in " + t.getCompletedSeconds());
		
	}
	
	public static void main(String[] args) {
		
		List<Person> people = buildPeople(1);
		jsonSerializeDeserialize(people);
		tickSerializeDeserialize(people);
		
		try {
			TickBuilder tb = TickBuilder.newBuilder(3);
		
			Tick tick = tb.doubleField(3, 3.3).intField(4, 3).stringField(5, "sdfasdfsd").stringField(6, "sdfsd").build();
			System.out.println(tick.toByteArray());
					System.out.println("next");
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}
}
