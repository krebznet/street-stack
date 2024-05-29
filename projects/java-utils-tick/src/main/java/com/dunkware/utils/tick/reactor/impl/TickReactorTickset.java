package com.dunkware.utils.tick.reactor.impl;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.utils.tick.proto.TickProto.Tick.TickFieldType;
import com.dunkware.utils.tick.reactor.TickReactorException;
import com.dunkware.utils.tick.reactor.blueprint.ReactorTickField;
import com.dunkware.utils.tick.reactor.blueprint.ReactorTickSet;

public class TickReactorTickset {

	private ReactorTickSet input;

	private List<TickReactorTick> ticks = new ArrayList<TickReactorTick>();

	private ReactorTickSet tickset;

	public void init(ReactorTickSet tickset) throws TickReactorException {
		this.tickset = tickset;
		int i = 0;
		while (i < tickset.getSize()) {

			TickReactorTick reactorTick = new TickReactorTick();
			reactorTick.setType(tickset.getType());
			for (ReactorTickField field : tickset.getFields()) {
				TickReactorTickField tickField = new TickReactorTickField();
				boolean typeHandled = false;
				// set field id
				tickField.setId(field.getId());
				if (field.getType().equals("Double")) {
					try {
						tickField.setDoubleValue(Double.valueOf(field.getValue()));
					} catch (RuntimeException e) {
						throw new TickReactorException("Exception setting tick value " + e.toString());
					}
					tickField.setType(TickFieldType.DOUBLE);
					typeHandled = true;
				}
				if (field.getType().equals("Int")) {
					tickField.setType(TickFieldType.INT);
					try {
						tickField.setIntValue(Integer.valueOf(field.getValue()));
					} catch (RuntimeException e) {
						throw new TickReactorException("Exception setting tick value " + e.toString());
					}
					typeHandled = true;
				}
				if (field.getType().equals("Long")) {
					tickField.setType(TickFieldType.LONG);
					try {
						tickField.setLongValue(Long.valueOf(field.getValue()));
					} catch (RuntimeException e) {
						throw new TickReactorException("Exception setting tick value " + e.toString());
					}
					typeHandled = true;
				}
				if (field.getType().equals("String")) {
					tickField.setType(TickFieldType.STRING);
					
					
					try {
						if(field.isSeed()) {
							tickField.setStringValue(field.getValue() + i);
						} else { 
							tickField.setStringValue(field.getValue());	
						}
						
					} catch (RuntimeException e) {
						throw new TickReactorException("Exception setting tick value " + e.toString());
					}
					typeHandled = true;
				}

				if (!typeHandled) {
					throw new TickReactorException("Tick Field type not handled " + field.getType());
				}
				reactorTick.addField(field.getId(), tickField);
			}
			ticks.add(reactorTick);
			i++;
		}
	}

	public List<TickReactorTick> getTicks() {
		return ticks;
	}

}