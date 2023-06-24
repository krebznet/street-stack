package com.dunkware.trade.service.stream.json.streamprops;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.json.DJson;

public class StreamLabels {
	
	public static void main(String[] args) {
		StreamLabels l = new StreamLabels();
		VarLabel v = new VarLabel();
		v.setIdent("VolCount30Sec");
		v.setLabel("Moving Volume 30 Second");
		l.getVars().add(v);
		SignalLabel s = new SignalLabel();
		s.setIdent("TRADE1");
		s.setLabel("Breakout 1");
		l.getSignals().add(s);
		try {
			System.out.println(DJson.serializePretty(l));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private List<VarLabel> vars = new ArrayList<VarLabel>();
	private List<SignalLabel> signals = new ArrayList<SignalLabel>();
	
	public List<VarLabel> getVars() {
		return vars;
	}
	public void setVars(List<VarLabel> vars) {
		this.vars = vars;
	}
	public List<SignalLabel> getSignals() {
		return signals;
	}
	public void setSignals(List<SignalLabel> signals) {
		this.signals = signals;
	}
	
	

}
