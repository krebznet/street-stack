package com.dunkware.xstream.core.extensions;

import java.util.List;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamException;
import com.dunkware.xstream.api.XStreamExtension;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.core.annotations.AXStreamExtension;
import com.dunkware.xstream.util.XStreamHelper;
import com.dunkware.xstream.xproject.model.XStreamExtensionType;

@AXStreamExtension(type = VarSnapshotPrinterType.class)
public class VarSnapshotPrinter implements XStreamExtension {

	private XStream stream; 
	private VarSnapshotPrinterType type;
	
	@Override
	public void init(XStream stream, XStreamExtensionType type) throws XStreamException {
		this.stream = stream;
		this.type = (VarSnapshotPrinterType)type;
	}

	@Override
	public void preStart() throws XStreamException {
		
		
	}

	@Override
	public void start() throws XStreamException {
		stream.getClock().scheduleRunnable(new Printer(), type.getInterval());		
	}

	@Override
	public void preDispose() {
		
		
	}

	@Override
	public void dispose() {
		
		
	}

	private class Printer implements Runnable { 
		
		public void run() { 
			
			List<XStreamEntity> rows = stream.getRows();
			System.out.println("Snapshot Stream Row Count " + rows.size());
			for (XStreamEntity xStreamRow : rows) {
				System.out.println(XStreamHelper.varSnapshotString(xStreamRow));
			}
		}
	}
	
}
