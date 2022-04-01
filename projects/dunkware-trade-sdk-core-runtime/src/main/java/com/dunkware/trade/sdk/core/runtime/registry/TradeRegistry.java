package com.dunkware.trade.sdk.core.runtime.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import com.dunkware.trade.sdk.core.model.broker.BrokerType;
import com.dunkware.trade.sdk.core.model.system.SystemType;
import com.dunkware.trade.sdk.core.model.trade.EntryType;
import com.dunkware.trade.sdk.core.model.trade.ExitType;
import com.dunkware.trade.sdk.core.runtime.broker.Broker;
import com.dunkware.trade.sdk.core.runtime.broker.anot.ABroker;
import com.dunkware.trade.sdk.core.runtime.system.anot.ASystem;
import com.dunkware.trade.sdk.core.runtime.trade.TradeEntry;
import com.dunkware.trade.sdk.core.runtime.trade.TradeExit;
import com.dunkware.trade.sdk.core.runtime.trade.anot.ATradeEntry;
import com.dunkware.trade.sdk.core.runtime.trade.anot.ATradeExit;

public class TradeRegistry {
	
	private static TradeRegistry instance = null;
	
	private List<RegistryElement> brokers = new ArrayList<RegistryElement>();
	private List<RegistryElement> tradeEntries = new ArrayList<RegistryElement>();
	private List<RegistryElement> tradeExits = new ArrayList<RegistryElement>();
	private List<RegistryElement> systems = new ArrayList<RegistryElement>();
	
	
	public static TradeRegistry get() { 
		if(instance == null) { 
			instance = new TradeRegistry();
		}
		return instance; 
	}
	private TradeRegistry() { 
		Reflections reflections = new Reflections("com.dunkware");
		Set<Class<?>> brokers = reflections.getTypesAnnotatedWith(ABroker.class);
		for (Class<?> class1 : brokers) {
			RegistryElement element = new RegistryElement();
			element.clazz = class1;
			element.type = class1.getAnnotation(ABroker.class).type();
			this.brokers.add(element);
		}
		Set<Class<?>> entries = reflections.getTypesAnnotatedWith(ATradeEntry.class);
		for (Class<?> class1 : entries) {
			RegistryElement element = new RegistryElement();
			element.clazz = class1;
			element.type = class1.getAnnotation(ATradeEntry.class).type();
			this.tradeEntries.add(element);
		}
		Set<Class<?>> exits = reflections.getTypesAnnotatedWith(ATradeExit.class);
		for (Class<?> class1 : exits) {
			RegistryElement element = new RegistryElement();
			element.clazz = class1;
			element.type = class1.getAnnotation(ATradeExit.class).type();
			this.tradeExits.add(element);
		}
		Set<Class<?>> systems = reflections.getTypesAnnotatedWith(ASystem.class);
		for (Class<?> class1 : systems) {
			RegistryElement element = new RegistryElement();
			element.clazz = class1;
			element.type = class1.getAnnotation(ASystem.class).type();
			this.systems.add(element);
		}
		
		
	}
	
	public TradeExit createTradeExit(ExitType type) throws TradeRegistryException { 
		for (RegistryElement broker : tradeExits) {
			if(broker.type.isInstance(type))  {
				try {
					TradeExit brokerI = (TradeExit)broker.clazz.newInstance();
					return brokerI;
				} catch (Exception e) {
					throw new TradeRegistryException("Exception creating trade exit instance " + e.toString(),e);
				}
			}
		}
		throw new TradeRegistryException("trade exit not found for type " + type.getClass().getName());
	}
	
	public TradeEntry createTradeEntry(EntryType type) throws TradeRegistryException { 
		for (RegistryElement broker : tradeEntries) {
			if(broker.type.isInstance(type))  {
				try {
					TradeEntry brokerI = (TradeEntry)broker.clazz.newInstance();
					return brokerI;
				} catch (Exception e) {
					throw new TradeRegistryException("Exception creating trade entry instance " + e.toString(),e);
				}
			}
		}
		throw new TradeRegistryException("trade entry not found for type " + type.getClass().getName());
	}
	
	public Broker createBroker(BrokerType type) throws TradeRegistryException { 
		for (RegistryElement broker : brokers) {
			if(broker.type.isInstance(type))  {
				try {
					Broker brokerI = (Broker)broker.clazz.newInstance();
					return brokerI;
				} catch (Exception e) {
					throw new TradeRegistryException("Exception creating broker instance " + e.toString(),e);
				}
			}
		}
		throw new TradeRegistryException("Broker not found for type " + type.getClass().getName());
	}
	
	public System createSystem(SystemType type) throws TradeRegistryException { 
		for (RegistryElement broker : systems) {
			if(broker.type.isInstance(type))  {
				try {
					System brokerI = (System)broker.clazz.newInstance();
					return brokerI;
				} catch (Exception e) {
					throw new TradeRegistryException("Exception creating System instance " + e.toString(),e);
				}
			}
		}
		throw new TradeRegistryException("System not found for type " + type.getClass().getName());
		
	}
	
	
	private class RegistryElement { 
		
		public Class<?> type;
		public Class<?> clazz;
	}

}
