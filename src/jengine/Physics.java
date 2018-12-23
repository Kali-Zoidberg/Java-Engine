package jengine;

public class Physics {
	private static Vector2D axis = new Vector2D(100,1);

	public static Vector2D getAxis() {
		return axis;
	}

	public static void setAxis(Vector2D axis) {
		Physics.axis = axis;
	}
	/**
	 * Calculates the normals of a shape and returns them as an array based on the nuber of segments of the shape.
	 * @param shape
	 * @return
	 */
	
	public static Vector2D[] getNormals(Shape shape)
	{
		Cartesian2D[] points = shape.getPoints();
		int pointsLen = shape.getPoints().length;
		Vector2D normals[] = new Vector2D[pointsLen - 1];
		for (int i = 1; i < pointsLen - 1; ++i)
		{
			//create vectors based on the points of the vector
			Vector2D vectorSegment = new Vector2D(points[i+1].getX() - points[i].getX(),
													points[i].getY() - points[i + 1].getY());
		
			normals[i - 1] = vectorSegment.normL();
		}
		//Special case since the last line segment goes from index 4 to 0
		Vector2D vectorSegment = new Vector2D(points[1].getX() - points[pointsLen - 1].getX(),
				points[1].getY() - points[pointsLen - 1].getY());
		
		normals[normals.length - 1] = vectorSegment.normL();

		return normals;
	}
	
	/**
	 * Calculates the min and max points of a shape by projecting them onto an axis
	 * @param shape The shape to calculate the projection
	 * @return Returns the min and max points of the shape in the form a Vector2D array.
	 */
	public static double[] minMaxProj(Vector2D[] normals, Vector2D axis)
	{
		
		int normLen = normals.length;
		Vector2D axisUnit = axis.unitVector();
		Vector2D minPointVect = new Vector2D(normals[1].getX(), normals[1].getY());
		Vector2D maxPointVect = new Vector2D(normals[1].getX(), normals[1].getY());
		
		//calc initial min and max dot products
		double maxDotProd = maxPointVect.dot(axisUnit);
		double minDotProd = minPointVect.dot(axisUnit);
		
		//start at index 2 because center is index 0, remember?
		for (int i = 2; i < normLen - 1; ++i)
		{
			Vector2D vectorSegment = new Vector2D(normals[i].getX(), normals[i].getY());
			double curDotProd = vectorSegment.dot(axisUnit);
			
		
			//find minimum point
			if(curDotProd < minDotProd)
			{
				minPointVect = vectorSegment;
				minDotProd = curDotProd;
			} //find max dot prod
			if(curDotProd > maxDotProd)
			{
				maxPointVect = vectorSegment;
				maxDotProd = curDotProd;
			}
		}
		double minAndMax[] = { minDotProd, maxDotProd};
		return minAndMax;
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
