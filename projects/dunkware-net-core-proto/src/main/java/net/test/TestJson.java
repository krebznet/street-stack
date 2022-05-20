package net.test;

import com.dunkware.net.proto.core.GOperator;
import com.dunkware.net.proto.netstream.GEntityCriteriaVar;
import com.dunkware.net.proto.netstream.GEntityCriteriaVarType;
import com.dunkware.net.proto.netstream.GEntityMatcher;
import com.dunkware.net.proto.netstream.GEntityVarCriteria;
import com.dunkware.net.proto.netstream.GNetScannerStopRequest;
import com.google.protobuf.util.JsonFormat;

public class TestJson {

	public static void main(String[] args) {
		GNetScannerStopRequest request = GNetScannerStopRequest.newBuilder().setScannerIdent("sdfsdf").build();
		GEntityCriteriaVar critVar = GEntityCriteriaVar.newBuilder().setType(GEntityCriteriaVarType.VALUE_RELATIVE).setId(1).setIdent("3").build();
		GEntityMatcher matcher = 
		GEntityMatcher.newBuilder().addVarCriterias(GEntityVarCriteria.newBuilder().setVar(critVar).setOperator(GOperator.GT).setValue("stringha").build()).build();
		
		try {
			String me = JsonFormat.printer().print(matcher);
			GEntityMatcher.Builder b = GEntityMatcher.newBuilder();
			JsonFormat.parser().ignoringUnknownFields().merge(me, b);
			
			GEntityMatcher m = b.build();
			System.out.println(m.getVarCriterias(0).getVar().getTypeValue());
			System.out.println(JsonFormat.printer().print(m));
			System.out.println(me);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
	}
}
