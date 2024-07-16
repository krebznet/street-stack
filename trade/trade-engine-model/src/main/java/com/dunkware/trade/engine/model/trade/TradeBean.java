package com.dunkware.trade.engine.model.trade;


import java.beans.PropertyChangeSupport;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import com.dunkware.trade.engine.model.order.TradeOrderBean;
import com.dunkware.utils.core.observable.ObservableBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeBean extends ObservableBean 	 {
	
    private String source;
    private String key;
    private String ticker;
    private String tickerType;
    private String status;
    private LocalDateTime openingTimestamp;
    private LocalDateTime openTimestamp;
    private LocalDateTime closingTimestamp;
    private LocalDateTime closedTimestamp;
    private Duration tradeDuration;
    private Duration openingDuration;
    private Duration closingDuration;
    private int allocatedShares;
    private double allocatedCapital;
    private double tradeCapital;
    private int tradeSize;
    private double avgEntryPrice;
    private double avgExitPrice;
    private double exitCommision;
    private double entryCommision;
    private String tradeSide;
    private double tradeCommission;
    private double lastPrice;
    
    private List<TradeOrderBean> orders;
    
    private PropertyChangeSupport support;

    

}
