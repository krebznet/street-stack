package com.dunkware.xstream.mysql;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamException;
import com.dunkware.xstream.api.XStreamExtension;
import com.dunkware.xstream.core.annotations.AXStreamExtension;
import com.dunkware.xstream.xproject.model.XStreamExtensionType;

@AXStreamExtension(type = MySqlCaptureExtType.class)
public class MySqlCaptureExt implements XStreamExtension {

	private MySqlCaptureExtType type;
	private XStream stream;
	
	private MySqlCaptureRunner runner;

	
	@Override
	public void init(XStream stream, XStreamExtensionType type) throws XStreamException {
		this.type = (MySqlCaptureExtType) type;
		this.stream = stream;
	}

	@Override
	public void preStart() throws XStreamException {

	}

	@Override
	public void start() throws XStreamException {
		runner = new MySqlCaptureRunner();
		MySqlCaptureInput input = new MySqlCaptureInput();
		input.setTablePrefix(type.getTablePrefix());
		input.setCaptureInterval(type.getInterval());
		input.setDatabase(type.getDatabase());
		input.setHost(type.getHost());
		input.setUsername(type.getUsername());
		input.setPassword(type.getPassword());
		input.setPort(type.getPort());
		input.setDropTable(true);
		try {
			runner.startCapture(input, stream);		
		} catch (Exception e) {
			throw new XStreamException("MySqlCapture Runner Exception " + e.toString());
		}
	}

	@Override
	public void preDispose() {
		runner.stopCapture();
	}

	@Override
	public void dispose() {

	}

	

}
