package edu.berkeley.compbio.jlibsvm.kernel;

import edu.berkeley.compbio.jlibsvm.MathSupport;
import edu.berkeley.compbio.jlibsvm.SvmException;
import edu.berkeley.compbio.jlibsvm.SvmPoint;

import java.util.Properties;

/**
 * @author <a href="mailto:dev@davidsoergel.com">David Soergel</a>
 * @version $Id$
 */
public class PolynomialKernel extends GammaKernel
	{
	public int degree;// for poly
	public float coef0;// for poly/sigmoid


	public PolynomialKernel(Properties props)
		{
		this(Integer.parseInt(props.getProperty("degree")), Float.parseFloat(props.getProperty("gamma")), Float.parseFloat(props.getProperty("coef0")));
		}

	public PolynomialKernel(int degree, float gamma, float coef0)
		{
		super(gamma);
		if (degree < 0)
			{
			throw new SvmException("degree of polynomial kernel < 0");
			}

		this.degree = degree;
		this.coef0 = coef0;
		}

	public float evaluate(SvmPoint x, SvmPoint y)
		{
		return MathSupport.powi(gamma * MathSupport.dot(x, y) + coef0, degree);
		}


	public String toString()
		{
		StringBuilder sb = new StringBuilder();
		sb.append("kernel_type polynomial\n");
		sb.append("degree " + degree + "\n");
		sb.append("gamma " + gamma + "\n");
		sb.append("coef0 " + coef0 + "\n");
		return sb.toString();
		}
	}