package weka.classifiers;

import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.DecisionStumpExposer;
import weka.core.Instances;

public class ClassifierExposer {
    public static Instances getDecisionStumpTrainingData(Classifier classifier){
        return DecisionStumpExposer.getInstances((DecisionStump) classifier);
    }
}
