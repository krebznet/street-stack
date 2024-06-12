package comm.dunkware.trade.tick.model.provider;

import com.dunkware.common.util.json.DunkJson;
import com.dunkware.trade.tick.model.provider.TickProviderSpec;

public class TickProviderSpecTest {

	public static void main(String[] args) {
		TickProviderSpec spec = new TickProviderSpec();
		spec.getProperties().put("name", "duncan");
		spec.getProperties().put("port", 200);
		spec.getProperties().put("online", true);
		spec.setName("atick");
		try {
			String serialized = DunkJson.serialize(spec);
			System.out.println(serialized);
			
			TickProviderSpec deserialized = DunkJson.getObjectMapper().readValue(serialized, TickProviderSpec.class);
			System.out.println(deserialized.getProperties().toString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
