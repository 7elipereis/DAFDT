package dafdt.wekaex;

import dafdt.models.DataAttribute;
import dafdt.models.Condition;
import dafdt.models.NodeSide;
import dafdt.models.Rule;
import org.apache.commons.lang3.math.NumberUtils;
import weka.classifiers.trees.j48.ClassifierTree;
import weka.classifiers.trees.j48.ModelSelection;
import weka.core.AttributeStats;

import java.util.ArrayList;

public class ClassifierTreeEx extends ClassifierTree {
    public ClassifierTreeEx(ModelSelection toSelectLocModel) {
        super(toSelectLocModel);
    }
    public int m_son_index = -1;
    public ClassifierTreeEx m_parent = null;
    public String m_label = "";
    public ClassifierTree[] getSons() {
        return m_sons;
    }


    public Rule getRule(ArrayList<AttributeStats> stats) {
        StringBuilder result = new StringBuilder();
        Rule rule = new Rule();

        if((this.isLeaf()))
        {
            ClassifierTreeEx current = this;
            //case the class is categorical?
            if(NumberUtils.isNumber(current.m_label)) {
                rule.data =  Double.parseDouble(current.m_label);
                rule.isCategorical = false;
            }
            else {
                rule.classvalue = current.m_label;
                rule.isCategorical = true;
            }
            rule.total = current.m_localModel.distribution().total();
            rule.incorrect = current.m_localModel.distribution().numIncorrect();


            while(current.m_parent!=null) {

                Condition condition = null;
                if(!m_train.attribute(current.m_parent.getAttributeName()).isNominal()) {
                    double min = stats.get(m_train.attribute(current.m_parent.getAttributeName()).index()).numericStats.min;
                    double max = stats.get(m_train.attribute(current.m_parent.getAttributeName()).index()).numericStats.max;

                    condition = new Condition(new DataAttribute(current.m_parent.getAttributeName(), min, max));

                    condition.threshold = current.m_parent.splitPoint();

                    if(current.m_parent.getSons()[0].equals(current)) {
                        condition.nodeSide = NodeSide.Left;
                    }
                    if(current.m_parent.getSons()[1].equals(current)) {
                        condition.nodeSide = NodeSide.Right;
                    }
                }
                else {

                    double perbag[] = ((DistributionEx)((ClassifierSplitModelEx)current.m_parent.m_localModel).getDistribution()).getPerBag();
                    int bestindex = -1;
                    double bestscore = -1;
                    for(int i = 0; i < perbag.length; i++) {
                        if(perbag[i]> bestscore) {
                            bestindex = i;
                            bestscore = perbag[i];
                        }
                    }

                    String v = current.m_parent.m_localModel.rightSide(bestindex, m_train);
                    v = v.substring(v.lastIndexOf(" ")+1, v.length());
                    condition = new Condition(new DataAttribute(current.m_parent.getAttributeName(), v), true);

                }




                rule.conditions.add(condition);
                current = current.m_parent;
            }
        }

        return rule;
    }

    public String getAttributeName() {
        String result = "";
        result = m_localModel.leftSide(m_train);
        return result;
    }
    public double splitPoint() {return ((C45SplitEx)m_localModel).getSplitPoint();}

}
