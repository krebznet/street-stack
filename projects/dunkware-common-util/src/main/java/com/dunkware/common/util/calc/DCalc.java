package com.dunkware.common.util.calc;

public class DCalc {

	
	/**
	 * Returns the mean of a range of values. Valid object types are Integer, Long,
	 * Double.
	 * 
	 * @param values
	 * @return
	 * @throws MathException
	 */
	public static double getMean(Object[] values) throws DCalcException {
		double temp = 0;

		int count = 0;
		for (Object object : values) {
			if (object == null) {
				continue;
			}
			if (object instanceof Double) {
				Double value = (Double) object;
				temp += value;
				count++;
				continue;
			}
			if (object instanceof Integer) {
				Integer value = (Integer) object;
				temp += value;
				count++;
				continue;
			}
			if (object instanceof Long) {
				Long value = (Long) object;
				temp += value;
				count++;
				continue;
			}
			throw new DCalcException("Unknown object type in value[] " + object.getClass().getName());

		}
		// format?
		return temp / count;

	}
	


	public static double castRoundTo3(double d) {
		return (long) (d * 1000 + 0.5) / 1000.0;
	}

	
	public static double getPercentageChange(double y1, double y2) {
		return castRoundTo3(((y1-y2) / y2)*100);	
	}
}
