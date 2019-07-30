package dafdt.wekaex;

import weka.classifiers.trees.ht.Split;

import java.util.List;

public abstract class SplitEx extends Split {

    public List<String> getSplitAttNames(){
        return this.m_splitAttNames;
    }
}
