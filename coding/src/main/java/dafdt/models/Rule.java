package dafdt.models;

import dafdt.wekaex.AdaBoostM1Ex;
import dafdt.wekaex.AttributeEx;
import dafdt.wekaex.ClassifierEx;
import dafdt.wekaex.J48Ex;
import de.lmu.ifi.dbs.elki.data.Cluster;
import de.lmu.ifi.dbs.elki.data.Clustering;
import de.lmu.ifi.dbs.elki.data.model.KMeansModel;
import de.lmu.ifi.dbs.elki.data.model.Model;
import weka.classifiers.Classifier;
import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;
import weka.clusterers.DBSCAN;
import weka.core.Attribute;
import weka.core.AttributeStats;
import weka.core.Instances;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class Rule {

	public double total;
	public ArrayList<Condition> conditions;
	public double data;
	public double incorrect;
	public String classvalue;
	public boolean isCategorical;
	public double[][] generatedData;
	public double weight;
	public Instances originaldata;
	public DBSCAN clusterInfoDBSCAN;
	public Cluster<Model> dbscanclusters;
	public Clustering<KMeansModel> kmeansclusters;
	public ArrayList<double[]> centroidsList;


	public boolean isCategorical() {
		return isCategorical;
	}
	public void setCategorical(boolean isCategorical) {
		this.isCategorical = isCategorical;
	}
	public String getClassvalue() {
		return classvalue;
	}
	public void setClassvalue(String classvalue) {
		this.classvalue = classvalue;
	}
	public Rule() {
		conditions = new ArrayList<Condition>();
		incorrect = 0;
	}
	public Rule(double total, double incorrect) {
		conditions = new ArrayList<Condition>();
		this.incorrect = incorrect;
		this.total = total;
	}

	public double getData() {
		return this.data;
	}


	@Override
	public String toString() {

		StringBuilder result =  new StringBuilder();
		//result.append("Number of instances: " + total + " ");
		//result.append("Conditions: ");
		for (Condition condition : conditions) {
			result.append(" (");
			result.append(condition.toString());
			result.append(") ");
		}
		if(isCategorical) {
			result.append("Class: " + classvalue);
		}else {
			result.append("Class: " + data );
		}

		return result.toString();
	}

	public double getRuleInfluence(double totalInstances) {
		double result = 0;		
		result = (total/totalInstances);		
		return result;
	}

	private ArrayList<Condition> filterConditions(){
		ArrayList<Condition> filteredConditions = new ArrayList<Condition>();
		ArrayList<DataAttribute> dataattributes = new ArrayList<DataAttribute>();

		for (Condition condition : conditions) {
			Condition hold = condition;
			if(!condition.isCategorical) {
				for(Condition c : conditions) {
					if(condition.nodeSide.equals(c.nodeSide) && condition.attribute.name().equals(c.attribute.name())) {
						switch (condition.nodeSide) {
						case Left:
							if(hold.threshold >= c.threshold) {hold=c;}
							break;
						case Right:
							if(hold.threshold <= c.threshold) {hold=c;}
							break;
						}
					}
				} 
			}
			if(!filteredConditions.contains(hold))filteredConditions.add(hold);
		}

		//creates empty-valued conditions for attributes which are not in the rule




		return filteredConditions;
	}


	public ArrayList<DataAttribute> discoverAttributesDataBoundaries(ArrayList<Attribute> metadatalist, ArrayList<AttributeStats> stats){
		ArrayList<Condition> conditions = filterConditions();
		ArrayList<Condition> boundaries = new ArrayList<Condition>();

		ArrayList<DataAttribute> attributelist = new ArrayList<DataAttribute>();
		for(Attribute a: metadatalist) {
			DataAttribute da = new DataAttribute(a.name());
			if(stats.get(metadatalist.indexOf(a)).numericStats!=null) {
				da.setMax(stats.get(metadatalist.indexOf(a)).numericStats.max);
				da.setMin(stats.get(metadatalist.indexOf(a)).numericStats.min);
			}
			
			//if conditions are empty
			//boolean daConstraint = false;

			for(Condition c: conditions) {
				if(!c.isCategorical) {
					if(c.attribute.name().equals(a.name())) {
						switch (c.nodeSide) {
						case Left:
							da.setMax(c.threshold);
							break;
						case Right:
							da.setMin(c.threshold);
							break;

						default:
							break;
						}

					}
				}
				else {
					if(c.attribute.name().equals(a.name())) {
						//da.setMax(Double.parseDouble(c.attribute.getFixedValue()));
						//da.setMin(Double.parseDouble(c.attribute.getFixedValue()));
						da.fixedValue = c.attribute.getFixedValue();
						if(da.fixedValue == "22") {
							da.isNominal = true;
						}
						
					}

				}
			}

			attributelist.add(da);
		}








		/*for(Condition condition : conditions) {	

			if(!condition.isCategorical) {
				switch (condition.nodeSide) {
				case Left:
					if(attributeExist(condition.attribute.name(), boundaries)) {
						for(Condition c : boundaries) {
							if(condition.attribute.name().equals(c.attribute.name())) {
								c.attribute.setMax(condition.threshold);
							}
						}					
					}
					else {
						condition.attribute.setMax(condition.threshold);
						boundaries.add(condition);
					}
					break;
				case Right:
					if(attributeExist(condition.attribute.name(), boundaries)) {
						for(Condition c : boundaries) {
							if(condition.attribute.name().equals(c.attribute.name())) {
								c.attribute.setMin(condition.threshold);							
							}
						}
					}
					else {
						condition.attribute.setMin(condition.threshold);
						boundaries.add(condition);
					}
					break;
				}
			}
			else {
				condition.attribute.setMax(Double.parseDouble(condition.attribute.getFixedValue()));
				condition.attribute.setMin(condition.attribute.getMax());
				boundaries.add(condition);
			}
		}
		 */

		for(Attribute att : metadatalist) {

			if(!attributeExist(att.name(), conditions)) {
				double min =0,max =0;
				if(stats.get(att.index()).numericStats!=null) {
					min = stats.get(att.index()).numericStats.min;
					max = stats.get(att.index()).numericStats.max;
				}
				for(DataAttribute dataattribute: attributelist) {
					if(dataattribute.name().equals(att.name())) {
						dataattribute.setMax(max);
						dataattribute.setMin(min);
					}
				}
				//DataAttribute da = new DataAttribute(att.name(), min, max);

				//boundaries.add(new Condition(da));

			}

		}
		return attributelist;

	}

	private boolean attributeExist(String name, ArrayList<Condition> conditions) {
		boolean result = false;
		for(Condition condition : conditions) {
			if(condition.attribute.name().equals(name)) {result = true; break;}
		}
		return result;
	}


	public static double[][] getRulesMaxAndMin(ArrayList<Classifier> classifiers) {
		// TODO Auto-generated method stub

		ArrayList<Classifier> classifiersensembled = new ArrayList<Classifier>();
		ArrayList<Rule> rules = new ArrayList<Rule>();
		ArrayList<Attribute> metadatalist = new ArrayList<Attribute>();

		if(classifiers.get(0).getClass().getName().equals(AdaBoostM1.class.getName())) {
			for(Attribute att:Collections.list((((ClassifierEx)((AdaBoostM1Ex)classifiers.get(0)).getClassifiers().get(0)).getTrainingData().enumerateAttributes()))) {metadatalist.add((AttributeEx)att);}
		}
		if(classifiers.get(0).getClass().getName().equals(J48.class.getName())) {
			for(Attribute att:Collections.list( ((ClassifierEx)classifiers.get(0)).getTrainingData().enumerateAttributes())) {metadatalist.add((AttributeEx) att);}
		}
		if(classifiers.get(0).getClass().getName().equals(DecisionStump.class.getName())) {
			for(Attribute att:Collections.list( ((ClassifierEx)classifiers.get(0)).getTrainingData().enumerateAttributes())) {metadatalist.add((AttributeEx)att);}
		}
		//for(Attribute att:Collections.list( ((J48)classifiers.get(0)).getTrainingData().enumerateAttributes())) {metadatalist.add(att);}

		for (Classifier c : classifiers) {
			if(c.getClass().getName().equals(AdaBoostM1.class.getName())) {
				for (Classifier ensembledc : ((AdaBoostM1Ex)c).getFilteredClassifiers()) {
					classifiersensembled.add(ensembledc);
				}
			}
			if(c.getClass().getName().equals(J48.class.getName())) {
				classifiersensembled.add(c);
			}
			if(c.getClass().getName().equals(DecisionStump.class.getName())) {
				classifiersensembled.add(c);
			}
		}

		ArrayList<double[]> maxnmins = new ArrayList<double[]>();

		for (Classifier classifier : classifiersensembled) {

			if(classifier instanceof J48) {
				if(((J48Ex)classifier).NodeCount()>1) {
					//ArrayList<AttributeStats> stats = ((J48)classifier).stats;
					//classifiers.add(classifier);
					for (Rule r : ((J48Ex)classifier).getRules()) {

						ArrayList<DataAttribute> attributes = r.discoverAttributesDataBoundaries(metadatalist, ((J48Ex)classifier).getStats());
						rules.add(r);
						maxnmins.add(new double[] {attributes.get(0).getMin(), attributes.get(0).getMax(),attributes.get(1).getMin(),attributes.get(1).getMax()});					

					}

				}

			}
			else {
				if(classifier instanceof DecisionStump)
				{
					for (Rule r : ((ClassifierEx)classifier).getRules()) {

						ArrayList<DataAttribute> attributes = r.discoverAttributesDataBoundaries(metadatalist, ((ClassifierEx)classifier).getStats());
						rules.add(r);
						maxnmins.add(new double[] {attributes.get(0).getMin(), attributes.get(0).getMax(),attributes.get(1).getMin(),attributes.get(1).getMax()});					


					}


				}
			}

		}

		double[][] rulesMaxAndMin = new double[rules.size()][4];

		for (double[] mm : maxnmins) {
			rulesMaxAndMin[maxnmins.indexOf(mm)] = mm;
		}

		return rulesMaxAndMin;
	}

	public static double[] getRulesLeafsLabels(ArrayList<Classifier> classifiers) {
		ArrayList<Classifier> classifiersensembled = new ArrayList<Classifier>();
		ArrayList<Rule> rules = new ArrayList<Rule>();
		ArrayList<Attribute> metadatalist = new ArrayList<Attribute>();
		double[] result;
		if(classifiers.get(0).getClass().getName().equals(AdaBoostM1.class.getName())) {
			for(Attribute att:Collections.list((((ClassifierEx)((AdaBoostM1Ex)classifiers.get(0)).getClassifiers().get(0)).getTrainingData().enumerateAttributes()))) {metadatalist.add(att);}
		}
		if(classifiers.get(0).getClass().getName().equals(J48.class.getName())) {
			for(Attribute att:Collections.list( ((J48Ex)classifiers.get(0)).getTrainingData().enumerateAttributes())) {metadatalist.add(att);}
		}
		//for(Attribute att:Collections.list( ((J48)classifiers.get(0)).getTrainingData().enumerateAttributes())) {metadatalist.add(att);}


		for (Classifier c : classifiers) {
			if(c.getClass().getName().equals(AdaBoostM1.class.getName())) {
				for (Classifier ensembledc : ((AdaBoostM1Ex)c).getFilteredClassifiers()) {
					classifiersensembled.add(ensembledc);
				}				
			}
			if(c.getClass().getName().equals(J48.class.getName())) {
				classifiersensembled.add(c);
			}
		}

		ArrayList<String> labels = new ArrayList<String>();

		for (Classifier classifier : classifiersensembled) {

			if(classifier instanceof J48) {
				if(((J48Ex)classifier).NodeCount()>1) {
					//ArrayList<AttributeStats> stats = ((J48)classifier).stats;
					//classifiers.add(classifier);
					for (Rule r : ((J48Ex)classifier).getRules()) {

						Double w = (Double)(r.total/(((J48Ex)classifier).TotalInstances()));

						String label =  (new DecimalFormat("0.00")).format(w);
						labels.add(label);
						//labels.add(((Double)(((J48)classifier).TotalInstances()/r.total)).toString());
						//ArrayList<DataAttribute> attributes = r.discoverAttributesDataBoundaries(metadatalist, ((J48)classifier).stats);					
						rules.add(r);					

					}
				}

			}else {
				if(classifier instanceof DecisionStump) {
					for (Rule r : ((ClassifierEx)classifier).getRules()) {

						Double w = (Double)(r.total/(((ClassifierEx)classifier).TotalInstances()));

						String label =  (new DecimalFormat("0.00")).format(w);					
						labels.add(label);
						//labels.add(((Double)(((J48)classifier).TotalInstances()/r.total)).toString());
						//ArrayList<DataAttribute> attributes = r.discoverAttributesDataBoundaries(metadatalist, ((J48)classifier).stats);					
						rules.add(r);					

					}

					
				}
			}
		}
		result = new double[rules.size()];

		for (Rule r: rules) {
			result[rules.indexOf(r)] =r.weight;

		}
		return result;

	}

}
