package com.dunkware.net.core.channel;

import com.dunkware.net.core.data.NetBean;

public interface NetChannel {
	
	void close();
	
	void send(NetBean bean); 
	
	void addObserver(NetChannelObserver observer);
	
	void removeObserver(NetChannelObserver observer);
	
	boolean isOpen();
	
	int getId();

}
