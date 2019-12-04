/*
 * MATLAB Compiler: 7.0.1 (R2019a)
 * Date: Tue Dec  3 17:44:37 2019
 * Arguments: 
 * "-B""macro_default""-W""java:DataMergingMatlabUtils,Plotter""-T""link:lib""-d""C:\\repos\\l3s\\DAFDT\\coding\\libs""class{Plotter:C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genRot2D.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genRot2DDrift.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\pca2.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\pca2originalgenerated.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotBoxApproachStreamSizeAll.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotDS.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotOverlapped.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotOverlappedGradientBoxes.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotOverlappedGradientBoxesGrayAreas.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotStrEvals.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotStrEvalsComp2.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotStrEvalsComp2Split.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotStrEvalsComp3.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotStrEvalsComp3Split.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotStrEvalsComp3SplitDTSize.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotStrEvalsComp3SplitTag.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotStrEvalsCompBar.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotWithRuleBoundaries.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\printPctCorrectStream.m}""class{DatasetRotator:C:\\repos\\l3s\\DAFDT\\coding\\matlab\\BaseLineBatchAbruptDrift.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\BaseLineBatchDrift.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genRot.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genRot2D.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genRot2DDrift.m,C:\\repos\\l3s\\DAFDT\\coding\\matlab\\rot.m}""class{DatasetGenerator:C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genDS2.m}""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\filterDS.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genDS2.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genDS4.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\generateDS.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genrand.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genRot.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\normDS.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotBoxApproachStreamSize.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\plotDS.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\rot.m""-a""C:\\repos\\l3s\\DAFDT\\coding\\matlab\\rotateDS.m"
 */

package DataMergingMatlabUtils;

import com.mathworks.toolbox.javabuilder.pooling.Poolable;
import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The <code>DatasetRotatorRemote</code> class provides a Java RMI-compliant interface to 
 * MATLAB functions. The interface is compiled from the following files:
 * <pre>
 *  C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genRot2D.m
 *  C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genRot2DDrift.m
 *  C:\\repos\\l3s\\DAFDT\\coding\\matlab\\BaseLineBatchAbruptDrift.m
 *  C:\\repos\\l3s\\DAFDT\\coding\\matlab\\BaseLineBatchDrift.m
 *  C:\\repos\\l3s\\DAFDT\\coding\\matlab\\genRot.m
 *  C:\\repos\\l3s\\DAFDT\\coding\\matlab\\rot.m
 * </pre>
 * The {@link #dispose} method <b>must</b> be called on a 
 * <code>DatasetRotatorRemote</code> instance when it is no longer needed to ensure that 
 * native resources allocated by this class are properly freed, and the server-side proxy 
 * is unexported.  (Failure to call dispose may result in server-side threads not being 
 * properly shut down, which often appears as a hang.)  
 *
 * This interface is designed to be used together with 
 * <code>com.mathworks.toolbox.javabuilder.remoting.RemoteProxy</code> to automatically 
 * generate RMI server proxy objects for instances of 
 * DataMergingMatlabUtils.DatasetRotator.
 */
public interface DatasetRotatorRemote extends Poolable
{
    /**
     * Provides the standard interface for calling the <code>genRot2D</code> MATLAB 
     * function with 2 input arguments.  
     *
     * Input arguments to standard interface methods may be passed as sub-classes of 
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of any 
     * supported Java type (i.e. scalars and multidimensional arrays of any numeric, 
     * boolean, or character type, or String). Arguments passed as Java types are 
     * converted to MATLAB arrays according to default conversion rules.
     *
     * All inputs to this method must implement either Serializable (pass-by-value) or 
     * Remote (pass-by-reference) as per the RMI specification.
     *
     * Documentation as provided by the author of the MATLAB function:
     * <pre>
     * %GENROT Summary of this function goes here
     * %   Detailed explanation goes here
     * </pre>
     *
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the MATLAB function.
     *
     * @return Array of length nargout containing the function outputs. Outputs are 
     * returned as sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>. 
     * Each output array should be freed by calling its <code>dispose()</code> method.
     *
     * @throws java.rmi.RemoteException An error has occurred during the function call or 
     * in communication with the server.
     */
    public Object[] genRot2D(int nargout, Object... rhs) throws RemoteException;
    /**
     * Provides the standard interface for calling the <code>genRot2DDrift</code> MATLAB 
     * function with 3 input arguments.  
     *
     * Input arguments to standard interface methods may be passed as sub-classes of 
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of any 
     * supported Java type (i.e. scalars and multidimensional arrays of any numeric, 
     * boolean, or character type, or String). Arguments passed as Java types are 
     * converted to MATLAB arrays according to default conversion rules.
     *
     * All inputs to this method must implement either Serializable (pass-by-value) or 
     * Remote (pass-by-reference) as per the RMI specification.
     *
     * Documentation as provided by the author of the MATLAB function:
     * <pre>
     * %GENROT Summary of this function goes here
     * %   Detailed explanation goes here
     * </pre>
     *
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the MATLAB function.
     *
     * @return Array of length nargout containing the function outputs. Outputs are 
     * returned as sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>. 
     * Each output array should be freed by calling its <code>dispose()</code> method.
     *
     * @throws java.rmi.RemoteException An error has occurred during the function call or 
     * in communication with the server.
     */
    public Object[] genRot2DDrift(int nargout, Object... rhs) throws RemoteException;
    /**
     * Provides the standard interface for calling the 
     * <code>BaseLineBatchAbruptDrift</code> MATLAB function with 3 input arguments.  
     *
     * Input arguments to standard interface methods may be passed as sub-classes of 
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of any 
     * supported Java type (i.e. scalars and multidimensional arrays of any numeric, 
     * boolean, or character type, or String). Arguments passed as Java types are 
     * converted to MATLAB arrays according to default conversion rules.
     *
     * All inputs to this method must implement either Serializable (pass-by-value) or 
     * Remote (pass-by-reference) as per the RMI specification.
     *
     * Documentation as provided by the author of the MATLAB function:
     * <pre>
     * %generate random points
     * </pre>
     *
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the MATLAB function.
     *
     * @return Array of length nargout containing the function outputs. Outputs are 
     * returned as sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>. 
     * Each output array should be freed by calling its <code>dispose()</code> method.
     *
     * @throws java.rmi.RemoteException An error has occurred during the function call or 
     * in communication with the server.
     */
    public Object[] BaseLineBatchAbruptDrift(int nargout, Object... rhs) throws RemoteException;
    /**
     * Provides the standard interface for calling the <code>BaseLineBatchDrift</code> 
     * MATLAB function with 5 input arguments.  
     *
     * Input arguments to standard interface methods may be passed as sub-classes of 
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of any 
     * supported Java type (i.e. scalars and multidimensional arrays of any numeric, 
     * boolean, or character type, or String). Arguments passed as Java types are 
     * converted to MATLAB arrays according to default conversion rules.
     *
     * All inputs to this method must implement either Serializable (pass-by-value) or 
     * Remote (pass-by-reference) as per the RMI specification.
     *
     * Documentation as provided by the author of the MATLAB function:
     * <pre>
     * %BASELINEBATCHDRIFT Summary of this function goes here
     * %   Detailed explanation goes here
     * </pre>
     *
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the MATLAB function.
     *
     * @return Array of length nargout containing the function outputs. Outputs are 
     * returned as sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>. 
     * Each output array should be freed by calling its <code>dispose()</code> method.
     *
     * @throws java.rmi.RemoteException An error has occurred during the function call or 
     * in communication with the server.
     */
    public Object[] BaseLineBatchDrift(int nargout, Object... rhs) throws RemoteException;
    /**
     * Provides the standard interface for calling the <code>genRot</code> MATLAB 
     * function with 2 input arguments.  
     *
     * Input arguments to standard interface methods may be passed as sub-classes of 
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of any 
     * supported Java type (i.e. scalars and multidimensional arrays of any numeric, 
     * boolean, or character type, or String). Arguments passed as Java types are 
     * converted to MATLAB arrays according to default conversion rules.
     *
     * All inputs to this method must implement either Serializable (pass-by-value) or 
     * Remote (pass-by-reference) as per the RMI specification.
     *
     * Documentation as provided by the author of the MATLAB function:
     * <pre>
     * %GENROT Summary of this function goes here
     * %   Detailed explanation goes here
     * </pre>
     *
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the MATLAB function.
     *
     * @return Array of length nargout containing the function outputs. Outputs are 
     * returned as sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>. 
     * Each output array should be freed by calling its <code>dispose()</code> method.
     *
     * @throws java.rmi.RemoteException An error has occurred during the function call or 
     * in communication with the server.
     */
    public Object[] genRot(int nargout, Object... rhs) throws RemoteException;
    /**
     * Provides the standard interface for calling the <code>rot</code> MATLAB function 
     * with 2 input arguments.  
     *
     * Input arguments to standard interface methods may be passed as sub-classes of 
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of any 
     * supported Java type (i.e. scalars and multidimensional arrays of any numeric, 
     * boolean, or character type, or String). Arguments passed as Java types are 
     * converted to MATLAB arrays according to default conversion rules.
     *
     * All inputs to this method must implement either Serializable (pass-by-value) or 
     * Remote (pass-by-reference) as per the RMI specification.
     *
     * Documentation as provided by the author of the MATLAB function:
     * <pre>
     * %ROT Summary of this function goes here
     * %   Detailed explanation goes here
     * </pre>
     *
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the MATLAB function.
     *
     * @return Array of length nargout containing the function outputs. Outputs are 
     * returned as sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>. 
     * Each output array should be freed by calling its <code>dispose()</code> method.
     *
     * @throws java.rmi.RemoteException An error has occurred during the function call or 
     * in communication with the server.
     */
    public Object[] rot(int nargout, Object... rhs) throws RemoteException;
  
    /** 
     * Frees native resources associated with the remote server object 
     * @throws java.rmi.RemoteException An error has occurred during the function call or in communication with the server.
     */
    void dispose() throws RemoteException;
}
