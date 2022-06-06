package com.dunkware.net.core.data;

import com.dunkware.net.proto.data.GField;

public interface NetBeanObserver {

	public void fieldUpdate(NetBean bean, GField field);
}
