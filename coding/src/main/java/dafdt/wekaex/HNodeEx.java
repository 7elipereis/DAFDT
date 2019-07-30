package dafdt.wekaex;

import dafdt.models.NodeSide;
import weka.classifiers.trees.ht.HNode;
import weka.classifiers.trees.ht.LearningNode;

import java.io.Serializable;

public abstract class HNodeEx extends HNode {
    public HNode m_parent;
    public NodeSide nodeSide;
    public String categoryValue;
}
