package com.dunkware.trade.broker.tws.connector;

import com.dunkware.trade.broker.tws.scanner.TwsScanner;
import com.dunkware.trade.broker.tws.scanner.TwsScannerEvent;
import com.dunkware.trade.broker.tws.scanner.TwsScannerInput;
import com.dunkware.trade.broker.tws.scanner.TwsScannerInstrument;
import com.dunkware.trade.broker.tws.scanner.TwsScannerListener;
import com.dunkware.trade.broker.tws.scanner.TwsScannerService;
import com.dunkware.trade.broker.tws.scanner.TwsScannerType;

public class TwsConnectorTest implements TwsScannerListener  {

	private TwsConnector _twsConnector;
	public TwsConnectorTest() {
		Thread runner = new Thread() { 
			public void run() { 
				_twsConnector = new TwsConnector();
				_twsConnector.addConnectorService(new TwsScannerService());
				try {
					_twsConnector.startConnector("localhost", 7495);
					Thread.sleep(1000);
					TwsScannerInput input = new TwsScannerInput(TwsScannerType.TOP_PERC_GAIN);
					input.setInstrument(TwsScannerInstrument.USStocks);
					TwsScannerService scannerService = (TwsScannerService)_twsConnector.getService(TwsScannerService.class);
					TwsScanner scanner = scannerService.createScanner(input);
					scanner.addListener(TwsConnectorTest.this);
					while(true) { 
						try {
							Thread.sleep(1000);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		runner.start();
	}
	@Override
	public void scannerEvent(TwsScannerEvent event) {
		
		
		
	}

	
	public static void main(String[] args) {
		TwsConnectorTest test = new TwsConnectorTest();
	}
	
}
