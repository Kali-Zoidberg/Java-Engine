package chowshapes;

import jengine.Cartesian2D;
import jengine.Vector2D;

public class Circle extends Shape
{
	protected Cartesian2D[] points = new Cartesian2D[1];
	private double radius = 1;
	public Circle(double x, double y, double radius) {
		super(x, y, radius* 2 , radius  * 2);
		genPoints();
		this.setRadius(radius);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void genPoints() {
		points[0] = new Cartesian2D(this.getX() + this.radius, this.getY() + this.radius);
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cartesian2D[] getPoints() {
		genPoints();
		// TODO Auto-generated method stub
		return points;
	}

	@Override
	public Cartesian2D getCenter() {
		points[0] = new Cartesian2D(this.getX() + this.radius, this.getY() + this.radius);
		return points[0];
	}

	@Override
	public Vector2D[] getVectors() {
		// TODO Auto-generated method stub
		Vector2D[] retVectors = {new Vector2D(points[0].getX(), points[0].getY())};
		return retVectors;
	}

	@Override
	public Vector2D[] getNormals() {
		Vector2D[] retVectors = {getVectors()[0].normL()};
		return retVectors;
	}

	@Override
	public void printPoints() {
		// TODO Auto-generated method stub
		
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
		super.setHeight(radius * 2);
		super.setWidth(radius * 2);
	}

	@Override
	public double getArea() {
		return (this.radius * this.radius) * Math.PI;
	}
	
}