package weka.classifiers.trees;

public class J48Exposer {

    public static int GetNumNodes(J48 j48){
        return j48.m_root.numNodes();
    }
}
