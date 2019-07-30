package dafdt.wekaex;

import weka.classifiers.trees.ht.HNode;
import weka.classifiers.trees.ht.Split;
import weka.classifiers.trees.ht.SplitNode;
import weka.classifiers.trees.ht.WeightMass;

import java.util.LinkedHashMap;
import java.util.Map;

public class SplitNodeEx extends SplitNode {
    public SplitNodeEx(Map<String, WeightMass> classDistrib, Split split) {
        super(classDistrib, split);
    }

    public SplitNode splitNode;

    public SplitEx getSplit(){
        return (SplitEx) this.m_split;
    }
}
