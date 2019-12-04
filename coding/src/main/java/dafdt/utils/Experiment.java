package dafdt.utils;

import weka.core.Instance;
import weka.core.Instances;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Experiment {

    public static double[][] readDataSetToArray(String file) throws FileNotFoundException {
        Instances dataset = null;
        double[][] data = null;
        try {
            dataset = new Instances(new FileReader(file));
            data = new double[dataset.size()][dataset.numAttributes()];
            for (Instance instance : dataset) {
                data[dataset.indexOf(instance)] = instance.toDoubleArray();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return data;
    }


    public static double[][] dataSetToArray(Instances dataset){
        double[][] data = new double[dataset.size()][dataset.numAttributes()];
        for (Instance instance : dataset) {
            data[dataset.indexOf(instance)] = instance.toDoubleArray();
        }
        return data;
    }
}
