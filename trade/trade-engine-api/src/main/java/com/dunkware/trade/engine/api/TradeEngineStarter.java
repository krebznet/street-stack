package com.dunkware.trade.engine.api;

import java.util.List;

import com.dunkware.trade.engine.model.api.extend.TradeValidatorType;
import com.dunkware.utils.core.concurrent.DunkExecutor;
import com.dunkware.utils.core.events.DunkEventNode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeEngineStarter {
	
	private OrderExecutor orderExecutor;
	
	private List<TradeValidatorType> tradeValidators;
	
	private double allocatedCapital;
	
	private DunkEventNode parentNode; 
	
	private DunkExecutor taskExecutor; 
	
	

}
