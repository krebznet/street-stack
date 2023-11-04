package com.dunkware.trade.service.stream.server.blueprint.varmeta;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.common.util.helpers.DFileHelper;

public class VarMetaDataRegistry {
	
	
	public static final String data = "# Tick Feed Variables \n"
			+ "1~TickSymbol~Tick Symbol~Tick Feed~tick(202,0,STR)\n"
			+ "2~TickLast~Price~Tick Feed~tick(202,1,DUB)\n"
			+ "3~TickVolume~Volume~Tick Feed~tick(202,27,LONG)\n"
			+ "4~TickTradeCount~Trade Count~Tick Feed~tick(202,30,INT)\n"
			+ "100~TickLast1sec~Price1 Second Update~Tick Snapshot~snapshot(TickLast[0])1SEC\n"
			+ "101~TickVolume1sec~Volume 1 Second Update~Tick Snapshot~snapshot(TickVolume[0])1SEC\n"
			+ "102~TickTradeCount1sec~Trade Count 1 Second Update~Tick Snapshot~snapshot(TickTradeCount[0])1SEC\n"
			+ "1010~Sma10sec~Simple Moving Average 10 Second~Simple Moving Average~avg({TickLast1sec[0,9]}}\n"
			+ "1020~Sma20sec~Simple Moving Average 20 Second~Simple Moving Average~avg({TickLast1sec[0,19]})\n"
			+ "1030~Sma30sec~Simple Moving Average 30 Second~Simple Moving Average~avg({TickLast1sec[0,29]})\n"
			+ "1040~Sma40sec~Simple Moving Average 40 Second~Simple Moving Average~avg({TickLast1sec[0,39]}}\n"
			+ "1050~Sma50sec~Simple Moving Average 50 Second~Simple Moving Average~avg({TickLast1sec[0,49]}}\n"
			+ "2001~Sma1min~Simple Moving Average 1 Minute~Simple Moving Average~avg({TickLast1sec[0,59]})\n"
			+ "2002~Sma2min~Simple Moving Average 2 Minute~Simple Moving Average~avg({TickLast1sec[0,119]})\n"
			+ "2003~Sma3min~Simple Moving Average 3 Minute~Simple Moving Average~avg({TickLast1sec[0,179]})\n"
			+ "2004~Sma4min~Simple Moving Average 4 Minute~Simple Moving Average~avg({TickLast1sec[0,239]})\n"
			+ "2005~Sma5min~Simple Moving Average 5 Minute~Simple Moving Average~avg({TickLast1sec[0,299]})\n"
			+ "5005~Mvc5sec~Moving Volume Count 5 Second~Moving Volume Count~sub(TickVolume1sec[0],TickVolume1sec[4])\n"
			+ "5010~Mvc5sec~Moving Volume Count 10 Second~Moving Volume Count~sub(TickVolume1sec[0],TickVolume1sec[9])\n"
			+ "5030~Mvc30sec~Moving Volume Count 30 Second~Moving Volume Count~sub(TickVolume1sec[0],TickVolume1sec[29])\n"
			+ "5060~Mvc5sec~Moving Volume Count 1 Minute~Moving Volume Count~sub(TickVolume1sec[0],TickVolume1sec[59])\n"
			+ "6005~Vcb5sec~Volume Count Bar 5 Second~Volume Count Bar~snapshot(sub(TickVolume1sec[0],TickVolume1sec[4]))5SEC\n"
			+ "6010~Vcb10sec~Volume Count Bar 10 Second~Volume Count Bar~snapshot(sub(TickVolume1sec[0],TickVolume1sec[0]))10SEC\n"
			+ "6015~Vcb5sec~Volume Count Bar 15 Second~Volume Count Bar~snapshot(sub(TickVolume1sec[0],TickVolume1sec[14]))15SEC\n"
			+ "6030~Vcb30sec~Volume Count Bar 50 Second~Volume Count Bar~snapshot(sub(TickVolume1sec[0],TickVolume1sec[29]))30SEC\n"
			+ "6060~Vcb60sec~Volume Count Bar 1 Minute~Volume Count Bar~snapshot(sub(TickVolume1sec[0],TickVolume1sec[59]))60SEC\n"
			+ "7005~Mtc5sec~Moving Trade Count 5 Second~Moving Trade Count~sub(TickTradeCount1sec[0],TickTradeCount1sec[4])\n"
			+ "7010~Mtc10sec~Moving Trade Count 10 Second~Moving Trade Count~sub(TickTradeCount1sec[0],TickTradeCount1sec[9])\n"
			+ "7015~Mtc15sec~Moving Trade Count 15 Second~Moving Trade Count~sub(TickTradeCount1sec[0],TickTradeCount1sec[14])\n"
			+ "7030~Mtc30sec~Moving Trade Count 30 Second~Moving Trade Count~sub(TickTradeCount1sec[0],TickTradeCount1sec[29])\n"
			+ "8005~Tcb5sec~Trade Count Bar 5 Second~Trade Count Bar~snapshot(sub(TickTradeCount1sec[0],TickTradeCount1sec[4]))5SEC\n"
			+ "8010~Tcb10sec~Trade Count Bar 10 Second~Trade Count Bar~snapshot(sub(TickTradeCount1sec[0],TickTradeCount1sec[10]))10SEC\n"
			+ "8015~Tcb15sec~Trade Count Bar 15 Second~Trade Count Bar~snapshot(sub(TickTradeCount1sec[0],TickTradeCount1sec[14]))15SEC\n"
			+ "8030~Tcb30sec~Trade Count Bar 30 Second~Trade Count Bar~snapshot(sub(TickTradeCount1sec[0],TickTradeCount1sec[29]))30SEC\n"
			+ "8060~Tcb30sec~Trade Count Bar 60 Second~Trade Count Bar~snapshot(sub(TickTradeCount1sec[0],TickTradeCount1sec[59]))60SEC\n"
			+ "31020~SmaRoc10x20sec~SMA Rate of Change 10x20 Second~SMA Rate of Change~roc(Sma10sec[0],Sma20sec[0])\n"
			+ "32030~SmaRoc20x30sec~SMA Rate of Change 20x30 Second~SMA Rate of Change~roc(Sma20sec[0],Sma30sec[0])\n"
			+ "33040~SmaRoc30x40sec~SMA Rate of Change 30x40 Second~SMA Rate of Change~roc(Sma30sec[0],Sma40sec[0])\n"
			+ "34050~SmaRoc40x50sec~SMA Rate of Change 40x50 Second~SMA Rate of Change~roc(Sma40sec[0],Sma50sec[0])\n"
			+ "35060~SmaRoc50x60sec~SMA Rate of Change 50x60 Second~SMA Rate of Change~roc(Sma50sec[0],sma1min[0])\n"
			+ "40102~SmaRoc1x2min~SMA Rate of Change 1X2 Minute~Sma Rate of Change~roc(Sma1min[0],Sma2min[0])\n"
			+ "40203~SmaRoc2x3min~SMA Rate of Change 2X3 Minute~Sma Rate of Change~roc(Sma2min[0],Sma3min[0])\n"
			+ "40304~SmaRoc3x4min~SMA Rate of Change 3X4 Minute~Sma Rate of Change~roc(Sma3min[0],Sma4min[0])\n"
			+ "40405~SmaRoc4x5min~SMA Rate of Change 4X5 Minute~Sma Rate of Change~roc(Sma4min[0],Sma5min[0])";
	
	private Map<Integer,VarMetaData> registry = new ConcurrentHashMap<Integer,VarMetaData>();
	
	
	public static void main(String[] args) {
		try {
			String split[] = data.split("\n");
			for (String string : split) {
				System.out.println(string);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	public void load( ) throws Exception { 
		String[] fileLines = data.split("\n"); 
		for (String string : fileLines) {
			if(string.startsWith("#")) { 
				continue;
			}
			String[] split = string.split("~");
			int id = Integer.valueOf(split[0]);
			String ident = split[1];
			String name = split[2];
			String group = split[3];
			String expression = split[4];
			VarMetaData meta = new VarMetaData();
			meta.setId(id);
			meta.setGroup(group);;
			meta.setIdentifier(ident);
			meta.setName(name);;
			meta.setExpression(expression);
			registry.put(id, meta);
		}
			
	}
	
	
	public VarMetaData getVarMetaData(int id) throws Exception { 
		VarMetaData meta = registry.get(id);
		if(meta == null) { 
			throw new Exception("Var MetaDat not found for variable id " + id);
		}
		return meta;
	}
	
	
	public Collection<VarMetaData> getVarMetaData() { 
		return registry.values();
	}

}
