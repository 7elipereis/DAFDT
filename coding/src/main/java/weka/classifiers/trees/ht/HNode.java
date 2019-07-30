//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package weka.classifiers.trees.ht;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import dafdt.models.NodeSide;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Utils;

public abstract class HNode implements Serializable {
    private static final long serialVersionUID = 197233928177240264L;
    public Map<String, WeightMass> m_classDistribution = new LinkedHashMap();
    protected int m_leafNum;
    protected int m_nodeNum;
    public HNode m_parent;
    public NodeSide nodeSide;
    public String categoryValue;

    public HNode() {
    }

    public HNode(Map<String, WeightMass> classDistrib) {
        this.m_classDistribution = classDistrib;
    }

    public boolean isLeaf() {
        return true;
    }

    public int numEntriesInClassDistribution() {
        return this.m_classDistribution.size();
    }

    public boolean classDistributionIsPure() {
        int count = 0;
        Iterator var2 = this.m_classDistribution.entrySet().iterator();

        while(var2.hasNext()) {
            Entry<String, WeightMass> el = (Entry)var2.next();
            if (((WeightMass)el.getValue()).m_weight > 0.0D) {
                ++count;
                if (count > 1) {
                    break;
                }
            }
        }

        return count < 2;
    }

    public void updateDistribution(Instance inst) {
        if (!inst.classIsMissing()) {
            String classVal = inst.stringValue(inst.classAttribute());
            WeightMass m = (WeightMass)this.m_classDistribution.get(classVal);
            if (m == null) {
                m = new WeightMass();
                m.m_weight = 1.0D;
                this.m_classDistribution.put(classVal, m);
            }

            m.m_weight += inst.weight();
        }
    }

    public double[] getDistribution(Instance inst, Attribute classAtt) throws Exception {
        double[] dist = new double[classAtt.numValues()];

        for(int i = 0; i < classAtt.numValues(); ++i) {
            WeightMass w = (WeightMass)this.m_classDistribution.get(classAtt.value(i));
            if (w != null) {
                dist[i] = w.m_weight;
            } else {
                dist[i] = 1.0D;
            }
        }

        Utils.normalize(dist);
        return dist;
    }

    public int installNodeNums(int nodeNum) {
        ++nodeNum;
        this.m_nodeNum = nodeNum;
        return nodeNum;
    }

    protected int dumpTree(int depth, int leafCount, StringBuffer buff) {
        double max = -1.0D;
        String classVal = "";
        Iterator var7 = this.m_classDistribution.entrySet().iterator();

        while(var7.hasNext()) {
            Entry<String, WeightMass> e = (Entry)var7.next();
            if (((WeightMass)e.getValue()).m_weight > max) {
                max = ((WeightMass)e.getValue()).m_weight;
                classVal = (String)e.getKey();
            }
        }

        buff.append(classVal + " (" + String.format("%-9.3f", max).trim() + ")");
        ++leafCount;
        this.m_leafNum = leafCount;
        return leafCount;
    }

    protected void printLeafModels(StringBuffer buff) {
    }

    public void graphTree(StringBuffer text) {
        double max = -1.0D;
        String classVal = "";
        Iterator var5 = this.m_classDistribution.entrySet().iterator();

        while(var5.hasNext()) {
            Entry<String, WeightMass> e = (Entry)var5.next();
            if (((WeightMass)e.getValue()).m_weight > max) {
                max = ((WeightMass)e.getValue()).m_weight;
                classVal = (String)e.getKey();
            }
        }

        text.append("N" + this.m_nodeNum + " [label=\"" + classVal + " (" + String.format("%-9.3f", max).trim() + ")\" shape=box style=filled]\n");
    }

    public String toString(boolean printLeaf) {
        this.installNodeNums(0);
        StringBuffer buff = new StringBuffer();
        this.dumpTree(0, 0, buff);
        if (printLeaf) {
            buff.append("\n\n");
            this.printLeafModels(buff);
        }

        return buff.toString();
    }

    public double totalWeight() {
        double tw = 0.0D;

        Entry e;
        for(Iterator var3 = this.m_classDistribution.entrySet().iterator(); var3.hasNext(); tw += ((WeightMass)e.getValue()).m_weight) {
            e = (Entry)var3.next();
        }

        return tw;
    }

    public LeafNode leafForInstance(Instance inst, SplitNode parent, String parentBranch) {
        return new LeafNode(this, parent, parentBranch);
    }

    public abstract void updateNode(Instance var1) throws Exception;
}
