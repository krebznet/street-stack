package com.dunkware.trade.service.stream.server.controller.session.container.scanner;

import com.dunkware.common.util.data.NetScannerDelta;

public interface SessionContainerEntityScannerListener {

	void scannerDelta(NetScannerDelta delta);
}
