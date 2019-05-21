package dafdt.wekaex;

import weka.classifiers.trees.j48.Distribution;

public class DistributionEx extends Distribution {
    public DistributionEx(int numBags, int numClasses) {
        super(numBags, numClasses);
    }

    public double[] getPerBag(){
        return m_perBag;
    }
}
