package com.dunkware.trade.engine.core.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import com.dunkware.trade.engine.api.TradeEntryExecutor;
import com.dunkware.trade.engine.api.TradeEntryTrigger;
import com.dunkware.trade.engine.api.TradeExitExecutor;
import com.dunkware.trade.engine.api.TradeExitTrigger;
import com.dunkware.trade.engine.api.TradeValidator;
import com.dunkware.trade.engine.api.anot.ATradeEntryExecutor;
import com.dunkware.trade.engine.api.anot.ATradeEntryTrigger;
import com.dunkware.trade.engine.api.anot.ATradeExitExecutor;
import com.dunkware.trade.engine.api.anot.ATradeExitTrigger;
import com.dunkware.trade.engine.api.anot.ATradeValidator;
import com.dunkware.trade.engine.api.registry.TradeRegistry;
import com.dunkware.trade.engine.model.api.TradeValidatorType;
import com.dunkware.trade.engine.model.api.extend.TradeEntryExecutorType;
import com.dunkware.trade.engine.model.api.extend.TradeEntryTriggerType;
import com.dunkware.trade.engine.model.api.extend.TradeExitExecutorType;
import com.dunkware.trade.engine.model.api.extend.TradeExitTriggerType;

public class TradeRegistryImpl implements TradeRegistry {

	private static TradeRegistryImpl instance = null;

	public static TradeRegistryImpl get() {
		if (instance == null)
			instance = new TradeRegistryImpl();
		return instance;

	}

	private List<RegistryTypeElement> exitTriggers = new ArrayList<RegistryTypeElement>();
	private List<RegistryTypeElement> exitExecutors = new ArrayList<RegistryTypeElement>();
	private List<RegistryTypeElement> entryTriggers = new ArrayList<RegistryTypeElement>();
	private List<RegistryTypeElement> entryExecutors = new ArrayList<RegistryTypeElement>();
	private List<RegistryTypeElement> tradeValidators = new ArrayList<RegistryTypeElement>();

	private TradeRegistryImpl() {

		Reflections reflections = new Reflections("com.dunkware");
		Set<Class<?>> expClasses = reflections.getTypesAnnotatedWith(ATradeEntryTrigger.class);
		for (Class<?> expclass : expClasses) {
			ATradeEntryTrigger[] expAnots = expclass.getAnnotationsByType(ATradeEntryTrigger.class);
			RegistryTypeElement exp = new RegistryTypeElement(expclass, expAnots[0].type());
			entryTriggers.add(exp);

		}
		for (Class<?> expclass : expClasses) {
			ATradeExitTrigger[] expAnots = expclass.getAnnotationsByType(ATradeExitTrigger.class);
			RegistryTypeElement exp = new RegistryTypeElement(expclass, expAnots[0].type());
			exitTriggers.add(exp);

		}
		Set<Class<?>> extClasses = reflections.getTypesAnnotatedWith(ATradeEntryExecutor.class);
		for (Class<?> extClass : extClasses) {
			ATradeEntryExecutor[] extAnots = extClass.getAnnotationsByType(ATradeEntryExecutor.class);
			
			RegistryTypeElement exp = new RegistryTypeElement(extClass, extAnots[0].type());
			entryExecutors.add(exp);
		}
		Set<Class<?>> classes = reflections.getTypesAnnotatedWith(ATradeExitExecutor.class);
		for (Class<?> clazz : classes) {
			ATradeExitExecutor expAnot = clazz.getAnnotation(ATradeExitExecutor.class);
			RegistryTypeElement element = new RegistryTypeElement(clazz, expAnot.type());
			exitExecutors.add(element);
		}
		classes = reflections.getTypesAnnotatedWith(ATradeValidator.class);
		for (Class<?> clazz : classes) {
			ATradeValidator expAnot = clazz.getAnnotation(ATradeValidator.class);
			RegistryTypeElement element = new RegistryTypeElement(clazz, expAnot.type());
			tradeValidators.add(element);
		}
		
	}

	@Override
	public TradeEntryExecutor entryExecutor(TradeEntryExecutorType type) throws Exception {
		for (RegistryTypeElement RegistryTypeElement : entryExecutors) {
			if(RegistryTypeElement.getType().isInstance(type)) { 
				try {
					TradeEntryExecutor exp = (TradeEntryExecutor)RegistryTypeElement.getElement().newInstance();
					return exp;
				} catch (Exception e) {
					throw new Exception("Create Entry Executor Type " + e.toString() + " " +  type.getClass().getName());
				}
			}
		}
		throw new Exception("Create Entry Executor Type Not Cound " + type.getClass().getName());

	}

	@Override
	public TradeExitExecutor exitExecutor(TradeExitExecutorType type) throws Exception {
		for (RegistryTypeElement RegistryTypeElement : exitExecutors) {
			if(RegistryTypeElement.getType().isInstance(type)) { 
				try {
					TradeExitExecutor exp = (TradeExitExecutor)RegistryTypeElement.getElement().newInstance();
					return exp;
				} catch (Exception e) {
					throw new Exception("Create Exit Executor Type " + e.toString() + " " +  type.getClass().getName());
				}
			}
		}
		throw new Exception("Create Exit Executor Type Not Cound " + type.getClass().getName());
	}

	@Override
	public TradeEntryTrigger entryTrigger(TradeEntryTriggerType type) throws Exception {
		for (RegistryTypeElement RegistryTypeElement : entryTriggers) {
			if(RegistryTypeElement.getType().isInstance(type)) { 
				try {
					TradeEntryTrigger exp = (TradeEntryTrigger)RegistryTypeElement.getElement().newInstance();
					return exp;
				} catch (Exception e) {
					throw new Exception("Create Entry Trigger Type " + e.toString() + " " +  type.getClass().getName());
				}
			}
		}
		throw new Exception("Create Entry Trigger Type Not Cound " + type.getClass().getName());
	}

	@Override
	public TradeExitTrigger exitTrigger(TradeExitTriggerType type) throws Exception {
		for (RegistryTypeElement RegistryTypeElement : exitTriggers) {
			if(RegistryTypeElement.getType().isInstance(type)) { 
				try {
					TradeExitTrigger exp = (TradeExitTrigger)RegistryTypeElement.getElement().newInstance();
					return exp;
				} catch (Exception e) {
					throw new Exception("Create Exit Trigger Type " + e.toString() + " " +  type.getClass().getName());
				}
			}
		}
		throw new Exception("Create Exit Trigger Type Not Cound " + type.getClass().getName());
	}

	@Override
	public TradeValidator tradeValidator(TradeValidatorType type) throws Exception {
		for (RegistryTypeElement RegistryTypeElement : tradeValidators) {
			if(RegistryTypeElement.getType().isInstance(type)) { 
				try {
					TradeValidator exp = (TradeValidator)RegistryTypeElement.getElement().newInstance();
					return exp;
				} catch (Exception e) {
					throw new Exception("Create Validator = Type " + e.toString() + " " +  type.getClass().getName());
				}
			}
		}
		throw new Exception("Create Validaor Type Not Cound " + type.getClass().getName());
	}

	private class RegistryTypeElement {

		private Class<?> element;
		private Class<?> type;

		public RegistryTypeElement(Class<?> element, Class<?> type) {
			this.element = element;
			this.type = type;
		}

		public Class<?> getElement() {
			return element;
		}

		public Class<?> getType() {
			return type;
		}

	}

}
