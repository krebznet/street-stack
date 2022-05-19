package com.dunkware.xstream.net.core.scanner;

import java.util.Collection;

public interface StreamSignalScanner {

	public void addListener(StreamSignalScannerListener listener);

	public void removeListener(StreamSignalScannerListener listener);

	public Collection<StreamSignalScannerItem> getItems();

	public void dispose();

}
