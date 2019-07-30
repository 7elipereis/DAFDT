//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package weka.classifiers.trees.ht;

import java.io.Serializable;
import weka.core.Attribute;
import weka.core.Instance;

public class UnivariateNumericBinarySplit extends Split implements Serializable {
    private static final long serialVersionUID = -7392204582942741097L;
    public double m_splitPoint;

    public UnivariateNumericBinarySplit(String attName, double splitPoint) {
        this.m_splitAttNames.add(attName);
        this.m_splitPoint = splitPoint;
    }

    public String branchForInstance(Instance inst) {
        Attribute att = inst.dataset().attribute((String)this.m_splitAttNames.get(0));
        if (att != null && !inst.isMissing(att)) {
            return inst.value(att) <= this.m_splitPoint ? "left" : "right";
        } else {
            return null;
        }
    }

    public String conditionForBranch(String branch) {
        String result = (String)this.m_splitAttNames.get(0);
        if (branch.equalsIgnoreCase("left")) {
            result = result + " <= ";
        } else {
            result = result + " > ";
        }

        result = result + String.format("%-9.3f", this.m_splitPoint);
        return result;
    }
}
