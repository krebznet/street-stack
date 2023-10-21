package com.dunkware.trade.broker.tws.scanner;

public class TwsScannerInput {
	
	private Double _abovePrice = null;
	private Double _belowPrice = null;
	private Double _aboveMarketCap = null;
	private Double _belowMarketCap = null;
	private Integer _aboveVolume = null;
	private TwsScannerType _scannerType;
	private TwsScannerInstrument _scannerInstrument = TwsScannerInstrument.USStocks;
	
	public TwsScannerInput(TwsScannerType type) { 
		_scannerType = type;
	}
	
	public void setInstrument(TwsScannerInstrument instrument) { 
		_scannerInstrument = instrument;
	}
	
	public TwsScannerInstrument getInstrument() { 
		return _scannerInstrument;
	}
	public void setScannerType(TwsScannerType type) {
		_scannerType = type;
	}
	
	public TwsScannerType getScannerType() { 
		return _scannerType;
	}
	
	public void setAbovePrice(Double price) { 
		_abovePrice = price;
	}
	
	public void setBelowPrice(Double price) { 
		_belowPrice = price;
	}
	
	public Double getAbovePrice() { 
		return _abovePrice;
	}
	
	public Double getBelowPrice() { 
		return _belowPrice;
	}
	
	public void setAboveVolume(Integer volume) { 
		_aboveVolume = volume;
	}

	public Integer getAboveVolume() { 
		return _aboveVolume;
	}
	
	public void setAboveMarketCap(Double above) { 
		this._aboveMarketCap = above;
	}
	
	public Double getAboveMarketCap() { 
		return _aboveMarketCap;
	}
	
	public void setBelowMarketCap(Double below) { 
		this._belowMarketCap = below;
	}
	
	public Double getBelowMarketCap() { 
		return _belowMarketCap;
	}
}
