package dafdt.examples;

//import DataMergingMatlabUtils.Plotter;
//import com.mathworks.toolbox.javabuilder.MWException;
import dafdt.models.OverlappedAreas;
import dafdt.utils.DataLoader;

import weka.classifiers.meta.AdaBoostM1Ex;
import weka.core.Instances;

public class PlotOverlappedAreas {

    public static void main(String[] args){

        System.out.println("Testing DAFDT Plot Overlapped Areas");
        AdaBoostM1Ex adaBoostM1Ex = new AdaBoostM1Ex();
        Instances dataset = DataLoader.readDataSet("src/main/resources/data/D1.arff");

        try {
            adaBoostM1Ex.buildClassifier(dataset);
            //Plotter matlabPlotter = new Plotter();
            OverlappedAreas overlappedAreas = adaBoostM1Ex.findOverlapped();
            //matlabPlotter.plotOverlapped(overlappedAreas.data, overlappedAreas.maxmins, overlappedAreas.weights, overlappedAreas.labels, "FileOverlappedColorGradient", "Overlapped Areas Plot");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
