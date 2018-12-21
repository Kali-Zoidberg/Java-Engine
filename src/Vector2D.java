
public class Vector2D extends Cartesian2D{

	private double magn = 0.0;
	private float dir = 0.0f;

	
	Vector2D() {
		//doesnt do much lol
	}
	
	/**
	 * Constructs a vector with only x and y points.
	 * @param x The X point of the vector
	 * @param y The Y point of the vector.
	 */
	
	Vector2D(double x, double y) {
		super(x,y);
		magn = Math.hypot(x, y);
		dir = (float) Math.atan2(y,x);
		//dir = (float) Math.atan(magn);
	}
	/**
	 * Constructs a vector object with a magnitude and direction (in radians).
	 * @param magn Magnitude of the vector.
	 * @param dir Direction of the vector passed in radians.
	 */
	Vector2D(double magnitude, float direction) {
		magn = magnitude;
		dir = direction;
		
		double dir_sin = Math.sin(dir);
		double dir_cos = Math.cos(dir);
		
		y = magn * dir_sin;
		super.setY(y);
		
		x = magn * dir_cos;
		super.setX(x);
	}
	
	
	/**
	 * Sets the x coordinate of the Vector.
	 * @param The new x coordinate of the vector.
	 */
	
	public void setX(double x) {	
		super.setX(x);
		magn = Math.hypot(x, y);
		dir = (float) Math.atan(magn);	
	}
	
	/**
	 * Sets the y coordinate of the Vector.
	 * @param y The new y coordinate of the vector.
	 */
	
	public void setY(double y) {
		super.setY(y);
		magn = Math.hypot(x, y);
		dir = (float) Math.atan(magn);
	}
	
	
	/**
	 * Returns the horizontal (x) component of the vector.
	 */
	public double getX(){ return x; }
	
	/**
	 * Returns the vertical (y) component of the vector.
	 */
	public double getY(){ return y; }
	
	/**
	 * Retrieves the magnitude of the vector.
	 * @return Returns the magnitude of the vector.
	 */
	
	public double getMagnitude() {
		return magn;
	}
	
	/**
	 * SEts the Magnitude based on the given parameter. Instead of using trig functions,
	 * The ratio of the new magnitude over the old is calculated and used to shrink or grow the x and y coordinates.
	 * @param magnitude The new magnitude for the Vector.
	 */
	
	public void setMagnitude(double magnitude) {
		float ratio = (float) (magn/magnitude); //Calculate ratio then downcast.
		x *= ratio; 
		super.setX(x);
		
		y *= ratio;
		super.setY(y);
		
		magn = magnitude;
	}
	
	
	/**
	 * Retrieves the direction of the vector.
	 * @return Returns the direction of the vector.
	 */
	
	public double getDirection() {
		return dir;
	}
	
	
	/**
	 * Sets the direction of the vector.
	 * @param direction The new direction of the vector.
	 */
	
	public void setDirection(float direction) {
		
		dir = direction;
		
		double dir_sin = Math.sin(dir);
		double dir_cos = Math.cos(dir);
		
		y = magn * dir_sin;
		super.setY(y);
		
		x = magn * dir_cos;
		super.setX(x);
	}
	
	
	/**
	 * Sets both the magnitude and direction. This method is most effective when both magnitude and direction need to be changed.
	 * @param magnitude The new magnitude of the vector.
	 * @param direction The new direction of the vector.
	 */
	
	public void setMagnAndDir(double magnitude, float direction) {
		this.setMagnitude(magnitude);
		direction = dir;
	}
	
	
	/**
	 * Converts the direction from radians to degrees and returns it.
	 * @return Returns the direction in degrees.
	 */
	
	public float getDirection_Degree() {
		float temp_dir = (float) (dir * (180/Math.PI));
		return temp_dir;
	}
	
	public String toString()
	{
		String buf = String.format("X: %f Y: %f: Theta: %f", this.getX(),this.getY(), this.getDirection_Degree());
		return buf;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
}
