package weka.classifiers.trees;

import dafdt.models.Condition;
import dafdt.models.DataAttribute;
import dafdt.models.NodeSide;
import dafdt.models.Rule;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.Utils;

import java.util.ArrayList;

public class DecisionStumpExposer {

    public static Instances getInstances(DecisionStump decisionStump){
        return decisionStump.m_Instances;
    }

    public static void getWeight(DecisionStump decisionStump){

    }

    public static ArrayList<Rule> getRules(final DecisionStump decisionStump){

        ArrayList<Rule> result = new ArrayList<Rule>();
        final Attribute att = decisionStump.m_Instances.attribute(decisionStump.m_AttIndex);

        //rule under splitpoint
        Rule ru = new Rule();
        ru.data = Double.parseDouble(decisionStump.m_Instances.classAttribute().value(Utils.maxIndex(decisionStump.m_Distribution[0])));
        try {
            //ru.classvalue = printClass(m_Distribution[0]);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ru.conditions = new ArrayList<Condition>() {{add(new Condition(new DataAttribute(att.name(), 0, decisionStump.m_SplitPoint), false, NodeSide.Left,decisionStump.m_SplitPoint));}};
        ru.weight = Utils.roundDouble(decisionStump.m_Distribution[2][0], 2);
        ru.total = ru.weight*100;


        result.add(ru);

        //rule above splitpoint
        Rule ra = new Rule();
        ra.data = Double.parseDouble(decisionStump.m_Instances.classAttribute().value(Utils.maxIndex(decisionStump.m_Distribution[1])));
        try {
            //ra.classvalue = printClass(m_Distribution[1]);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ra.conditions = new ArrayList<Condition>() {{add(new Condition(new DataAttribute(att.name(), decisionStump.m_SplitPoint,100), false, NodeSide.Right,decisionStump.m_SplitPoint));}};
        ra.weight = Utils.roundDouble(decisionStump.m_Distribution[2][1], 2);
        ra.total = ra.weight*100;

        result.add(ra);



        return result;

    }
}
