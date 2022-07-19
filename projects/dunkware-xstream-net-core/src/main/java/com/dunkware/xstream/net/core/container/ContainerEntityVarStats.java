package com.dunkware.xstream.net.core.container;

import java.time.LocalDateTime;

public interface ContainerEntityVarStats {
	
	LocalDateTime getHighTime();
	
	Object getHigh();
	
	LocalDateTime getLowTime(); 
	
	Object getLow(); 

}
