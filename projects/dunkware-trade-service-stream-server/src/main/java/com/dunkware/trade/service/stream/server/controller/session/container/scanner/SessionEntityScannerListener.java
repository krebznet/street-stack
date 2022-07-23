package com.dunkware.trade.service.stream.server.controller.session.container.scanner;

import com.dunkware.common.util.data.NetScannerDelta;

public interface SessionEntityScannerListener {

	void scannerDelta(NetScannerDelta delta);
}
