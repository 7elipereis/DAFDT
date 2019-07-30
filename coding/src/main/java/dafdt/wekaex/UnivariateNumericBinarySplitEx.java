package dafdt.wekaex;

import weka.classifiers.trees.ht.UnivariateNumericBinarySplit;

public class UnivariateNumericBinarySplitEx extends UnivariateNumericBinarySplit {
    public UnivariateNumericBinarySplitEx(String attName, double splitPoint) {
        super(attName, splitPoint);
    }

    public double getSplitPoint(){
        return this.m_splitPoint;
    }
}
