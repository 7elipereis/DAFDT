package dafdt.wekaex;

import weka.classifiers.trees.j48.C45Split;

public class C45SplitEx extends C45Split {
    public C45SplitEx(int attIndex, int minNoObj, double sumOfWeights, boolean useMDLcorrection) {
        super(attIndex, minNoObj, sumOfWeights, useMDLcorrection);
    }

    public double getSplitPoint() {
        return this.m_splitPoint;
    }
}
