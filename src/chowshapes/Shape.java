package chowshapes;

import jengine.Cartesian2D;
import jengine.Vector2D;

public abstract class Shape {
	protected double x,y,width,height;
	public Shape(double x, double y, double width, double height)
	{
		this.x = x;
		this.y = y;
		this.width  = width;
		this.height = height;
		//genPoints();
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
		genPoints();
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
		genPoints();
	}
	public double getWidth()
	{
		return this.width;
	}
	public void setWidth(double width)
	{
		this.width = width;
		genPoints();
	}
	public double getHeight()
	{
		return this.height;
	}
	public void setHeight(double height)
	{
		this.height = height;
		genPoints();
	}
	public abstract double getArea();

	protected abstract void genPoints();
	public abstract Cartesian2D[] getPoints();
	public abstract Cartesian2D getCenter();
	public abstract Vector2D[] getVectors();
	public abstract Vector2D[] getNormals();
	public abstract void printPoints();
}


