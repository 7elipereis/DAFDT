package dafdt.wekaex;

import dafdt.models.Rule;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.j48.ClassifierTree;
import weka.core.AttributeStats;
import weka.core.Instances;
import weka.core.Utils;

import java.util.ArrayList;

public class J48Ex extends J48 implements ClassifierEx {

    private double m_weight = 0;
    private ArrayList<Rule> rules;
    private ArrayList<ClassifierTreeEx> m_leafs = new ArrayList<ClassifierTreeEx>();
    private ArrayList<ClassifierTreeEx> m_nodes = new ArrayList<ClassifierTreeEx>();

    public ArrayList<AttributeStats> getStats() {
        return stats;
    }

    private ArrayList<AttributeStats> stats;
    private ClassifierTree m_parent = null;
    private int m_son_index = -1;

    public int NodeCount() {
        return m_root.numNodes();
    }

    @Override
    public void setWeight(double weight) {

    }

    public double getWeight() {
        // TODO Auto-generated method stub
        return m_weight;
    }
    public ArrayList<Rule> getRules(){

        if(rules !=null) return rules;

        buildNodes();

        ArrayList<Rule> result = new ArrayList<Rule>();

        for(ClassifierTreeEx c : this.findLeaves()) {
            result.add(c.getRule(stats));
        }

        double totalInstances = 0;
        for(Rule r : result) {totalInstances+=r.total;}

        for(Rule r : result) {
            double w = (Double)(r.total/totalInstances);
            r.weight = Utils.roundDouble(w, 2);
        }

        rules = result;
        return rules;

    }
    private void buildNodes(){
        if(m_nodes.isEmpty())evalSons((ClassifierTreeEx) m_root);

        if(m_leafs==null) {
            for (ClassifierTreeEx leave : m_leafs) {
                m_nodes.add(leave);
            }
        }

        if(m_nodes!=null) {
            for (ClassifierTreeEx classifierTree : m_nodes) {

                setParent(classifierTree);

                if(!classifierTree.isLeaf()) {
                    ((ClassifierTreeEx)classifierTree.getSons()[0]).m_son_index = 0;
                    ((ClassifierTreeEx)classifierTree.getSons()[1]).m_son_index = 1;
                }
            }
        }
    }

    private void evalSons(ClassifierTreeEx node) {
        m_nodes.add(node);

        if(!node.isLeaf()) {
            for (int i = 0; i < node.getSons().length; i++) {
                if(node.getSons()[i].isLeaf()) {
                    m_leafs.add((ClassifierTreeEx) node.getSons()[i]);
                }
                else {
                    evalSons((ClassifierTreeEx) node.getSons()[i]);
                }
            }
        }else {
            m_leafs.add(node);
        }
    }

    private void setParent(ClassifierTreeEx node) {
        if(!node.isLeaf()) {
            for (int i = 0; i < node.getSons().length; i++) {
                ((ClassifierTreeEx)node.getSons()[i]).m_parent = node;
                ((ClassifierTreeEx)node.getSons()[i]).m_son_index = i;
            }
        }
    }

    private ArrayList<ClassifierTreeEx> findLeaves() {
        if(m_leafs.isEmpty())evalSons((ClassifierTreeEx) m_root);
        return m_leafs;
    }

    public Instances getTrainingData() {
        return m_root.getTrainingData();
    }

    public double TotalInstances() {
        double total=0;
        for (Rule r : getRules()) {
            total+=r.total;
        }
        return total;
    }

    @Override
    public double[][] getRulesMaxAndMin() {
        return new double[0][];
    }

    @Override
    public double[] getRulesLeafsLabels() {
        return new double[0];
    }

    @Override
    public boolean isNominal() {
        return false;
    }
}
