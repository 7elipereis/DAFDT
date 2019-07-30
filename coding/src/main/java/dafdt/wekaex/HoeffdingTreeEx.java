package dafdt.wekaex;

import dafdt.models.Condition;
import dafdt.models.DataAttribute;
import dafdt.models.NodeSide;
import dafdt.utils.DataSetGenerator;
import dafdt.utils.DataSetWriter;
import dafdt.models.Rule;
import weka.classifiers.trees.HoeffdingTree;
import weka.classifiers.trees.ht.*;
import weka.core.Attribute;
import weka.core.AttributeStats;
import weka.core.Instances;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

public class HoeffdingTreeEx extends HoeffdingTree implements ClassifierEx {

    public ArrayList<Rule> rules;
    private ArrayList<HNode> m_leafs = new ArrayList<HNode>();
    private ArrayList<HNode> m_nodes = new ArrayList<HNode>();
    public ArrayList<AttributeStats> stats;
    private double totalInstances = 0;
    private boolean isNominal;

    @Override
    public void setWeight(double weight) {

    }

    @Override
    public double getWeight() {
        return 0;
    }

    @Override
    public Instances getTrainingData() {
        return this.m_header;
    }

    @Override
    public ArrayList<Rule> getRules() {

        findLeafs(m_root);
        buildRulesFromLeafs();
        String tree = this.toString();
        StringBuffer buff = new StringBuffer();
        for(Rule r : rules) {
            buff.append(r.toString() + "\n");
        }
        return rules;
    }

    public void findLeafs(HNode node) {
        if(this.m_leafs==null)this.m_leafs = new ArrayList<HNode>();

        if(node.getClass().getName().equals(SplitNode.class.getName())) {
            if((((SplitNode)node).m_split.getClass().getName().equals(UnivariateNumericBinarySplit.class.getName()))) {
                for(Map.Entry<String, HNode> v: ((SplitNode)node).m_children.entrySet()) {
                    v.getValue().m_parent = node;
                    //v.getValue().m_parent.nodeSide = v.getKey().equals("left")?NodeSide.Left:NodeSide.Right;
                    v.getValue().nodeSide = v.getKey().equals("left")?NodeSide.Left:NodeSide.Right;
                    findLeafs(v.getValue());
                }
            }
            if((((SplitNode)node).m_split.getClass().getName().equals(UnivariateNominalMultiwaySplit.class.getName()))) {
                for(Map.Entry<String, HNode> v: ((SplitNode)node).m_children.entrySet()) {
                    v.getValue().m_parent = node;
                    v.getValue().categoryValue = v.getKey();
                    //v.getValue().m_parent.nodeSide = v.getKey().equals("left")?NodeSide.Left:NodeSide.Right;
                    v.getValue().nodeSide = v.getKey().equals("left")?NodeSide.Left:NodeSide.Right;
                    findLeafs(v.getValue());
                }
            }
        }else {
            if(node.getClass().getName().equals(NBNodeAdaptive.class.getName())) {
                m_leafs.add(node);
            }
        }

    }

    public void buildRulesFromLeafs() {
        if(rules==null)rules = new ArrayList<Rule>();
        for(HNode node : m_leafs) {
            HNode currentNode = node;
            Rule rule = new Rule();
            rule.isCategorical = true;
            while(currentNode.m_parent != null) {

                if(currentNode.getClass().getName().equals(NBNodeAdaptive.class.getName())) {
                    String classVal = "";
                    double max = -1;
                    for (Map.Entry<String, WeightMass> en : ((NBNodeAdaptive)currentNode).m_classDistribution.entrySet()) {
                        if (en.getValue().m_weight > max) {
                            max = en.getValue().m_weight;
                            classVal = en.getKey();
                        }
                    }
                    rule.classvalue = classVal;
                    rule.weight = max;
                    rule.total = Math.round(max);

                    if(currentNode.m_parent.getClass().getName().equals(SplitNode.class.getName())) {
                        if((((SplitNode)currentNode.m_parent).m_split.getClass().getName().equals(UnivariateNumericBinarySplit.class.getName()))) {
                            String attributename = ((SplitNode)currentNode.m_parent).m_split.m_splitAttNames.get(0);
                            double splitpoint = ((UnivariateNumericBinarySplit)((SplitNode)currentNode.m_parent).m_split).m_splitPoint;
                            DataAttribute attribute = new DataAttribute(attributename, -1, -1);
                            Condition condition = new Condition(attribute, false,currentNode.nodeSide , splitpoint);
                            rule.conditions.add(condition);
                            currentNode = currentNode.m_parent;
                        }else {
                            if((((SplitNode)currentNode.m_parent).m_split.getClass().getName().equals(UnivariateNominalMultiwaySplit.class.getName()))) {
                                DataAttribute attribute = new DataAttribute(((SplitNode)currentNode.m_parent).m_split.m_splitAttNames.get(0), String.valueOf(Math.round(Double.parseDouble(currentNode.categoryValue))));
                                Condition condition = new Condition(attribute, true);
                                rule.conditions.add(condition);
                                currentNode = currentNode.m_parent;
                            }
                        }

                    }
                }else {
                    if(currentNode.getClass().getName().equals(SplitNode.class.getName())) {
                        if((((SplitNode)currentNode.m_parent).m_split.getClass().getName().equals(UnivariateNumericBinarySplit.class.getName()))) {
                            String attributename = ((SplitNode)currentNode.m_parent).m_split.m_splitAttNames.get(0);
                            double splitpoint = ((UnivariateNumericBinarySplit)((SplitNode)currentNode.m_parent).m_split).m_splitPoint;
                            DataAttribute attribute = new DataAttribute(attributename, -1, -1);
                            Condition condition = new Condition(attribute, false,currentNode.nodeSide , splitpoint);
                            rule.conditions.add(condition);
                            currentNode = currentNode.m_parent;
                        }else {
                            if((((SplitNode)currentNode.m_parent).m_split.getClass().getName().equals(UnivariateNominalMultiwaySplit.class.getName()))) {
                                DataAttribute attribute = new DataAttribute(((SplitNode)currentNode.m_parent).m_split.m_splitAttNames.get(0), String.valueOf(Math.round(Double.parseDouble(currentNode.categoryValue))));
                                Condition condition = new Condition(attribute, true);
                                rule.conditions.add(condition);
                                currentNode = currentNode.m_parent;
                            }
                        }
                    }
                }

            }
            rules.add(rule);

        }
    }

    @Override
    public ArrayList<AttributeStats> getStats() {

        //stats = new ArrayList<AttributeStats>();
        //for(int i=0; i< this.m_header.numAttributes();i++) {
            //stats.add(this.m_header.attributeStats(i));
        //}
        return this.stats;
    }

    @Override
    public double TotalInstances() {
        return 0;
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

    public Instances generateInstances(int size) {
        Instances result = null;
        DataSetGenerator dsg = new DataSetGenerator(this, size);
        ArrayList<String[]> generated_data = dsg.GenerateNominal();

        DataSetWriter dsw = new DataSetWriter();

        String[] attnames = new String[this.getTrainingData().numAttributes()-1];
        for(Attribute att: Collections.list(this.getTrainingData().enumerateAttributes())) {
            if(att.name()!= null)
                attnames[att.index()]=att.name();
        }
        String arfffile = dsw.arffWriter("", generated_data, dsg.getMetadatalist());

        try {
            result = new Instances(new FileReader(arfffile));
            result.randomize(new Random(100));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void buildClassifier(Instances data) throws Exception {
        reset();

        isNominal = data.classAttribute().isNominal();
        m_header = new Instances(data, 0);
        if (m_selectedSplitMetric == GINI_SPLIT) {
            m_splitMetric = new GiniSplitMetric();
        } else {
            m_splitMetric = new InfoGainSplitMetric(m_minFracWeightForTwoBranchesGain);
        }

        data = new Instances(data);
        data.deleteWithMissingClass();
        for (int i = 0; i < data.numInstances(); i++) {
            updateClassifier(data.instance(i));
        }

        stats = new ArrayList<AttributeStats>();
        for(int i=0; i<data.numAttributes();i++) {
            stats.add(data.attributeStats(i));
        }

        // can classifier handle the data?
        getCapabilities().testWithFail(data);

    }
}
