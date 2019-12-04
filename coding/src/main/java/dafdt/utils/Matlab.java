package dafdt.utils;

import DataMergingMatlabUtils.Plotter;
import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;

public class Matlab {

    static Plotter plotter;

    static {
        try {
            plotter = new Plotter();
        } catch (MWException e) {
            e.printStackTrace();
        }
    }

    public static void plotDSWithRuleBoundaries(double[][] data, double[][] boundaries, double[] labels, String filepath, String title) {

        try {

            MWNumericArray ds = new MWNumericArray(data, MWClassID.DOUBLE);
            MWNumericArray l = new MWNumericArray(labels, MWClassID.DOUBLE);
            MWNumericArray b = new MWNumericArray(boundaries, MWClassID.DOUBLE);

            plotter.plotWithRuleBoundaries(ds, b,l,filepath, title);
            ds.dispose();


        } catch (MWException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
