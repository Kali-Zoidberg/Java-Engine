package chowshapes;

import jengine.Cartesian2D;
import jengine.Vector2D;

public class Hexagon extends Shape{
	private Cartesian2D points[] = new Cartesian2D[7];
	public Hexagon(double x, double y, double width, double height) 
	{
		super(x, y, width, height);
		genPoints();
		printNormals();
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void genPoints() {
		points[1] = new Cartesian2D(this.getX(), this.getY() + this.getHeight() / 2);
		points[2] = new Cartesian2D(this.getX() + this.getWidth() / 4, this.getY() + this.getHeight());
		points[3] = new Cartesian2D(this.getX() + this.getWidth() * 0.75, this.getY() + this.getHeight());
		points[4] = new Cartesian2D(this.getX() + this.getWidth(), this.getY() + this.getHeight() / 2);
		points[5] = new Cartesian2D(this.getX() + this.getWidth() *0.75, this.getY());
		points[6] = new Cartesian2D(this.getX() + this.getWidth() / 4, this.getY());
	}

	@Override
	public Cartesian2D[] getPoints() {
		points[1] = new Cartesian2D(this.getX(), this.getY() + this.getHeight() / 2);
		points[2] = new Cartesian2D(this.getX() + this.getWidth() / 4, this.getY() + this.getHeight());
		points[3] = new Cartesian2D(this.getX() + this.getWidth() * 3/4, this.getY() + this.getHeight());
		points[4] = new Cartesian2D(this.getX() + this.getWidth(), this.getY() + this.getHeight() / 2);
		points[5] = new Cartesian2D(this.getX() + this.getWidth() * 3/4, this.getY());
		points[6] = new Cartesian2D(this.getX() + this.getWidth() / 4, this.getY());
		return points;
	}
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
	@Override
	public Cartesian2D getCenter() {
		// TODO Auto-generated method stub
		return null;
	}
	public void printNormals()
	{
		Vector2D norms[] = getNormals();
		for (int i = 0; i < norms.length; ++i)
			System.out.println(norms[i].toString());
	}
	@Override
	public void printPoints() {
		// TODO Auto-generated method stub
		
	}

}
