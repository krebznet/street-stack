package com.dunkware.common.util.properties;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.datatype.DataTypes;

public class DPropertiesRuleValidator implements DPropertiesValidator {


	private List<Rule> rules;

	private DPropertiesRuleValidator(List<Rule> rules) {
		this.rules = rules;
	}
	
	public static DPropertiesRuleValidator.Builder newBuilder() { 
		return new DPropertiesRuleValidator.Builder();
	}

	@Override
	public void validate(DProperties props) throws DPropertiesException {
		for (Rule rule : rules) {

			if (rule.dataType == DataTypes.STRING) {
				try {
					props.getString(rule.property);
				} catch (Exception e) {
					throw new DPropertiesException("Validation Rule " + rule.property + " " + rule.dataType+ " failed");
				}
			}
			
			if (rule.dataType == DataTypes.INTEGER) {
				try {
					props.getInt(rule.property);
				} catch (Exception e) {
					throw new DPropertiesException("Validation Rule " + rule.property + " " + rule.dataType + " failed");
				}
			}
			
			if (rule.dataType == DataTypes.BOOLEAN) {
				try {
					props.getBoolean(rule.property);
				} catch (Exception e) {
					throw new DPropertiesException("Validation Rule " + rule.property + " " + rule.dataType + " failed");
				}
			}

		}
	}

	private static class Rule {

		public String property;
		public int dataType;

	}

	public static class Builder {
		
		private List<Rule> rules = new ArrayList<DPropertiesRuleValidator.Rule>();
		
		public Builder() { 
			
		}
		
		public Builder addRule(String property, int dataType) {
			Rule rule = new Rule();
			rule.property = property;
			rule.dataType = dataType;
			rules.add(rule);
			return this;
		}
		
		public DPropertiesRuleValidator build() { 
			return new DPropertiesRuleValidator(rules);
		}
		
		
	}

}
