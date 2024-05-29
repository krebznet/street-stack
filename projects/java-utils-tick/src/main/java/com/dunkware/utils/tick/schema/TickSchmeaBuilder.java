package com.dunkware.utils.tick.schema;

public class TickSchmeaBuilder {

	public static TickSchmeaBuilder newBuilder(String schemaName) {
		return new TickSchmeaBuilder(schemaName);
	}
	
	
	private TickSchema schema; 

	private TickSchmeaBuilder(String schemaName) {
		schema = new TickSchema();
		schema.setName(schemaName);
	}

	public  class TickTypeBuilder {

		private TickSchmeaBuilder schemaBuilder;

		private TickType tickType;
		

		public TickTypeBuilder(TickSchmeaBuilder schemaBuilder, int id, String name) {
			tickType = new TickType();
			tickType.setId(id);
			tickType.setName(name);
		}

		public TickTypeBuilder idField(int id, String name) {
			TickTypeField field = new TickTypeField();
			tickType.setId(id);
			field.setId(id);
			field.setName(name);
			tickType.getFields().add(field);
			return this;
		}
		
		public TickTypeBuilder identField(int id, String name) { 
			TickTypeField field = new TickTypeField();
			field.setId(id);;
			field.setName(name);
			tickType.setId(id);;
			tickType.getFields().add(field);
			return this; 
		}
		
		public TickTypeBuilder field(int id, String name) { 
			TickTypeField field = new TickTypeField();
			field.setId(id);
			field.setName(name);
			tickType.getFields().add(field);
			return this;
		}
		
		public TickSchmeaBuilder build() { 
			schema.getTypes().add(tickType);
			return schemaBuilder;
		}

	}  

}
