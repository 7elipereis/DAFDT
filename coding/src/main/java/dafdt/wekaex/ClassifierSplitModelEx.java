package dafdt.wekaex;

import weka.classifiers.trees.j48.ClassifierSplitModel;
import weka.classifiers.trees.j48.Distribution;

public abstract class ClassifierSplitModelEx extends ClassifierSplitModel {

    public Distribution getDistribution(){
        return m_distribution;
    }
}
