

abstract class Shape {
	protected double x;
	protected double y;
	Shape(double x, double y)
	{
		
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
	public abstract Cartesian2D[] getPoints();
}