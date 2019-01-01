package chowshapes;

import jengine.Cartesian2D;
import jengine.Vector2D;

public class Rectangle extends Shape {
	
	private Cartesian2D points[] = new Cartesian2D[5];
	
	public Rectangle(double x, double y, double width, double height)
	{
		super(x,y,width,height);
		genPoints();
		//printPoints();
	}
	/**
	 * 4------1
	 * |      |
	 * |   0  |
	 * |	  |
	 * 3------2
	 */
	protected void genPoints()
	{
		points[0] = getCenter();
		points[1] = new Cartesian2D(x + width, y);
		points[2] = new Cartesian2D(x + width, y + height);
		points[3] = new Cartesian2D(x,y+height);
		points[4] = new Cartesian2D(x,y);

	//	printPoints();
		
	}
	public void setX(double x)
	{
		super.setX(x);
		
	}
	public void setY(double y)
	{
		super.setY(y);
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
		
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public Cartesian2D getCenter()
	{
		return new Cartesian2D( (this.getX() + this.getWidth())/2, (this.getY() + this.getHeight()) / 2);
	}
	@Override
	public Cartesian2D[] getPoints() {
		
		points[0] = getCenter();
		points[1] = new Cartesian2D(x + width, y);
		points[2] = new Cartesian2D(x + width, y + height);
		points[3] = new Cartesian2D(x,y+height);
		points[4] = new Cartesian2D(x,y);
		return points;
	}
	/**
	 * Calculates the normals of a shape and returns them as an array based on the nuber of segments of the shape.
	 
	 * @return
	 */
	
	public Vector2D[] getNormals()
	{
		
		int pointsLen = points.length;
		Vector2D normals[] = new Vector2D[pointsLen - 1];
		for (int i = 1; i < pointsLen - 1; ++i)
		{
			//create vectors based on the points of the vector
			Vector2D vectorSegment = new Vector2D(points[i+1].getX() - points[i].getX(),
													points[i + 1].getY() - points[i].getY());
		
			normals[i - 1] = vectorSegment.normL();
		}
		//Special case since the last line segment goes from index 4 to 0
		Vector2D vectorSegment = new Vector2D(points[1].getX() - points[pointsLen - 1].getX(),
				points[1].getY() - points[pointsLen - 1].getY());
		
		normals[normals.length - 1] = vectorSegment.normL();

		return normals;
	}
	
	public Vector2D[] getVectors()
	{
		//create an array of size points -1 because initioal point is center
		Vector2D[] retVectors = new Vector2D[points.length - 1];
		for (int i = 1; i < points.length; ++i)
		{
			//start at index one because the first point is the center and we do not want its vector
			retVectors[i - 1] = new Vector2D(points[i].getX(), points[i].getY());
		}
		return retVectors;
	}
	
	public void printPoints()
	{
		System.out.println("\n*****\n");
		for (int i = 0; i< points.length; ++i)
			System.out.printf("Point[%d]. X: %f Y: %f \n", i, points[i].getX(), points[i].getY());
	}
	@Override
	public double getArea() {
		return width * height;
	}
}
