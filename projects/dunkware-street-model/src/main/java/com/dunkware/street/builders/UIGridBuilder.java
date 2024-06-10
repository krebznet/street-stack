package com.dunkware.street.builders;

import com.dunkware.street.model.UIGridColumn;

public class UIGridBuilder {
	
	public static UIGridBuilder newBuilder() { 
		return new UIGridBuilder();
	}
	
	
	
	public static class UIGridcolumnBuilder { 
		
		private UIGridBuilder builder; 
		private UIGridColumn column = new UIGridColumn();
		public UIGridcolumnBuilder(UIGridBuilder gridB) { 
			this.builder = gridB;
			
		}
		
		public UIGridcolumnBuilder setType() { 
			return null;
		}
		
		
	}

}
