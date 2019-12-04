/*
 * MATLAB Compiler: 7.0.1 (R2019a)
 * Date: Tue Dec  3 17:44:37 2019
 * Arguments: 
 * "-B""macro_default""-W""java:DataMergingMatlabUtils,Plotter""-T""link:lib""-d""C:\\repos\\l3s\\DAFDT\\coding\\libs""class{Plotter:C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genRot2D.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genRot2DDrift.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\pca2.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\pca2originalgenerated.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotBoxApproachStreamSizeAll.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotDS.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotOverlapped.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotOverlappedGradientBoxes.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotOverlappedGradientBoxesGrayAreas.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotStrEvals.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotStrEvalsComp2.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotStrEvalsComp2Split.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotStrEvalsComp3.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotStrEvalsComp3Split.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotStrEvalsComp3SplitDTSize.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotStrEvalsComp3SplitTag.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotStrEvalsCompBar.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotWithRuleBoundaries.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\printPctCorrectStream.m}""class{DatasetRotator:C:\\repos\\l3s\\DAFDT\\coding\\matlab\\BaseLineBatchAbruptDrift.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\BaseLineBatchDrift.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genRot.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genRot2D.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genRot2DDrift.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\rot.m}""class{DatasetGenerator:C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genDS2.m}""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\filterDS.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genDS2.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genDS4.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\generateDS.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genrand.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genRot.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\normDS.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotBoxApproachStreamSize.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotDS.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\rot.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\rotateDS.m"
 */

package DataMergingMatlabUtils;

import com.mathworks.toolbox.javabuilder.*;
import com.mathworks.toolbox.javabuilder.internal.*;

/**
 * <i>INTERNAL USE ONLY</i>
 */
public class DataMergingMatlabUtilsMCRFactory
{
    /** Component's uuid */
    private static final String sComponentId = "DataMergingM_FCE69E3CDC4ABB7078816B2A5BC614E5";
    
    /** Component name */
    private static final String sComponentName = "DataMergingMatlabUtils";
    
   
    /** Pointer to default component options */
    private static final MWComponentOptions sDefaultComponentOptions = 
        new MWComponentOptions(
            MWCtfExtractLocation.EXTRACT_TO_CACHE, 
            new MWCtfClassLoaderSource(DataMergingMatlabUtilsMCRFactory.class)
        );
    
    
    private DataMergingMatlabUtilsMCRFactory()
    {
        // Never called.
    }
    
    public static MWMCR newInstance(MWComponentOptions componentOptions) throws MWException
    {
        if (null == componentOptions.getCtfSource()) {
            componentOptions = new MWComponentOptions(componentOptions);
            componentOptions.setCtfSource(sDefaultComponentOptions.getCtfSource());
        }
        return MWMCR.newInstance(
            componentOptions, 
            DataMergingMatlabUtilsMCRFactory.class, 
            sComponentName, 
            sComponentId,
            new int[]{9,6,0}
        );
    }
    
    public static MWMCR newInstance() throws MWException
    {
        return newInstance(sDefaultComponentOptions);
    }
}
