package dafdt.utils;

import weka.core.Instance;
import weka.core.Instances;

public class DataFilter {

    public static Instances filterClassValue(int value){
        Instances dataset = DataLoader.readDataSet("src/main/resources/data/D1Diagonal.arff");
        dataset.setClassIndex(2);

        Instances result = new Instances(dataset, 0,0);

        for(Instance i : dataset){
            if(i.classValue()==0){
                result.add(i);
            }
        }
        return result;
    }
}
