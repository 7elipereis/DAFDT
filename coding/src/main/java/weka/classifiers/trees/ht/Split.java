//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package weka.classifiers.trees.ht;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import weka.core.Instance;

public abstract class Split implements Serializable {
    private static final long serialVersionUID = 5390368487675958092L;
    public List<String> m_splitAttNames = new ArrayList();

    public Split() {
    }

    public abstract String branchForInstance(Instance var1);

    public abstract String conditionForBranch(String var1);

    public List<String> splitAttributes() {
        return this.m_splitAttNames;
    }
}
