package jengine;
/**
 * 
 * @author Nicholas Chow
 *	Credits to Kah Shiu Chong for his tutorial on the Separating Axis Theorem tutorial and psuedocode.
 *	You may find the tutorial here: https://gamedevelopment.tutsplus.com/tutorials/collision-detection-using-the-separating-axis-theorem--gamedev-169
 */
public class Physics {

	
	
	
	/**
	 * Calculates the min and max points of a shape by projecting them onto an axis
	 * @param shape The shape to calculate the projection
	 * @return Returns the min and max points of the shape in the form a Vector2D array.
	 */
	public static double[] minMaxProj(Vector2D[] normals, Vector2D axis)
	{
		
		int normLen = normals.length;
		Vector2D axisUnit = axis.unitVector();
		Vector2D minPointVect = new Vector2D(normals[0].getX(), normals[0].getY());
		Vector2D maxPointVect = new Vector2D(normals[0].getX(), normals[0].getY());
		
		//calc initial min and max dot products
		double maxDotProd = maxPointVect.dot(axisUnit);
		double minDotProd = minPointVect.dot(axisUnit);
		
		//start at index 1 because the min/max index is first 0.
		for (int i = 1; i < normLen - 1; ++i)
		{
			double curDotProd = normals[i].dot(axisUnit);
			
			
			//find minimum point
			if(minDotProd > curDotProd)
			{
				minPointVect = normals[i];
				minDotProd = curDotProd;
			} //find max dot prod
			if(curDotProd > maxDotProd)
			{
				maxPointVect = normals[i];
				maxDotProd = curDotProd;
			}
		}
		double minAndMax[] = { minDotProd, maxDotProd};
		return minAndMax;
	}
	
	public static boolean projOverLap(double[] minMaxA, double minMaxB[])
	{
		return (minMaxA[0] > minMaxB[1] || minMaxB[0] > minMaxA[1]);
	}
	
	public void findGaps()
	{
		
	}
	
	public void update()
	{
		/**
		 * calc all projetions?
		 */
	}
}
