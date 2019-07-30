package dafdt.wekaex;

import dafdt.models.NodeSide;
import weka.classifiers.trees.ht.HNode;
import weka.classifiers.trees.ht.Split;
import weka.classifiers.trees.ht.SplitNode;
import weka.classifiers.trees.ht.WeightMass;

import java.util.Map;

public class HNodeCo extends SplitNode {

    public HNode node;
    public HNodeCo m_parent;
    public NodeSide nodeSide;
    public String categoryValue;


    public HNodeCo(Map<String, WeightMass> classDistrib, Split split) {
        super(classDistrib, split);
    }
    public Split getSplit(){
        return this.m_split;
    }
}
