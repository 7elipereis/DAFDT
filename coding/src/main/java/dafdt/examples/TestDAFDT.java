package dafdt.examples;

import dafdt.utils.DataLoader;
import dafdt.utils.Experiment;
import dafdt.utils.GraphVizHelper;
import dafdt.utils.Matlab;
import dafdt.wekaex.HoeffdingTreeEx;
import weka.core.Instances;

public class TestDAFDT {

    public static void main(String args[]) throws Exception {

        System.out.println("Testing DAFDT with K-means");

        //create a hoeffdingtreeex container
        HoeffdingTreeEx tree = new HoeffdingTreeEx();

        //parametization for the tree
        StringBuilder options = new StringBuilder();
        options.append("-G 5 ");
        options.append("-H 1 ");
        tree.setOptions(options.toString().split(" "));

        //load the dataset that will be used to learn the hoeffdingtreeex
        Instances dataset = DataLoader.readDataSet("src/main/resources/data/D1Diagonal.arff");

        //train the tree with the dataset
        tree.buildClassifier(dataset);

        //print tree
        GraphVizHelper.print2PNG(tree.graph(),"tree learned from Diagonal dataset 2 classes, 70% split", "Experiment/treeVisualization");

        //print tree boundaries
        Matlab.plotDSWithRuleBoundaries(Experiment.dataSetToArray(dataset), tree.getRulesMaxAndMin(), tree.getRulesLeafsLabels(),"Experiment/TreesLeafsBoundaries","treeBoundaries");

        //save dataset temporally to train the clusters
        tree.originaldata = dataset;

        //find the leafs' boundaries and assign the original instances to them
        tree.fitOriginalToLeaf();

        tree.clearEmptyRules();

        //for each rule write a dataset file with it's original instances
        tree.writeRuleOriginalData();

        //run cluster individually for each rule data
        tree.clusterRules();

        //do the cluster-aided instances generation
        tree.generateInstances(1000, "Experiment/1000InstancesGeneratedWithKMeansAid", true);

        //just plot the centroids inside their rule boundaries
        tree.plotRulesCentroidsAndBoundaries();

        System.out.println("DAFDT finished!");
    }
}
