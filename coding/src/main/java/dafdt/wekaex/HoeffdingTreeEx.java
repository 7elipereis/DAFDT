package dafdt.wekaex;

import dafdt.utils.DataSetGenerator;
import dafdt.utils.DataSetWriter;
import dafdt.utils.Rule;
import weka.classifiers.trees.HoeffdingTree;
import weka.core.Attribute;
import weka.core.AttributeStats;
import weka.core.Instances;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class HoeffdingTreeEx extends HoeffdingTree implements ClassifierEx {

    @Override
    public void setWeight(double weight) {

    }

    @Override
    public double getWeight() {
        return 0;
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

    public Instances generateInstances(int size) {
        Instances result = null;
        DataSetGenerator dsg = new DataSetGenerator(this, size);
        ArrayList<String[]> generated_data = dsg.GenerateNominal();

        DataSetWriter dsw = new DataSetWriter();

        String[] attnames = new String[this.getTrainingData().numAttributes()-1];
        for(Attribute att: Collections.list(this.getTrainingData().enumerateAttributes())) {
            if(att.name()!= null)
                attnames[att.index()]=att.name();
        }
        String arfffile = dsw.arffWriter("", generated_data, dsg.getMetadatalist());

        try {
            result = new Instances(new FileReader(arfffile));
            result.randomize(new Random(100));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
}
