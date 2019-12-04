package dafdt.wekaex;

import dafdt.models.Condition;
import dafdt.models.DataAttribute;
import dafdt.models.NodeSide;
import dafdt.models.Rule;
import dafdt.utils.DataSetGenerator;
import dafdt.utils.DataSetWriter;
import dafdt.utils.Experiment;
import dafdt.utils.Matlab;
import de.lmu.ifi.dbs.elki.algorithm.clustering.DBSCAN;
import de.lmu.ifi.dbs.elki.algorithm.clustering.kmeans.KMeansLloyd;
import de.lmu.ifi.dbs.elki.algorithm.clustering.kmeans.initialization.RandomUniformGeneratedInitialMeans;
import de.lmu.ifi.dbs.elki.data.Cluster;
import de.lmu.ifi.dbs.elki.data.Clustering;
import de.lmu.ifi.dbs.elki.data.NumberVector;
import de.lmu.ifi.dbs.elki.data.model.KMeansModel;
import de.lmu.ifi.dbs.elki.data.model.Model;
import de.lmu.ifi.dbs.elki.data.type.TypeUtil;
import de.lmu.ifi.dbs.elki.database.Database;
import de.lmu.ifi.dbs.elki.database.StaticArrayDatabase;
import de.lmu.ifi.dbs.elki.database.ids.DBIDIter;
import de.lmu.ifi.dbs.elki.database.ids.DBIDRange;
import de.lmu.ifi.dbs.elki.database.relation.Relation;
import de.lmu.ifi.dbs.elki.datasource.ArrayAdapterDatabaseConnection;
import de.lmu.ifi.dbs.elki.datasource.DatabaseConnection;
import de.lmu.ifi.dbs.elki.distance.distancefunction.minkowski.SquaredEuclideanDistanceFunction;
import de.lmu.ifi.dbs.elki.utilities.random.RandomFactory;
import weka.classifiers.trees.HoeffdingTree;
import weka.classifiers.trees.ht.*;
import weka.core.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HoeffdingTreeEx extends HoeffdingTree implements ClassifierEx {

    public ArrayList<Rule> rules;
    private ArrayList<HNode> m_leafs = new ArrayList<HNode>();
    private ArrayList<HNode> m_nodes = new ArrayList<HNode>();
    public ArrayList<AttributeStats> stats;
    private double totalInstances = 0;
    private boolean isNominal;
    public Instances originaldata;

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

        if(rules==null){
            findLeafs(m_root);
            buildRulesFromLeafs();
            String tree = this.toString();
            StringBuffer buff = new StringBuffer();
            for(Rule r : rules) {
                buff.append(r.toString() + "\n");
            }
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
        ArrayList<Rule> rules = getRules();

        ArrayList<Attribute> metadatalist = new ArrayList<Attribute>();
        for(Attribute att:Collections.list(this.getTrainingData().enumerateAttributes())) {metadatalist.add(att);}

        double[][] rulesMaxAndMin = new double[rules.size()][4];
        //char[][] rulesLeafsLabels = new char[rules.size()][];

        for (Rule rule : rules) {

            //double ruleInfluence = Math.round(rule.getRuleInfluence(totalInstances)*numInstances);
            ArrayList<DataAttribute> attributes = rule.discoverAttributesDataBoundaries(metadatalist, stats);

            rulesMaxAndMin[rules.indexOf(rule)] = new double[]{attributes.get(0).getMin(),attributes.get(0).getMax(),attributes.get(1).getMin(),attributes.get(1).getMax()};
            String label = rule.data + "(" + rule.total + "/" + rule.incorrect +")";
            //rulesLeafsLabels[rules.indexOf(rule)] = label.toCharArray();
        }


        return rulesMaxAndMin;
    }

    @Override
    public double[] getRulesLeafsLabels() {
        ArrayList<Rule> rules = getRules();

        ArrayList<Attribute> metadatalist = new ArrayList<Attribute>();
        for(Attribute att:Collections.list(this.getTrainingData().enumerateAttributes())) {metadatalist.add(att);}

        //double[][] rulesMaxAndMin = new double[rules.size()][4];
        //char[][] rulesLeafsLabels = new char[rules.size()][];



        double[] result = new double[rules.size()];

        for(Rule r : rules) {
            double w = (Double)(r.total/this.TotalInstances());
            r.weight = Utils.roundDouble(w, 2);
            result[rules.indexOf(r)] = r.weight;
        }


        return result;
    }

    @Override
    public boolean isNominal() {
        return false;
    }

    public void fitOriginalToLeaf(){

        ArrayList<Attribute> metadatalist = new ArrayList<Attribute>();
        for(Attribute att : Collections.list(this.getTrainingData().enumerateAttributes())) {
            metadatalist.add(att);
        }

        this.rules = getRules();

        for(Rule rule: rules){

            rule.originaldata = new Instances(originaldata, 0,0);

            ArrayList<DataAttribute> dataAttributes = rule.discoverAttributesDataBoundaries(metadatalist, stats);

            for(Instance instance: originaldata){

                boolean instanceDoesFitLeaf = true;

                for(Attribute attribute: metadatalist){
                    //find instance value of the attribute
                    double instanceValue = instance.value(attribute);

                    //find dataattribute of the attribute
                    DataAttribute dataAttribute = null;
                    for(DataAttribute da: dataAttributes){
                        if(da.name().equals(attribute.name())){
                            dataAttribute = da;
                        }
                    }

                    if(dataAttribute.getMin() > instanceValue){
                        instanceDoesFitLeaf = false;
                    }
                    if(dataAttribute.getMax() < instanceValue){
                        instanceDoesFitLeaf = false;
                    }

                }

                //test class value
                double instanceClassValue = instance.classValue();

                if(Double.parseDouble(rule.classvalue) == 1){
                    String c = rule.classvalue;
                }
                if(rule.data == 1){
                    String c = rule.classvalue;
                }
                if(rule.isCategorical()) {
                    if(instanceClassValue != Double.parseDouble(rule.classvalue)){
                        instanceDoesFitLeaf = false;
                    }
                    //instance[attributes.size() - 1] = rule.classvalue;
                }
                else {

                    //instance[attributes.size() - 1] = String.valueOf(rule.data);
                }
                if(instanceDoesFitLeaf)rule.originaldata.add(instance);
            }
        }

    }
    public void writeRuleOriginalData(){

        int countRule = 0;
        for(Rule rule : rules){
            if(rule.originaldata!=null){
                DataSetWriter writer = new DataSetWriter();
                String atts[] = {"A","B"};
                writer.arffWriter("Experiment/Rules/rule_" + countRule, writer.readDataSetToArray(rule.originaldata), atts);
                countRule++;
            }

        }
    }

    public void clearEmptyRules(){
        rules.removeIf(value -> value.originaldata.size() == 0);

    }

    public void clusterRules(){

        for(Rule rule: rules){

            if(rule.originaldata.size()<=0)continue;

            DatabaseConnection dbc = new ArrayAdapterDatabaseConnection(Experiment.dataSetToArray(rule.originaldata));
            Database db = new StaticArrayDatabase(dbc, null);
            db.initialize();
            SquaredEuclideanDistanceFunction dist = SquaredEuclideanDistanceFunction.STATIC;
            //RandomNormalGeneratedInitialMeans initnormal = new RandomNormalGeneratedInitialMeans(RandomFactory.DEFAULT);
            RandomUniformGeneratedInitialMeans inituniform = new RandomUniformGeneratedInitialMeans(RandomFactory.DEFAULT);


            DBSCAN<NumberVector> dbscan = new DBSCAN<>(dist, 100, 10);
            KMeansLloyd<NumberVector> km = new KMeansLloyd<>(dist, 3, 0, inituniform);

            Clustering<Model> cdbscan = dbscan.run(db);
            Clustering<KMeansModel> ckmeans = km.run(db);

            Relation<NumberVector> reldbscan = db.getRelation(TypeUtil.NUMBER_VECTOR_FIELD);
            Relation<NumberVector> relkmeans = db.getRelation(TypeUtil.NUMBER_VECTOR_FIELD);

            DBIDRange idsdbscan = (DBIDRange) reldbscan.getDBIDs();
            DBIDRange idskmeans = (DBIDRange) relkmeans.getDBIDs();

            //iteration dbscan
            int i = 0;
            for(Cluster<Model> clu : cdbscan.getAllClusters()) {

                //save cluster info to RUle
                rule.dbscanclusters = clu;
                // K-means will name all clusters "Cluster" in lack of noise support:
                System.out.println("#" + i + ": " + clu.getNameAutomatic());
                System.out.println("Size: " + clu.size());
                //System.out.println("Center: " + clu.getModel().getPrototype().toString());
                // Iterate over objects:
                System.out.print("Objects: ");
                for(DBIDIter it = clu.getIDs().iter(); it.valid(); it.advance()) {
                    // To get the vector use:
                    NumberVector v = reldbscan.get(it);
                    // Offset within our DBID range: "line number"
                    final int offset = idsdbscan.getOffset(it);
                    System.out.print(" " + offset);
                    // Do NOT rely on using "internalGetIndex()" directly!
                }
                System.out.println();
                ++i;
            }

            //iteration kmeans
            int ikmeans = 0;
            //save cluster info to RUle
            rule.kmeansclusters = ckmeans;
            for(Cluster<KMeansModel> clu : ckmeans.getAllClusters()) {

                // K-means will name all clusters "Cluster" in lack of noise support:
                System.out.println("#" + ikmeans + ": " + clu.getNameAutomatic());
                System.out.println("Size: " + clu.size());
                System.out.println("Center: " + clu.getModel().getPrototype().toString());
                // Iterate over objects:
                System.out.print("Objects: ");
                for(DBIDIter it = clu.getIDs().iter(); it.valid(); it.advance()) {
                    // To get the vector use:
                    NumberVector v = relkmeans.get(it);

                    // Offset within our DBID range: "line number"
                    final int offset = idskmeans.getOffset(it);
                    System.out.print(" " + offset);
                    // Do NOT rely on using "internalGetIndex()" directly!
                }
                System.out.println();
                ++ikmeans;
            }


//            KMeansLloyd<NumberVector> km = new KMeansLloyd<>(dist, 3, 0, init);
//            Clustering<KMeansModel> c = km.run(db);
//            // Relation containing the number vectors:
//            Relation<NumberVector> rel = db.getRelation(TypeUtil.NUMBER_VECTOR_FIELD);
//            // We know that the ids must be a continuous range:
//            DBIDRange ids = (DBIDRange) rel.getDBIDs();
//
//            int i = 0;
//            for(Cluster<KMeansModel> clu : c.getAllClusters()) {
//                // K-means will name all clusters "Cluster" in lack of noise support:
//                System.out.println("#" + i + ": " + clu.getNameAutomatic());
//                System.out.println("Size: " + clu.size());
//                System.out.println("Center: " + clu.getModel().getPrototype().toString());
//                // Iterate over objects:
//                System.out.print("Objects: ");
//                for(DBIDIter it = clu.getIDs().iter(); it.valid(); it.advance()) {
//                    // To get the vector use:
//                    NumberVector v = rel.get(it);
//
//                    // Offset within our DBID range: "line number"
//                    final int offset = ids.getOffset(it);
//                    System.out.print(" " + offset);
//                    // Do NOT rely on using "internalGetIndex()" directly!
//                }
//                System.out.println();
//                ++i;
//            }


//            //rule.clusterInfoDBSCAN = new DBSCAN();
//            try {
//
//                Instances dataClusterer = null;
//                weka.filters.unsupervised.attribute.Remove filter = new weka.filters.unsupervised.attribute.Remove();
//                filter.setAttributeIndices("" + (rule.originaldata.classIndex() + 1));
//                try {
//                    filter.setInputFormat(rule.originaldata);
//                    dataClusterer = Filter.useFilter(rule.originaldata, filter);
//                } catch (Exception e1) {
//                    e1.printStackTrace();
//                    return;
//                }
//
//                //rule.clusterInfoDBSCAN.buildClusterer(dataClusterer);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

        }

    }


    public Instances generateInstances(int size, String datageneratedfilename, boolean usesKmeans) {
        Instances result = null;
        DataSetGenerator dsg = new DataSetGenerator(this, size);
        ArrayList<String[]> generated_data = dsg.GenerateNominal(usesKmeans);

        DataSetWriter dsw = new DataSetWriter();

        String[] attnames = new String[this.getTrainingData().numAttributes()-1];
        for(Attribute att: Collections.list(this.getTrainingData().enumerateAttributes())) {
            if(att.name()!= null)
                attnames[att.index()]=att.name();
        }
        String arfffile = dsw.arffWriter(datageneratedfilename, generated_data, dsg.getMetadatalist());
        //plot
        try {
            Matlab.plotDSWithRuleBoundaries(Experiment.readDataSetToArray(arfffile), this.getRulesMaxAndMin(), this.getRulesLeafsLabels(),"Experiment/GeneratedDataTreesLeafsBoundaries","Generated Data Against Tree Boundaries");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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

    public void plotRulesCentroidsAndBoundaries() {

        ArrayList<double[]> centroids = new ArrayList<>();

        for(Rule rule : rules){

            centroids.addAll(rule.centroidsList);
        }
        double[][] dataset = new double[centroids.size()][];
        ArrayList<String[]> datasetToPrint =  new ArrayList<>();

        int index = 0;
        for(double[] centroid : centroids){
            dataset[index] = centroid;
            String[] instanceToText = new String[centroid.length];
            int indexj = 0;
            for(double v : centroid){
                instanceToText[indexj] = String.valueOf(v);
                indexj++;
            }
            datasetToPrint.add(instanceToText);
            index++;
        }
        DataSetWriter dsw = new DataSetWriter();
        ArrayList<Attribute> metadatalist = new ArrayList<Attribute>();

        for(Attribute att : Collections.list(this.getTrainingData().enumerateAttributes())) {
            metadatalist.add(att);
        }
        metadatalist.add(this.getTrainingData().classAttribute());
        String arfffile = dsw.arffWriter("Experiment/centroidsDataSet", datasetToPrint, metadatalist);

        Matlab.plotDSWithRuleBoundaries(dataset, this.getRulesMaxAndMin(), this.getRulesLeafsLabels(),"Experiment/centroidsWithBoundaries","Tree Boundaries with Clusters Centroids");
    }
}
