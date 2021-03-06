package dafdt.utils;


import dafdt.models.DataAttribute;
import dafdt.models.Rule;
import dafdt.wekaex.ClassifierEx;
import dafdt.wekaex.NominalAttributeInfoEx;
import de.lmu.ifi.dbs.elki.data.Cluster;
import de.lmu.ifi.dbs.elki.data.model.KMeansModel;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import weka.core.Attribute;
import weka.core.AttributeStats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;


public class DataSetGenerator {
	
	private ArrayList<Rule> rules;
	private double totalInstances;
	private int numInstances;
	private ArrayList<Attribute> metadatalist;
	public ArrayList<Attribute> getMetadatalist() {
		return metadatalist;
	}

	public void setMetadatalist(ArrayList<Attribute> metadatalist) {
		this.metadatalist = metadatalist;
	}

	private ArrayList<AttributeStats> stats;
	private ClassifierEx classifier;
	
	public DataSetGenerator(ClassifierEx classifier, int numInstances) {
		this.rules = classifier.getRules();
		for (Rule rule : rules) {
			this.totalInstances += rule.total;
		}
		
		this.stats = classifier.getStats();
		this.numInstances = numInstances;
		metadatalist = new ArrayList<Attribute>();

		for(Attribute att : Collections.list(classifier.getTrainingData().enumerateAttributes())) {
			metadatalist.add(att);
		}
		metadatalist.add(classifier.getTrainingData().classAttribute());
		
		this.classifier = classifier;
		//this.classifier
		
	}
	
	public double[][] Generate() {
				
		ArrayList<double[]> generatedData = new ArrayList<double[]>();
		double[][] rulesMaxAndMin = classifier.getRulesMaxAndMin(); //new double[rules.size()][4];
		double[] rulesLeafsLabels = classifier.getRulesLeafsLabels(); //new char[rules.size()][];
		
		for (Rule rule : rules) {
			//if the rule has low weight it does not participate in the data generation
			rule.generatedData = new double[1][];
			rule.generatedData[0] = new double[1];
			
			// Uncomment for avoiding weak rules to generate data and introduce error
			//if(rule.weight < 0.3)continue;
			ArrayList<double[]> generatedDataPerRule = new ArrayList<double[]>();
			double ruleInfluence = Math.round(rule.getRuleInfluence(totalInstances)*numInstances);
			
			//hack for the cases where round rounds ruleinfluence to zero 0
			if(ruleInfluence==0)ruleInfluence=1;
			
			ArrayList<DataAttribute> attributes = rule.discoverAttributesDataBoundaries(metadatalist, stats);
			
			//rulesMaxAndMin[rules.indexOf(rule)] = new double[]{attributes.get(0).getMin(),attributes.get(0).getMax(),attributes.get(1).getMin(),attributes.get(1).getMax()};
			//String label = rule.data + "(" + rule.total + "/" + rule.incorrect +")";
			//rulesLeafsLabels[rules.indexOf(rule)] = label.toCharArray();
			
			for (int i = 0; i < ruleInfluence; i++) {				
				//double[] instance = new double[attributes.size()+1];	
				double[] instance = new double[attributes.size()];		
				for(DataAttribute attribute : attributes) {
					instance[attributes.indexOf(attribute)] = attribute.getRandomValue();					
				}
				generatedDataPerRule.add(instance);
				instance[attributes.size()-1] = rule.data;								
				generatedData.add(instance);				
			}
			
			if(generatedDataPerRule.size()>0) {
				double[][] datar  = new double[generatedDataPerRule.size()][generatedDataPerRule.get(0).length];
				for (int i = 0; i < datar.length; i++) {			
					if(generatedDataPerRule.size()>i) {
						datar[i] = generatedDataPerRule.get(i);
					}
				
				}
				
				rule.generatedData = datar;
				
			}
			else {
				
				System.out.println("generateddataperrule empty");
				
			}
		}
		
		double[][] data;
		if(!generatedData.isEmpty())
		{
			data  = new double[numInstances][generatedData.get(0).length];
			for (int i = 0; i < data.length; i++) {			
				if(generatedData.size()>i) {
					data[i] = generatedData.get(i);
				}
			}
		}else {
			data = null;
		}
		return data;
	}


	
	public ArrayList<String[]> GenerateNominal(boolean usesKmeans) {
		
		ArrayList<String[]> generatedData = new ArrayList<String[]>();
		ArrayList<String[]> generatedClusteredData = new ArrayList<String[]>();
		//double[][] rulesMaxAndMin = new double[rules.size()][4];
		//char[][] rulesLeafsLabels = new char[rules.size()][];
		
		StringBuffer buff = new StringBuffer();
		buff.append("filtered rules \n");
		int countRulesGeneration = 0;
		for (Rule rule : rules) {
			rule.centroidsList = new ArrayList<double[]>();
			double ruleInfluence = Math.round(rule.getRuleInfluence(totalInstances)*numInstances);
			ArrayList<DataAttribute> attributes = rule.discoverAttributesDataBoundaries(metadatalist, stats);
			buff.append("********** Rule #" + String.valueOf(rules.indexOf(rule)+1) + " Class value: "+ rule.classvalue + " total influence "+ ruleInfluence + "*********\n");
			
			//rulesMaxAndMin[rules.indexOf(rule)] = new double[]{attributes.get(0).getMin(),attributes.get(0).getMax(),attributes.get(1).getMin(),attributes.get(1).getMax()};
			//String label = rule.data + "(" + rule.total + "/" + rule.incorrect +")";
			//rulesLeafsLabels[rules.indexOf(rule)] = label.toCharArray();
			buff.append("filtered atributes conditions *****\n\n");
			for(DataAttribute attribute : attributes) {
				if(attribute.isNominal) {
					//String value = attribute.fixedValue==null? String.valueOf(ThreadLocalRandom.current().nextInt(1, this.stats.get(attributes.indexOf(attribute)).distinctCount + 1)) :attribute.getFixedValue();
					String value = attribute.fixedValue==null? "?":attribute.getFixedValue();
					buff.append(attribute.name() + " value ["+ value+"]\n");
				}
				else {
					if(attribute.name().equals("day")) {
						buff.append(attribute.name()+ "[1"+" "+ String.valueOf(this.stats.get(attributes.indexOf(attribute)).distinctCount)+"]\n");
					}else {
						buff.append(attribute.name()+ "[" + attribute.getMin() + " "+ attribute.getMax()+ "]\n");
					}
					
				}
			}
			for (int i = 0; i <= ruleInfluence; i++) {
				//String[] instance = new String[attributes.size()+1];	
				String[] instance = new String[attributes.size()];	
				for(DataAttribute attribute : attributes) {
					//check if attribute is numeric
					
					for(Attribute att : metadatalist) {
						if(att.name().equals(attribute.name()) && (!att.name().equals("class"))) {
							if(att.isNumeric()) {
								instance[attributes.indexOf(attribute)] = String.valueOf(attribute.getRandomValue());
							}
							else {
								if(attribute.getFixedValue()!=null) {
									instance[attributes.indexOf(attribute)] = attribute.getFixedValue();
								}
								else {
									int randomIndex = ThreadLocalRandom.current().nextInt(0, ((NominalAttributeInfoEx)att.getNominalAttributeInfo()).getValues().size());
									instance[attributes.indexOf(attribute)] = String.valueOf(((NominalAttributeInfoEx)att.getNominalAttributeInfo()).getValues().get(randomIndex));
								}
								
							}
						}
					}

				}
				if(rule.isCategorical()) {
					instance[attributes.size() - 1] = rule.classvalue; 
				}				
				else {
					instance[attributes.size() - 1] = String.valueOf(rule.data);
				}

				//test if generated instances is close to the cluster center
				if(usesKmeans){
					int ikmeans = 0;
					boolean isInstanceCloseToAnyCentroid = false;
					if(rule.kmeansclusters==null)continue;
					for(Cluster<KMeansModel> clu : rule.kmeansclusters.getAllClusters()) {


						double[] centroid = clu.getModel().getMean();

						double[] centroidFeaturesOnly = {centroid[0], centroid[1]};


						double[] instanceData = Arrays.stream(instance)
								.mapToDouble(Double::parseDouble)
								.toArray();
						double[] instanceDataFeaturesOnly = {instanceData[0], instanceData[1]};


						EuclideanDistance d = new EuclideanDistance();
						double distance = d.compute(centroidFeaturesOnly,instanceDataFeaturesOnly);
						if(distance <= 5)isInstanceCloseToAnyCentroid = true;

					}
					if(isInstanceCloseToAnyCentroid)
						generatedData.add(instance);
				}
				else {
					generatedData.add(instance);
				}
			}
			for(Cluster<KMeansModel> clu : rule.kmeansclusters.getAllClusters()) {
				double[] centroid = clu.getModel().getMean();
				rule.centroidsList.add(centroid);
			}
			
			buff.append("********* end rule ***********  \n\n\n\n");
		}
		
		ArrayList<String[]> data  = new ArrayList<String[]>();
		for(String[] instance : generatedData) {
			data.add(instance);
		}
	
		return data;
	}
}
