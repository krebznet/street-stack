package com.dunkware.net.core.runtime.data.wrapper;

import com.dunkware.net.proto.core.GBeanUpdate;

public interface DataBeanListener {
	
	public void beanUpdate(DataBean bean, GBeanUpdate update);

}
