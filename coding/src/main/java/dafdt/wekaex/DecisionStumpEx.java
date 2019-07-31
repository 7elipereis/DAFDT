package dafdt.wekaex;

import dafdt.models.Rule;
import weka.classifiers.trees.DecisionStump;
import weka.core.AttributeStats;
import weka.core.Instances;

import java.util.ArrayList;

public class DecisionStumpEx extends DecisionStump implements ClassifierEx {

    private double weight;

    @Override
    public void setWeight(double weight) {

    }

    public double getWeight() {
        // TODO Auto-generated method stub
        return this.weight;
    }

    @Override
    public Instances getTrainingData() {
        return null;
    }

    @Override
    public ArrayList<Rule> getRules() {
        return null;
    }

    @Override
    public ArrayList<AttributeStats> getStats() {
        return null;
    }

    @Override
    public double TotalInstances() {
        return 0;
    }

    @Override
    public double[][] getRulesMaxAndMin() {
        return new double[0][];
    }

    @Override
    public double[] getRulesLeafsLabels() {
        return new double[0];
    }

    @Override
    public boolean isNominal() {
        return false;
    }
}
