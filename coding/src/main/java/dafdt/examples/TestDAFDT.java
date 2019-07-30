package dafdt.examples;

import dafdt.utils.DataLoader;
import dafdt.wekaex.HoeffdingTreeEx;
import weka.classifiers.trees.HoeffdingTree;
import weka.core.Instances;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestDAFDT {

    public static void main(String args[]) throws Exception {

        System.out.println("Testing DAFDT");
        HoeffdingTreeEx tree = new HoeffdingTreeEx();
        Instances dataset = DataLoader.readDataSet("src/main/resources/data/D1.arff");
        tree.buildClassifier(dataset);
        tree.generateInstances(2000);



        System.out.println("DAFDT finished!");

    }
}
