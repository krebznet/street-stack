package com.dunkware.net.data.wrapper;

import com.dunkware.net.proto.core.GBeanUpdate;

public interface DataBeanListener {
	
	public void beanUpdate(DataBean bean, GBeanUpdate update);

}
