package jengine;


abstract class Shape {
	protected double x,y,width,height;
	Shape(double x, double y, double width, double height)
	{
		this.x = x;
		this.y = y;
		this.width  = width;
		this.height = height;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getWidth()
	{
		return this.width;
	}
	public void setWidth(double width)
	{
		this.width = width;
	}
	public double getHeight()
	{
		return this.height;
	}
	public void setHeight(double height)
	{
		this.height = height;
	}
	public abstract Cartesian2D[] getPoints();
	public abstract Cartesian2D getCenter();
	public abstract Vector2D[] getVectors();
	public abstract void printPoints();
}