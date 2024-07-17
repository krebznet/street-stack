package com.dunkware.trade.engine.model.api.node;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.trade.engine.model.api.TradeNodeType;
import com.dunkware.trade.engine.model.api.TradeValidatorType;
import com.dunkware.trade.engine.model.api.extend.TradeEntryExecutorType;
import com.dunkware.trade.engine.model.api.extend.TradeEntryTriggerType;
import com.dunkware.trade.engine.model.api.extend.TradeExitExecutorType;
import com.dunkware.trade.engine.model.api.extend.TradeExitTriggerType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Predefined template within a TradeSystem 
 * it has optianl entry triggers, a entry executor
 * exit executor and exit trigges. as well as 
 * validatoer for throttle and restriciting. 
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeStrategyType extends TradeNodeType {

	public static class Builder  {
		
		private TradeStrategyType strat = new TradeStrategyType();
		
		public Builder setEntryExecutor(TradeEntryExecutorType type) { 
			strat.setEntryExecutor(type);
			return this;
		}
		
		public Builder setExitExecutor(TradeExitExecutorType type) { 
			strat.setExitExecutor(type);;
			return this;
		}
		
		public Builder addExitTrigger(TradeExitTriggerType type) { 
			strat.getExitTriggers().add(type);
			
			return this; 
		}
		
		public Builder addEntryTriggerType(TradeEntryTriggerType type) { 
			strat.getEntryTrggers().add(type);
			return this;
		}
		
		public Builder addTradeValidaor(TradeValidatorType type) { 
				strat.getTradeValidators().add(type);
			return this; 
		}
		
		public Builder setWeight(int weight) { 
			strat.setWeightEnabled(true);
			strat.setWeight(weight);
			return this;
		}
		
		public Builder setCapital(double allocatedCapital, double tradeCapital) { 
			strat.setAllocatedCapital(allocatedCapital);
			strat.setTradeCapital(tradeCapital);
			return this;
		}
		
		public TradeStrategyType build() { 
			return strat;
		}
		
		
		
	}
	
	
	private TradeEntryExecutorType entryExecutor; 
	private TradeExitExecutorType exitExecutor;
	protected List<TradeExitTriggerType> exitTriggers = new ArrayList<TradeExitTriggerType>();
	protected List<TradeEntryTriggerType> entryTrggers = new ArrayList<TradeEntryTriggerType>();
	protected List<TradeValidatorType> tradeValidators = new ArrayList<TradeValidatorType>();
	protected double allocatedCapital;
	protected double tradeCapital = 300.00;
	private boolean weightEnabled = false;
	private int weight; 

	

}
