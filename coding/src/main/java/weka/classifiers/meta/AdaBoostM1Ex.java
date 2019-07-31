package weka.classifiers.meta;

import dafdt.models.DataAttribute;
import dafdt.models.OverlappedAreas;
import dafdt.models.Rule;
import dafdt.utils.Box;
import dafdt.wekaex.ClassifierEx;
import dafdt.wekaex.DecisionStumpEx;
import dafdt.wekaex.J48Ex;
import weka.classifiers.Classifier;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.J48Exposer;
import weka.core.Attribute;
import weka.core.Utils;

import java.util.ArrayList;
import java.util.Collections;

public class AdaBoostM1Ex extends AdaBoostM1 {

    //@Override
    public double[] getRulesLeafsLabels() {
        // TODO Auto-generated method stub

        ArrayList<Classifier> classifiers = new ArrayList<Classifier>();
        ArrayList<Rule> rules = new ArrayList<Rule>();
        ArrayList<Attribute> metadatalist = new ArrayList<Attribute>();
        for(Attribute att:Collections.list( ((ClassifierEx)m_Classifiers[0]).getTrainingData().enumerateAttributes())) {metadatalist.add(att);}

        ArrayList<String> labels = new ArrayList<String>();

        for (Classifier classifier : getFilteredClassifiers()) {
            classifiers.add(classifier);

            for (Rule r : ((ClassifierEx)classifier).getRules()) {

                //double w = (Double)(r.total/(((J48)classifier).TotalInstances()));
                //r.weight = Utils.roundDouble(w, 2);
                //String label =  (new DecimalFormat("0.00")).format(w);
                //labels.add(label);
                //labels.add(((Double)(((J48)classifier).TotalInstances()/r.total)).toString());
                //ArrayList<DataAttribute> attributes = r.discoverAttributesDataBoundaries(metadatalist, ((J48)classifier).stats);
                rules.add(r);

            }
        }
        double[] rulesLeafsLabels = new double[rules.size()];

        for (Rule r : rules) {
            rulesLeafsLabels[rules.indexOf(r)] = r.weight;

        }
        return rulesLeafsLabels;
    }

    public OverlappedAreas findOverlapped(){

        //getFilteredClassifiers();
        ArrayList<double[]> overlaps = new ArrayList<double[]>();
        ArrayList<Box> boxes = new ArrayList<Box>();
        double[][] maxmins = getRulesMaxAndMin();
        double[] leafs = getRulesLeafsLabels();
        ArrayList<Rule> rules = getFilteredRules();

        double[][][] data = new double[rules.size()][][];
        double[][] lims = new double[rules.size()][] ;
        double[] weights = new double[rules.size()];
        double[] labels = new double[rules.size()];

        //info to calculate global leafs weights
        double totalInstancesGenerated = 0;
        double[] globalWeights = new double[rules.size()];

        for (Rule r : rules) {
            data[rules.indexOf(r)] = r.generatedData;
            if(r.generatedData !=null)
            {
                totalInstancesGenerated += r.generatedData.length;
            }
            lims[rules.indexOf(r)] = maxmins[rules.indexOf(r)];
            weights[rules.indexOf(r)] = r.weight;
            labels[rules.indexOf(r)] = r.data;

        }

        //calculate global leafs weights with totalinstancesgenerated
        for(Rule r:rules) {
            double w = (((r.generatedData.length * 100)/totalInstancesGenerated)/100);
            globalWeights[rules.indexOf(r)] = Utils.roundDouble(w, 2);

            if(w > 1)
            {
                System.out.println("w > 1");
            }
        }

        for(int i = 0; i < maxmins.length; i++) {
            boxes.add(new Box(maxmins[i], leafs[i], rules.get(i)));
        }

        for (Box bi : boxes) {
            for(Box bj : boxes) {
                if(bi.overlaps(bj))bi.overlaps.add(bj);
                if(bi.overlaps(bj) && (bi.rule.data != bj.rule.data))bi.disagrementoverlaps.add(bj);
            }
        }

        OverlappedAreas ola = new OverlappedAreas();
        ola.data = data;
        ola.maxmins = maxmins;
        ola.weights = globalWeights;
        ola.labels = labels;
        return ola;
        //matlab.plotOverlapped(data, maxmins, globalWeights, labels, filepath + "OverlappedColorGradient", title);
        //matlab.plotOverlappedGradientBoxes(data, maxmins, globalWeights, labels, filepath + "OverlappedGradientBoxes", title);
        //matlab.plotOverlappedGradientBoxesGrayAreas(data, maxmins, globalWeights, labels, filepath + "OverlappedGradientBoxesGrayAreas", title);



        //for each box bi check if there are boxes bj which are overlapping it,
        //if bj overlaps bi with class disagreement then increment the bi overlap counter.
        //The overlap counter will be the gradient factor for the plotting: as higher the counter higher the alpha


    }
    public ArrayList<Rule> getFilteredRules(){
        ArrayList<Rule> filteredrules = new ArrayList<Rule>();

        for (Classifier classifier : getFilteredClassifiers()) {
            for (Rule r : ((ClassifierEx)classifier).getRules()) {
                filteredrules.add(r);
            }
        }
        return filteredrules;
    }
    //@Override
    public double[][] getRulesMaxAndMin() {
        // TODO Auto-generated method stub

        ArrayList<Classifier> classifiers = new ArrayList<Classifier>();
        ArrayList<Rule> rules = new ArrayList<Rule>();
        ArrayList<Attribute> metadatalist = new ArrayList<Attribute>();
        for(Attribute att:Collections.list( ((ClassifierEx)m_Classifiers[0]).getTrainingData().enumerateAttributes())) {metadatalist.add(att);}

        ArrayList<double[]> maxnmins = new ArrayList<double[]>();

        for (Classifier classifier : getFilteredClassifiers()) {
            classifiers.add(classifier);
            for (Rule r : ((ClassifierEx)classifier).getRules()) {

                ArrayList<DataAttribute> attributes = r.discoverAttributesDataBoundaries(metadatalist, ((ClassifierEx)classifier).getStats());
                rules.add(r);
                maxnmins.add(new double[] {attributes.get(0).getMin(), attributes.get(0).getMax(),attributes.get(1).getMin(),attributes.get(1).getMax()});

            }

        }
        double[][] rulesMaxAndMin = new double[rules.size()][4];

        for (double[] mm : maxnmins) {
            rulesMaxAndMin[maxnmins.indexOf(mm)] = mm;
        }

        return rulesMaxAndMin;
    }

    public ArrayList<ClassifierEx> getFilteredClassifiers(){

        ArrayList<ClassifierEx> result = new ArrayList<ClassifierEx>();

        if (m_NumIterationsPerformed == 1) {
            ClassifierEx classifier = ((ClassifierEx)m_Classifiers[0]);
            classifier.setWeight(1);
            result.add(classifier);
        }
        else {
            for (Classifier classifier : m_Classifiers) {
                if(classifier.getClass().getName().equals(J48.class.getName())) {
                    J48Ex c = (J48Ex)classifier;
                    if(J48Exposer.GetNumNodes(c)>1) {
                        if(c.getWeight()!=0) {
                            result.add(c);
                        }
                    }
                }
                if(classifier.getClass().getName().equals(DecisionStump.class.getName())) {
                    if(((DecisionStumpEx)classifier).getWeight()!=0) {
                        result.add((ClassifierEx) classifier);
                    }
                }
            }
        }
        return result;
    }
}
