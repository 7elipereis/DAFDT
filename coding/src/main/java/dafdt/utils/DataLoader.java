package dafdt.utils;

import weka.core.Instances;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataLoader {

    public static Instances readDataSet(String file){
        //load dataset
        Instances dataset = null;
        try{
            dataset = new Instances(new FileReader(file));
            dataset.setClassIndex(dataset.numAttributes()-1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataset;
    }
}
