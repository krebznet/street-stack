package com.dunkware.trade.sdk.lib.runtime.paper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.trade.sdk.core.model.broker.BrokerSpec;
import com.dunkware.trade.sdk.core.model.broker.BrokerStatus;
import com.dunkware.trade.sdk.core.model.broker.BrokerType;
import com.dunkware.trade.sdk.core.runtime.broker.BrokerAccount;
import com.dunkware.trade.sdk.core.runtime.broker.anot.ABroker;
import com.dunkware.trade.sdk.core.runtime.broker.impl.BrokerImpl;
import com.dunkware.trade.sdk.lib.model.paper.PaperBrokerType;
import com.dunkware.trade.tick.api.instrument.Instrument;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

@ABroker(type = PaperBrokerType.class)
public class PaperBroker extends BrokerImpl {

	private PaperBrokerType myType; 
	private DEventNode eventNode; 
	private DExecutor executor; 
	
	private List<PaperAccount> accounts = new ArrayList<PaperAccount>();
	
	private ConcurrentHashMap<String, PaperInstrument> instruments = new ConcurrentHashMap<String, PaperInstrument>();
	
	@Override
	public void connect(BrokerType type, DEventNode eventNode, DExecutor executor) throws Exception {
		this.myType = (PaperBrokerType)type;
		this.eventNode = eventNode; 
		this.executor = executor; 
		int i = 0; 
		setStatus(BrokerStatus.Connected);
		while(i < myType.getAccounts()) { 
			PaperAccount account = new PaperAccount(this, myType.getIdentifier().toUpperCase() + (i + 1));
			accounts.add(account);
			i++;
		}
	}

	@Override
	public void disconnect() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BrokerAccount getAccount(String accountId) throws Exception {
		for (PaperAccount paperAccount : accounts) {
			if(paperAccount.getIdentifier().equals(accountId)) { 
				return paperAccount;
			}
		}
		throw new Exception("Account " + accountId + " not found");
	}

	@Override
	public BrokerSpec getSpec() {
		BrokerSpec spec = new BrokerSpec();
		spec.setStatus(BrokerStatus.Connected);
		spec.setIdentifier(myType.getIdentifier());
		return spec; 
	}

	@Override
	public DEventNode getEventNode() {
		return eventNode; 
	}

	@Override
	public BrokerAccount[] getAccounts() {
		return accounts.toArray(new BrokerAccount[accounts.size()]);
	}

	@Override
	public String getIdentifier() {
		return myType.getIdentifier();
	}

	@Override
	public DExecutor getExecutor() {
		return executor; 
	}

	@Override
	protected Instrument subscribe(TradeTickerSpec ticker) throws Exception {
		PaperInstrument instrument = instruments.get(ticker.getSymbol());
		if(instrument == null) { 
			instrument = new PaperInstrument(ticker);
			instruments.put(ticker.getSymbol(), instrument);
		}
		return instrument; 
	}

	@Override
	protected void unsubscribe(Instrument instrument) {
		// TODO Auto-generated method stub
		
	}

}
