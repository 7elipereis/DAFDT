package dafdt.wekaex;

import dafdt.models.NodeSide;
import weka.classifiers.trees.ht.HNode;
import weka.classifiers.trees.ht.NBNodeAdaptive;
import weka.core.Instances;

public class NBNodeAdaptiveEx extends NBNodeAdaptive {
    public NBNodeAdaptiveEx(Instances header, double nbWeightThreshold) throws Exception {
        super(header, nbWeightThreshold);
    }

    public HNode m_parent;
    public NodeSide nodeSide;
    public String categoryValue;
}
