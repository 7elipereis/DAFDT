package dafdt.wekaex;

import dafdt.models.Rule;
import weka.classifiers.Classifier;
import weka.core.AttributeStats;
import weka.core.Instances;

import java.util.ArrayList;

public interface ClassifierEx extends Classifier {

    public void setWeight(double weight);
    public double getWeight();
    public Instances getTrainingData();
    public ArrayList<Rule> getRules();
    public ArrayList<AttributeStats> getStats();
    public double TotalInstances();
    public double[][] getRulesMaxAndMin();
    public double[] getRulesLeafsLabels();
    public boolean isNominal();
}
