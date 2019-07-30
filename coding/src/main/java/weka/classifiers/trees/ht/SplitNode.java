//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package weka.classifiers.trees.ht;

import weka.core.Instance;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;


public class SplitNode extends HNode {
    private static final long serialVersionUID = 1558033628618451073L;
    public Split m_split;
    public Map<String, HNode> m_children = new LinkedHashMap();

    public SplitNode(Map<String, WeightMass> classDistrib, Split split) {
        super(classDistrib);
        this.m_split = split;
    }

    public String branchForInstance(Instance inst) {
        return this.m_split.branchForInstance(inst);
    }

    public boolean isLeaf() {
        return false;
    }

    public int numChildred() {
        return this.m_children.size();
    }

    public void setChild(String branch, HNode child) {
        this.m_children.put(branch, child);
    }

    public LeafNode leafForInstance(Instance inst, SplitNode parent, String parentBranch) {
        String branch = this.branchForInstance(inst);
        if (branch != null) {
            HNode child = (HNode)this.m_children.get(branch);
            return child != null ? child.leafForInstance(inst, this, branch) : new LeafNode((HNode)null, this, branch);
        } else {
            return new LeafNode(this, parent, parentBranch);
        }
    }

    public void updateNode(Instance inst) {
    }

    protected int dumpTree(int depth, int leafCount, StringBuffer buff) {
        Iterator var4 = this.m_children.entrySet().iterator();

        while(true) {
            HNode child;
            String branch;
            do {
                if (!var4.hasNext()) {
                    return leafCount;
                }

                Entry<String, HNode> e = (Entry)var4.next();
                child = (HNode)e.getValue();
                branch = (String)e.getKey();
            } while(child == null);

            buff.append("\n");

            for(int i = 0; i < depth; ++i) {
                buff.append("|   ");
            }

            buff.append(this.m_split.conditionForBranch(branch).trim());
            buff.append(": ");
            leafCount = child.dumpTree(depth + 1, leafCount, buff);
        }
    }

    public int installNodeNums(int nodeNum) {
        nodeNum = super.installNodeNums(nodeNum);
        Iterator var2 = this.m_children.entrySet().iterator();

        while(var2.hasNext()) {
            Entry<String, HNode> e = (Entry)var2.next();
            HNode child = (HNode)e.getValue();
            if (child != null) {
                nodeNum = child.installNodeNums(nodeNum);
            }
        }

        return nodeNum;
    }

    public void graphTree(StringBuffer buff) {
        boolean first = true;
        Iterator var3 = this.m_children.entrySet().iterator();

        Entry e;
        HNode child;
        while(var3.hasNext()) {
            e = (Entry)var3.next();
            child = (HNode)e.getValue();
            String branch = (String)e.getKey();
            if (child != null) {
                String conditionForBranch = this.m_split.conditionForBranch(branch);
                if (first) {
                    String testAttName = null;
                    if (conditionForBranch.indexOf("<=") < 0) {
                        testAttName = conditionForBranch.substring(0, conditionForBranch.indexOf("=")).trim();
                    } else {
                        testAttName = conditionForBranch.substring(0, conditionForBranch.indexOf("<")).trim();
                    }

                    first = false;
                    buff.append("N" + this.m_nodeNum + " [label=\"" + testAttName + "\"]\n");
                }


                int startIndex = 0;
                if (conditionForBranch.indexOf("<=") > 0) {
                    startIndex = conditionForBranch.indexOf("<") - 1;
                } else if (conditionForBranch.indexOf("=") > 0) {
                    startIndex = conditionForBranch.indexOf("=") - 1;
                } else {
                    startIndex = conditionForBranch.indexOf(">") - 1;
                }

                conditionForBranch = conditionForBranch.substring(startIndex, conditionForBranch.length()).trim();
                buff.append("N" + this.m_nodeNum + "->N" + child.m_nodeNum + "[label=\"" + conditionForBranch + "\"]\n").append("\n");
            }
        }

        var3 = this.m_children.entrySet().iterator();

        while(var3.hasNext()) {
            e = (Entry)var3.next();
            child = (HNode)e.getValue();
            if (child != null) {
                child.graphTree(buff);
            }
        }

    }

    protected void printLeafModels(StringBuffer buff) {
        Iterator var2 = this.m_children.entrySet().iterator();

        while(var2.hasNext()) {
            Entry<String, HNode> e = (Entry)var2.next();
            HNode child = (HNode)e.getValue();
            if (child != null) {
                child.printLeafModels(buff);
            }
        }

    }
}
