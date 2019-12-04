/*
 * MATLAB Compiler: 7.0.1 (R2019a)
 * Date: Tue Dec  3 17:44:37 2019
 * Arguments: 
 * "-B""macro_default""-W""java:DataMergingMatlabUtils,Plotter""-T""link:lib""-d""C:\\repos\\l3s\\DAFDT\\coding\\libs""class{Plotter:C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genRot2D.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genRot2DDrift.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\pca2.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\pca2originalgenerated.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotBoxApproachStreamSizeAll.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotDS.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotOverlapped.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotOverlappedGradientBoxes.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotOverlappedGradientBoxesGrayAreas.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotStrEvals.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotStrEvalsComp2.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotStrEvalsComp2Split.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotStrEvalsComp3.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotStrEvalsComp3Split.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotStrEvalsComp3SplitDTSize.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotStrEvalsComp3SplitTag.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotStrEvalsCompBar.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotWithRuleBoundaries.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\printPctCorrectStream.m}""class{DatasetRotator:C:\\repos\\l3s\\DAFDT\\coding\\matlab\\BaseLineBatchAbruptDrift.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\BaseLineBatchDrift.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genRot.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genRot2D.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genRot2DDrift.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\rot.m}""class{DatasetGenerator:C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genDS2.m}""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\filterDS.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genDS2.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genDS4.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\generateDS.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genrand.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genRot.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\normDS.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotBoxApproachStreamSize.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotDS.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\rot.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\rotateDS.m"
 */

package DataMergingMatlabUtils;

import com.mathworks.toolbox.javabuilder.*;
import com.mathworks.toolbox.javabuilder.internal.*;
import java.util.*;

/**
 * The <code>DatasetRotator</code> class provides a Java interface to MATLAB functions. 
 * The interface is compiled from the following files:
 * <pre>
 *  C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genRot2D.m
 *  C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genRot2DDrift.m
 *  C:\\repos\\l3s\\DAFDT\\coding\\matlab\\BaseLineBatchAbruptDrift.m
 *  C:\\repos\\l3s\\DAFDT\\coding\\matlab\\BaseLineBatchDrift.m
 *  C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genRot.m
 *  C:\\repos\\l3s\\DAFDT\\coding\\matlab\\rot.m
 * </pre>
 * The {@link #dispose} method <b>must</b> be called on a <code>DatasetRotator</code> 
 * instance when it is no longer needed to ensure that native resources allocated by this 
 * class are properly freed.
 * @version 0.0
 */
public class DatasetRotator extends MWComponentInstance<DatasetRotator>
{
    /**
     * Tracks all instances of this class to ensure their dispose method is
     * called on shutdown.
     */
    private static final Set<Disposable> sInstances = new HashSet<Disposable>();

    /**
     * Maintains information used in calling the <code>genRot2D</code> MATLAB function.
     */
    private static final MWFunctionSignature sGenRot2DSignature =
        new MWFunctionSignature(/* max outputs = */ 1,
                                /* has varargout = */ false,
                                /* function name = */ "genRot2D",
                                /* max inputs = */ 2,
                                /* has varargin = */ false);
    /**
     * Maintains information used in calling the <code>genRot2DDrift</code> MATLAB 
     *function.
     */
    private static final MWFunctionSignature sGenRot2DDriftSignature =
        new MWFunctionSignature(/* max outputs = */ 1,
                                /* has varargout = */ false,
                                /* function name = */ "genRot2DDrift",
                                /* max inputs = */ 3,
                                /* has varargin = */ false);
    /**
     * Maintains information used in calling the <code>BaseLineBatchAbruptDrift</code> 
     *MATLAB function.
     */
    private static final MWFunctionSignature sBaseLineBatchAbruptDriftSignature =
        new MWFunctionSignature(/* max outputs = */ 1,
                                /* has varargout = */ false,
                                /* function name = */ "BaseLineBatchAbruptDrift",
                                /* max inputs = */ 3,
                                /* has varargin = */ false);
    /**
     * Maintains information used in calling the <code>BaseLineBatchDrift</code> MATLAB 
     *function.
     */
    private static final MWFunctionSignature sBaseLineBatchDriftSignature =
        new MWFunctionSignature(/* max outputs = */ 1,
                                /* has varargout = */ false,
                                /* function name = */ "BaseLineBatchDrift",
                                /* max inputs = */ 5,
                                /* has varargin = */ false);
    /**
     * Maintains information used in calling the <code>genRot</code> MATLAB function.
     */
    private static final MWFunctionSignature sGenRotSignature =
        new MWFunctionSignature(/* max outputs = */ 1,
                                /* has varargout = */ false,
                                /* function name = */ "genRot",
                                /* max inputs = */ 2,
                                /* has varargin = */ false);
    /**
     * Maintains information used in calling the <code>rot</code> MATLAB function.
     */
    private static final MWFunctionSignature sRotSignature =
        new MWFunctionSignature(/* max outputs = */ 1,
                                /* has varargout = */ false,
                                /* function name = */ "rot",
                                /* max inputs = */ 2,
                                /* has varargin = */ false);

    /**
     * Shared initialization implementation - private
     * @throws MWException An error has occurred during the function call.
     */
    private DatasetRotator (final MWMCR mcr) throws MWException
    {
        super(mcr);
        // add this to sInstances
        synchronized(DatasetRotator.class) {
            sInstances.add(this);
        }
    }

    /**
     * Constructs a new instance of the <code>DatasetRotator</code> class.
     * @throws MWException An error has occurred during the function call.
     */
    public DatasetRotator() throws MWException
    {
        this(DataMergingMatlabUtilsMCRFactory.newInstance());
    }
    
    private static MWComponentOptions getPathToComponentOptions(String path)
    {
        MWComponentOptions options = new MWComponentOptions(new MWCtfExtractLocation(path),
                                                            new MWCtfDirectorySource(path));
        return options;
    }
    
    /**
     * @deprecated Please use the constructor {@link #DatasetRotator(MWComponentOptions componentOptions)}.
     * The <code>com.mathworks.toolbox.javabuilder.MWComponentOptions</code> class provides an API to set the
     * path to the component.
     * @param pathToComponent Path to component directory.
     * @throws MWException An error has occurred during the function call.
     */
    public DatasetRotator(String pathToComponent) throws MWException
    {
        this(DataMergingMatlabUtilsMCRFactory.newInstance(getPathToComponentOptions(pathToComponent)));
    }
    
    /**
     * Constructs a new instance of the <code>DatasetRotator</code> class. Use this 
     * constructor to specify the options required to instantiate this component.  The 
     * options will be specific to the instance of this component being created.
     * @param componentOptions Options specific to the component.
     * @throws MWException An error has occurred during the function call.
     */
    public DatasetRotator(MWComponentOptions componentOptions) throws MWException
    {
        this(DataMergingMatlabUtilsMCRFactory.newInstance(componentOptions));
    }
    
    /** Frees native resources associated with this object */
    public void dispose()
    {
        try {
            super.dispose();
        } finally {
            synchronized(DatasetRotator.class) {
                sInstances.remove(this);
            }
        }
    }
  
    /**
     * Invokes the first MATLAB function specified to MCC, with any arguments given on
     * the command line, and prints the result.
     *
     * @param args arguments to the function
     */
    public static void main (String[] args)
    {
        try {
            MWMCR mcr = DataMergingMatlabUtilsMCRFactory.newInstance();
            mcr.runMain( sGenRot2DSignature, args);
            mcr.dispose();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    /**
     * Calls dispose method for each outstanding instance of this class.
     */
    public static void disposeAllInstances()
    {
        synchronized(DatasetRotator.class) {
            for (Disposable i : sInstances) i.dispose();
            sInstances.clear();
        }
    }

    /**
     * Provides the interface for calling the <code>genRot2D</code> MATLAB function 
     * where the first argument, an instance of List, receives the output of the MATLAB function and
     * the second argument, also an instance of List, provides the input to the MATLAB function.
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * %GENROT Summary of this function goes here
     * %   Detailed explanation goes here
     * </pre>
     * @param lhs List in which to return outputs. Number of outputs (nargout) is
     * determined by allocated size of this List. Outputs are returned as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>.
     * Each output array should be freed by calling its <code>dispose()</code>
     * method.
     *
     * @param rhs List containing inputs. Number of inputs (nargin) is determined
     * by the allocated size of this List. Input arguments may be passed as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or
     * as arrays of any supported Java type. Arguments passed as Java types are
     * converted to MATLAB arrays according to default conversion rules.
     * @throws MWException An error has occurred during the function call.
     */
    public void genRot2D(List lhs, List rhs) throws MWException
    {
        fMCR.invoke(lhs, rhs, sGenRot2DSignature);
    }

    /**
     * Provides the interface for calling the <code>genRot2D</code> MATLAB function 
     * where the first argument, an Object array, receives the output of the MATLAB function and
     * the second argument, also an Object array, provides the input to the MATLAB function.
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * %GENROT Summary of this function goes here
     * %   Detailed explanation goes here
     * </pre>
     * @param lhs array in which to return outputs. Number of outputs (nargout)
     * is determined by allocated size of this array. Outputs are returned as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>.
     * Each output array should be freed by calling its <code>dispose()</code>
     * method.
     *
     * @param rhs array containing inputs. Number of inputs (nargin) is
     * determined by the allocated size of this array. Input arguments may be
     * passed as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of
     * any supported Java type. Arguments passed as Java types are converted to
     * MATLAB arrays according to default conversion rules.
     * @throws MWException An error has occurred during the function call.
     */
    public void genRot2D(Object[] lhs, Object[] rhs) throws MWException
    {
        fMCR.invoke(Arrays.asList(lhs), Arrays.asList(rhs), sGenRot2DSignature);
    }

    /**
     * Provides the standard interface for calling the <code>genRot2D</code> MATLAB function with 
     * 2 comma-separated input arguments.
     * Input arguments may be passed as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of
     * any supported Java type. Arguments passed as Java types are converted to
     * MATLAB arrays according to default conversion rules.
     *
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * %GENROT Summary of this function goes here
     * %   Detailed explanation goes here
     * </pre>
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the MATLAB function.
     * @return Array of length nargout containing the function outputs. Outputs
     * are returned as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>. Each output array
     * should be freed by calling its <code>dispose()</code> method.
     * @throws MWException An error has occurred during the function call.
     */
    public Object[] genRot2D(int nargout, Object... rhs) throws MWException
    {
        Object[] lhs = new Object[nargout];
        fMCR.invoke(Arrays.asList(lhs), 
                    MWMCR.getRhsCompat(rhs, sGenRot2DSignature), 
                    sGenRot2DSignature);
        return lhs;
    }
    /**
     * Provides the interface for calling the <code>genRot2DDrift</code> MATLAB function 
     * where the first argument, an instance of List, receives the output of the MATLAB function and
     * the second argument, also an instance of List, provides the input to the MATLAB function.
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * %GENROT Summary of this function goes here
     * %   Detailed explanation goes here
     * </pre>
     * @param lhs List in which to return outputs. Number of outputs (nargout) is
     * determined by allocated size of this List. Outputs are returned as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>.
     * Each output array should be freed by calling its <code>dispose()</code>
     * method.
     *
     * @param rhs List containing inputs. Number of inputs (nargin) is determined
     * by the allocated size of this List. Input arguments may be passed as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or
     * as arrays of any supported Java type. Arguments passed as Java types are
     * converted to MATLAB arrays according to default conversion rules.
     * @throws MWException An error has occurred during the function call.
     */
    public void genRot2DDrift(List lhs, List rhs) throws MWException
    {
        fMCR.invoke(lhs, rhs, sGenRot2DDriftSignature);
    }

    /**
     * Provides the interface for calling the <code>genRot2DDrift</code> MATLAB function 
     * where the first argument, an Object array, receives the output of the MATLAB function and
     * the second argument, also an Object array, provides the input to the MATLAB function.
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * %GENROT Summary of this function goes here
     * %   Detailed explanation goes here
     * </pre>
     * @param lhs array in which to return outputs. Number of outputs (nargout)
     * is determined by allocated size of this array. Outputs are returned as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>.
     * Each output array should be freed by calling its <code>dispose()</code>
     * method.
     *
     * @param rhs array containing inputs. Number of inputs (nargin) is
     * determined by the allocated size of this array. Input arguments may be
     * passed as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of
     * any supported Java type. Arguments passed as Java types are converted to
     * MATLAB arrays according to default conversion rules.
     * @throws MWException An error has occurred during the function call.
     */
    public void genRot2DDrift(Object[] lhs, Object[] rhs) throws MWException
    {
        fMCR.invoke(Arrays.asList(lhs), Arrays.asList(rhs), sGenRot2DDriftSignature);
    }

    /**
     * Provides the standard interface for calling the <code>genRot2DDrift</code> MATLAB function with 
     * 3 comma-separated input arguments.
     * Input arguments may be passed as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of
     * any supported Java type. Arguments passed as Java types are converted to
     * MATLAB arrays according to default conversion rules.
     *
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * %GENROT Summary of this function goes here
     * %   Detailed explanation goes here
     * </pre>
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the MATLAB function.
     * @return Array of length nargout containing the function outputs. Outputs
     * are returned as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>. Each output array
     * should be freed by calling its <code>dispose()</code> method.
     * @throws MWException An error has occurred during the function call.
     */
    public Object[] genRot2DDrift(int nargout, Object... rhs) throws MWException
    {
        Object[] lhs = new Object[nargout];
        fMCR.invoke(Arrays.asList(lhs), 
                    MWMCR.getRhsCompat(rhs, sGenRot2DDriftSignature), 
                    sGenRot2DDriftSignature);
        return lhs;
    }
    /**
     * Provides the interface for calling the <code>BaseLineBatchAbruptDrift</code> MATLAB function 
     * where the first argument, an instance of List, receives the output of the MATLAB function and
     * the second argument, also an instance of List, provides the input to the MATLAB function.
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * %generate random points
     * </pre>
     * @param lhs List in which to return outputs. Number of outputs (nargout) is
     * determined by allocated size of this List. Outputs are returned as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>.
     * Each output array should be freed by calling its <code>dispose()</code>
     * method.
     *
     * @param rhs List containing inputs. Number of inputs (nargin) is determined
     * by the allocated size of this List. Input arguments may be passed as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or
     * as arrays of any supported Java type. Arguments passed as Java types are
     * converted to MATLAB arrays according to default conversion rules.
     * @throws MWException An error has occurred during the function call.
     */
    public void BaseLineBatchAbruptDrift(List lhs, List rhs) throws MWException
    {
        fMCR.invoke(lhs, rhs, sBaseLineBatchAbruptDriftSignature);
    }

    /**
     * Provides the interface for calling the <code>BaseLineBatchAbruptDrift</code> MATLAB function 
     * where the first argument, an Object array, receives the output of the MATLAB function and
     * the second argument, also an Object array, provides the input to the MATLAB function.
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * %generate random points
     * </pre>
     * @param lhs array in which to return outputs. Number of outputs (nargout)
     * is determined by allocated size of this array. Outputs are returned as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>.
     * Each output array should be freed by calling its <code>dispose()</code>
     * method.
     *
     * @param rhs array containing inputs. Number of inputs (nargin) is
     * determined by the allocated size of this array. Input arguments may be
     * passed as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of
     * any supported Java type. Arguments passed as Java types are converted to
     * MATLAB arrays according to default conversion rules.
     * @throws MWException An error has occurred during the function call.
     */
    public void BaseLineBatchAbruptDrift(Object[] lhs, Object[] rhs) throws MWException
    {
        fMCR.invoke(Arrays.asList(lhs), Arrays.asList(rhs), sBaseLineBatchAbruptDriftSignature);
    }

    /**
     * Provides the standard interface for calling the <code>BaseLineBatchAbruptDrift</code> MATLAB function with 
     * 3 comma-separated input arguments.
     * Input arguments may be passed as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of
     * any supported Java type. Arguments passed as Java types are converted to
     * MATLAB arrays according to default conversion rules.
     *
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * %generate random points
     * </pre>
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the MATLAB function.
     * @return Array of length nargout containing the function outputs. Outputs
     * are returned as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>. Each output array
     * should be freed by calling its <code>dispose()</code> method.
     * @throws MWException An error has occurred during the function call.
     */
    public Object[] BaseLineBatchAbruptDrift(int nargout, Object... rhs) throws MWException
    {
        Object[] lhs = new Object[nargout];
        fMCR.invoke(Arrays.asList(lhs), 
                    MWMCR.getRhsCompat(rhs, sBaseLineBatchAbruptDriftSignature), 
                    sBaseLineBatchAbruptDriftSignature);
        return lhs;
    }
    /**
     * Provides the interface for calling the <code>BaseLineBatchDrift</code> MATLAB function 
     * where the first argument, an instance of List, receives the output of the MATLAB function and
     * the second argument, also an instance of List, provides the input to the MATLAB function.
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * %BASELINEBATCHDRIFT Summary of this function goes here
     * %   Detailed explanation goes here
     * </pre>
     * @param lhs List in which to return outputs. Number of outputs (nargout) is
     * determined by allocated size of this List. Outputs are returned as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>.
     * Each output array should be freed by calling its <code>dispose()</code>
     * method.
     *
     * @param rhs List containing inputs. Number of inputs (nargin) is determined
     * by the allocated size of this List. Input arguments may be passed as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or
     * as arrays of any supported Java type. Arguments passed as Java types are
     * converted to MATLAB arrays according to default conversion rules.
     * @throws MWException An error has occurred during the function call.
     */
    public void BaseLineBatchDrift(List lhs, List rhs) throws MWException
    {
        fMCR.invoke(lhs, rhs, sBaseLineBatchDriftSignature);
    }

    /**
     * Provides the interface for calling the <code>BaseLineBatchDrift</code> MATLAB function 
     * where the first argument, an Object array, receives the output of the MATLAB function and
     * the second argument, also an Object array, provides the input to the MATLAB function.
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * %BASELINEBATCHDRIFT Summary of this function goes here
     * %   Detailed explanation goes here
     * </pre>
     * @param lhs array in which to return outputs. Number of outputs (nargout)
     * is determined by allocated size of this array. Outputs are returned as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>.
     * Each output array should be freed by calling its <code>dispose()</code>
     * method.
     *
     * @param rhs array containing inputs. Number of inputs (nargin) is
     * determined by the allocated size of this array. Input arguments may be
     * passed as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of
     * any supported Java type. Arguments passed as Java types are converted to
     * MATLAB arrays according to default conversion rules.
     * @throws MWException An error has occurred during the function call.
     */
    public void BaseLineBatchDrift(Object[] lhs, Object[] rhs) throws MWException
    {
        fMCR.invoke(Arrays.asList(lhs), Arrays.asList(rhs), sBaseLineBatchDriftSignature);
    }

    /**
     * Provides the standard interface for calling the <code>BaseLineBatchDrift</code> MATLAB function with 
     * 5 comma-separated input arguments.
     * Input arguments may be passed as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of
     * any supported Java type. Arguments passed as Java types are converted to
     * MATLAB arrays according to default conversion rules.
     *
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * %BASELINEBATCHDRIFT Summary of this function goes here
     * %   Detailed explanation goes here
     * </pre>
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the MATLAB function.
     * @return Array of length nargout containing the function outputs. Outputs
     * are returned as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>. Each output array
     * should be freed by calling its <code>dispose()</code> method.
     * @throws MWException An error has occurred during the function call.
     */
    public Object[] BaseLineBatchDrift(int nargout, Object... rhs) throws MWException
    {
        Object[] lhs = new Object[nargout];
        fMCR.invoke(Arrays.asList(lhs), 
                    MWMCR.getRhsCompat(rhs, sBaseLineBatchDriftSignature), 
                    sBaseLineBatchDriftSignature);
        return lhs;
    }
    /**
     * Provides the interface for calling the <code>genRot</code> MATLAB function 
     * where the first argument, an instance of List, receives the output of the MATLAB function and
     * the second argument, also an instance of List, provides the input to the MATLAB function.
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * %GENROT Summary of this function goes here
     * %   Detailed explanation goes here
     * </pre>
     * @param lhs List in which to return outputs. Number of outputs (nargout) is
     * determined by allocated size of this List. Outputs are returned as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>.
     * Each output array should be freed by calling its <code>dispose()</code>
     * method.
     *
     * @param rhs List containing inputs. Number of inputs (nargin) is determined
     * by the allocated size of this List. Input arguments may be passed as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or
     * as arrays of any supported Java type. Arguments passed as Java types are
     * converted to MATLAB arrays according to default conversion rules.
     * @throws MWException An error has occurred during the function call.
     */
    public void genRot(List lhs, List rhs) throws MWException
    {
        fMCR.invoke(lhs, rhs, sGenRotSignature);
    }

    /**
     * Provides the interface for calling the <code>genRot</code> MATLAB function 
     * where the first argument, an Object array, receives the output of the MATLAB function and
     * the second argument, also an Object array, provides the input to the MATLAB function.
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * %GENROT Summary of this function goes here
     * %   Detailed explanation goes here
     * </pre>
     * @param lhs array in which to return outputs. Number of outputs (nargout)
     * is determined by allocated size of this array. Outputs are returned as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>.
     * Each output array should be freed by calling its <code>dispose()</code>
     * method.
     *
     * @param rhs array containing inputs. Number of inputs (nargin) is
     * determined by the allocated size of this array. Input arguments may be
     * passed as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of
     * any supported Java type. Arguments passed as Java types are converted to
     * MATLAB arrays according to default conversion rules.
     * @throws MWException An error has occurred during the function call.
     */
    public void genRot(Object[] lhs, Object[] rhs) throws MWException
    {
        fMCR.invoke(Arrays.asList(lhs), Arrays.asList(rhs), sGenRotSignature);
    }

    /**
     * Provides the standard interface for calling the <code>genRot</code> MATLAB function with 
     * 2 comma-separated input arguments.
     * Input arguments may be passed as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of
     * any supported Java type. Arguments passed as Java types are converted to
     * MATLAB arrays according to default conversion rules.
     *
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * %GENROT Summary of this function goes here
     * %   Detailed explanation goes here
     * </pre>
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the MATLAB function.
     * @return Array of length nargout containing the function outputs. Outputs
     * are returned as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>. Each output array
     * should be freed by calling its <code>dispose()</code> method.
     * @throws MWException An error has occurred during the function call.
     */
    public Object[] genRot(int nargout, Object... rhs) throws MWException
    {
        Object[] lhs = new Object[nargout];
        fMCR.invoke(Arrays.asList(lhs), 
                    MWMCR.getRhsCompat(rhs, sGenRotSignature), 
                    sGenRotSignature);
        return lhs;
    }
    /**
     * Provides the interface for calling the <code>rot</code> MATLAB function 
     * where the first argument, an instance of List, receives the output of the MATLAB function and
     * the second argument, also an instance of List, provides the input to the MATLAB function.
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * %ROT Summary of this function goes here
     * %   Detailed explanation goes here
     * </pre>
     * @param lhs List in which to return outputs. Number of outputs (nargout) is
     * determined by allocated size of this List. Outputs are returned as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>.
     * Each output array should be freed by calling its <code>dispose()</code>
     * method.
     *
     * @param rhs List containing inputs. Number of inputs (nargin) is determined
     * by the allocated size of this List. Input arguments may be passed as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or
     * as arrays of any supported Java type. Arguments passed as Java types are
     * converted to MATLAB arrays according to default conversion rules.
     * @throws MWException An error has occurred during the function call.
     */
    public void rot(List lhs, List rhs) throws MWException
    {
        fMCR.invoke(lhs, rhs, sRotSignature);
    }

    /**
     * Provides the interface for calling the <code>rot</code> MATLAB function 
     * where the first argument, an Object array, receives the output of the MATLAB function and
     * the second argument, also an Object array, provides the input to the MATLAB function.
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * %ROT Summary of this function goes here
     * %   Detailed explanation goes here
     * </pre>
     * @param lhs array in which to return outputs. Number of outputs (nargout)
     * is determined by allocated size of this array. Outputs are returned as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>.
     * Each output array should be freed by calling its <code>dispose()</code>
     * method.
     *
     * @param rhs array containing inputs. Number of inputs (nargin) is
     * determined by the allocated size of this array. Input arguments may be
     * passed as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of
     * any supported Java type. Arguments passed as Java types are converted to
     * MATLAB arrays according to default conversion rules.
     * @throws MWException An error has occurred during the function call.
     */
    public void rot(Object[] lhs, Object[] rhs) throws MWException
    {
        fMCR.invoke(Arrays.asList(lhs), Arrays.asList(rhs), sRotSignature);
    }

    /**
     * Provides the standard interface for calling the <code>rot</code> MATLAB function with 
     * 2 comma-separated input arguments.
     * Input arguments may be passed as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of
     * any supported Java type. Arguments passed as Java types are converted to
     * MATLAB arrays according to default conversion rules.
     *
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * %ROT Summary of this function goes here
     * %   Detailed explanation goes here
     * </pre>
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the MATLAB function.
     * @return Array of length nargout containing the function outputs. Outputs
     * are returned as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>. Each output array
     * should be freed by calling its <code>dispose()</code> method.
     * @throws MWException An error has occurred during the function call.
     */
    public Object[] rot(int nargout, Object... rhs) throws MWException
    {
        Object[] lhs = new Object[nargout];
        fMCR.invoke(Arrays.asList(lhs), 
                    MWMCR.getRhsCompat(rhs, sRotSignature), 
                    sRotSignature);
        return lhs;
    }
}
