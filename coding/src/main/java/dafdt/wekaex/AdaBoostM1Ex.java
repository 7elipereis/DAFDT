package dafdt.wekaex;


import weka.classifiers.Classifier;
import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;
import weka.core.Utils;

import java.util.ArrayList;

public class AdaBoostM1Ex extends AdaBoostM1 {

    public ArrayList<ClassifierEx> getClassifiers(){
        ArrayList<ClassifierEx> classifiers = new ArrayList<ClassifierEx>();
        for (Classifier classifier : m_Classifiers) {
            classifiers.add((ClassifierEx) classifier);
            ((ClassifierEx)classifier).setWeight(Utils.roundDouble(m_Betas[classifiers.indexOf(classifier)], 2));
        }
        return classifiers;

    }

    public ArrayList<ClassifierEx> getFilteredClassifiers(){

        ArrayList<ClassifierEx> result = new ArrayList<ClassifierEx>();

        if (m_NumIterationsPerformed == 1) {
            ((ClassifierEx)m_Classifiers[0]).setWeight(1);
            result.add((ClassifierEx) m_Classifiers[0]);
        }
        else {
            for (Classifier classifier : m_Classifiers) {
                if(classifier.getClass().getName().equals(J48.class.getName())) {
                    J48Ex c = (J48Ex) classifier;
                    if(c.NodeCount()>1) {
                        if(((ClassifierEx)c).getWeight()!=0) {
                            result.add((ClassifierEx)c);
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
