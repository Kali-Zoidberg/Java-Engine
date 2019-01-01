package chowshapes;

import jengine.Cartesian2D;
import jengine.Vector2D;

public class Triangle extends Shape{
	protected Cartesian2D points[] = new Cartesian2D[4];
	public Triangle(double x, double y, double width, double height) {
		super(x, y, width, height);
		getPoints();
		printPoints();
		printNormals();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void genPoints() {
		// TODO Auto-generated method stub
		points[2] =  new Cartesian2D(this.x + width/2, this.y);
		points[1] = new Cartesian2D(this.x, this.y + height);
		points[3] = new Cartesian2D(this.x + width, this.y + this.height);
		points[0] = this.getCenter();
	}

	@Override
	public Cartesian2D[] getPoints() {
		//center points[0]
		//	 w/2	w
		
		//x			y
					
		points[2] =  new Cartesian2D(this.x + width/2, this.y);
		points[1] = new Cartesian2D(this.x, this.y + height);
		points[3] = new Cartesian2D(this.x + width, this.y + this.height);
		points[0] = this.getCenter();
		// TODO Auto-generated method stub
		return points;
	}

	@Override
	public Cartesian2D getCenter() {
		double centerX = 0;
		double centerY = 0;
		for (int i = 1; i < 4; ++i)
		{
			centerX += points[i].getX();
			centerY += points[i].getY();
		}
		
		// TODO Auto-generated method stub
		return new Cartesian2D(centerX/points.length - 1, centerY/points.length - 1);
	}

	@Override
	public Vector2D[] getVectors() {
		Vector2D[] retVectors = new Vector2D[points.length - 1];
		for (int i = 1; i < points.length; ++i)
		{
			//start at index one because the first point is the center and we do not want its vector
			retVectors[i - 1] = new Vector2D(points[i].getX(), points[i].getY());
		}
		return retVectors;
	}

	@Override
	public Vector2D[] getNormals() {
		int pointsLen = points.length;
		Vector2D normals[] = new Vector2D[pointsLen - 1];
		for (int i = 1; i < pointsLen - 1 ; ++i)
		{
			//create vectors based on the points of the vector
			Vector2D vectorSegment = new Vector2D(points[i + 1].getX() - points[i].getX(),
													points[i + 1].getY() - points[i].getY());
		
			normals[i - 1] = vectorSegment.normL();
		}
		//Special case since the last line segment goes from index 4 to 0
		Vector2D vectorSegment = new Vector2D(points[1].getX() - points[pointsLen - 1].getX(),
				points[1].getY() - points[pointsLen - 1].getY());
		normals[normals.length - 1] = vectorSegment.normL();

		return normals;
	}

	@Override
	public void printPoints() {
		System.out.println("\n*****\n");
		for (int i = 0; i< points.length; ++i)
			System.out.printf("Point[%d]. X: %f Y: %f \n", i, points[i].getX(), points[i].getY());		
	}
	public void printNormals()
	{
		Vector2D norms[] = getNormals();
		for (int i = 0; i < norms.length; ++i)
			System.out.println(norms[i].toString());
	}

	@Override
	public double getArea() {
		return width * (height/2);
	}

}
