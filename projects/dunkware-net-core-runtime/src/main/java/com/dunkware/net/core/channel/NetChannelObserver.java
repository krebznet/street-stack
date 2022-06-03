package com.dunkware.net.core.channel;

import com.dunkware.net.core.data.NetBean;

public interface NetChannelObserver {
	
	public void onNext(NetBean bean);
	
	public void onClose();
	
	public void onError(Throwable t);

}
