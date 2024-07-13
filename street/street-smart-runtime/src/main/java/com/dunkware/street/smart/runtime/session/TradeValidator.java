package com.dunkware.street.smart.runtime.session;

import com.dunkware.street.stream.model.session.TradeType;
import com.dunkware.street.stream.model.session.TradeValidatorType;

public interface TradeValidator {
	
	public void start(TradeValidatorType type);
	
	public void validateType(TradeType type) throws Exception;

	public void dispose();
}
