package dafdt.utils;

import dafdt.models.DataAttribute;
import weka.core.Utils;

public class Condition {
	
	
	
	DataAttribute attribute;
	//public String attribute;
	public NodeSide nodeSide;
	public double threshold;
	public boolean isCategorical;
	public boolean isNominal;
	
	public Condition(DataAttribute attribute) {
		this.attribute = attribute;
		this.isCategorical = false;
	}
	public Condition(DataAttribute attribute, boolean iscategorical) {
		this.attribute = attribute;
		this.isCategorical = iscategorical;
		this.isNominal = iscategorical;
	}
	public Condition(DataAttribute attribute, boolean iscategorical, NodeSide nodeside, double threshold) {
		this.attribute = attribute;
		this.isCategorical = iscategorical;
		this.nodeSide = nodeside;
		this.threshold = threshold;
	}
	
	@Override
	public String toString() {
		
		StringBuilder result = new StringBuilder();
		
		result.append(attribute.name());
		if(nodeSide!=null) {
			switch (nodeSide) {
			case Left:
				result.append(" <= ");
				break;
			case Right:
				result.append(" > ");
				break;			
			}
			
			result.append(Utils.roundDouble(threshold,4));
		}
		else {		
			result.append(" = ");
			result.append(attribute.fixedValue);
		}
		//result.append(String.format("%-9.3f", threshold));
		
		return result.toString();
	}
}
