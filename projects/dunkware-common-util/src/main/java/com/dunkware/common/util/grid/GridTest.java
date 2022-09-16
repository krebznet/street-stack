package com.dunkware.common.util.grid;

import com.dunkware.common.util.grid.action.GridAction;
import com.dunkware.common.util.grid.rules.GridRowRule;
import com.dunkware.common.util.grid.rules.GridRowRules;
import com.dunkware.common.util.grid.rules.GridRowRulesType;
import com.dunkware.common.util.json.DJson;

public class GridTest {

	public static void main(String[] args) {
		
		GridOptions options = new GridOptions();
		GridColumn actionsCol = new GridColumn(); 
		actionsCol.setHeaderName("Actions");
		GridAction startAction = new GridAction();
		startAction.setIcon("/assets/icons/start.png");
		startAction.setUrl("/trade/web/start/bot=?{bot.id}");
		GridRowRules rules = new GridRowRules(); 
		rules.setType(GridRowRulesType.AND);
		GridRowRule rule = new GridRowRule();
		rule.setField("status");
		rule.setOperator(GridOperator.EQ);
		rule.setValue("stopped");
		rules.getRules().add(rule);
		startAction.setRules(rules);
		
		actionsCol.getActions().add(startAction);
		
		GridAction stopAction = new GridAction();
		stopAction.setIcon("/assets/icons/stop.png");
		stopAction.setUrl("/trade/web/start/bot=?{bot.id}");
		GridRowRules stopRules = new GridRowRules(); 
		stopRules.setType(GridRowRulesType.AND);
		GridRowRule stopRule = new GridRowRule();
		stopRule.setField("status");
		stopRule.setOperator(GridOperator.EQ);
		stopRule.setValue("running");
		stopRules.getRules().add(rule);
		stopAction.setRules(rules);
		actionsCol.getActions().add(stopAction);
		
		options.getColumns().add(actionsCol);
		
		GridColumn binding = new GridColumn();
		binding.setHeaderName("Status");
		binding.setField("status");
		options.getColumns().add(binding);
		
		try {
			System.out.println(DJson.serializePretty(options));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
