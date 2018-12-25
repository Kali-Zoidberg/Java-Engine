package jengine;

public class Vector2D extends Cartesian2D{

	private double magn = 0.0;
	private double dir = 0.0f;

	
	public Vector2D() {
		//doesnt do much lol
	}
	public Vector2D(Vector2D vectorB)
	{
		super(vectorB.getX(), vectorB.getY());
		magn = Math.hypot(vectorB.getX(), vectorB.getY());
		dir = Math.atan2(y,x);
	}
	
	/**
	 * Constructs a vector with only x and y points.
	 * @param x The X point of the vector
	 * @param y The Y point of the vector.
	 */
	
	public Vector2D(double x, double y) {
		super(x,y);
		magn = Math.hypot(x, y);
		dir =  Math.atan2(y,x);
		//dir = (float) Math.atan(magn);
	}
	/**
	 * Constructs a vector object with a magnitude and direction (in radians).
	 * @param magn Magnitude of the vector.
	 * @param dir Direction of the vector passed in radians.
	 */
	public Vector2D(double magnitude, float direction) {
		magn = magnitude;
		dir = (double) direction;
		
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
		dir = (float) Math.atan2(y,x);	
	}
	
	/**
	 * Sets the y coordinate of the Vector.
	 * @param y The new y coordinate of the vector.
	 */
	
	public void setY(double y) {
		super.setY(y);
		magn = Math.hypot(x, y);
		dir = (float) Math.atan2(y,x);
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
	
	public void setDirection(double direction) {
		
		dir = direction;
		y = magn * Math.sin(dir);
		x = magn * Math.cos(dir);
	}
	
	
	/**
	 * Sets both the magnitude and direction. This method is most effective when both magnitude and direction need to be changed.
	 * @param magnitude The new magnitude of the vector.
	 * @param direction The new direction of the vector.
	 */
	
	public void setMagnAndDir(double magnitude, double direction) {
		this.setMagnitude(magnitude);
		direction = dir;
	}
	
	
	/**
	 * Converts the direction from radians to degrees and returns it.
	 * @return Returns the direction in degrees.
	 */
	
	public double getDirection_Degree() {
		float temp_dir = (float) (dir * (180/Math.PI));
		return temp_dir;
	}
	
	/**
	 * Finds the dot product of the current vector with VectorB
	 * @param vectorB The Vector to perform the dot operation with
	 * @return Returns a double representing the scalar result of the dot product
	 */
	public double dot(Vector2D vectorB)
	{
		return (this.getX() * vectorB.getX()) + (this.getY() * vectorB.getY());
	}
	
	public double dot2(Vector2D vectorB)
	{
		return (this.getMagnitude() * vectorB.getMagnitude() * Math.cos(-1 * this.getDirection() + vectorB.getDirection()));
	}
	
	/**
	 * copies the vector and returns a left normal
	 * @return Return a left normal such that V(x,y) = U(-y,x)
	 */
	public Vector2D normL()
	{
		Vector2D retVector = new Vector2D(this);
		double origX = retVector.getX();
		double origY = retVector.getY();
		retVector.setX(origY * -1);
		retVector.setY(origX);
		
		//retVector.setDirection(retVector.getDirection() + 1.5708);
		return retVector;
	}
	/**
	 * Copies the vector and returns a right normal
	 * @return Returns a right normal such that V(x,y) = U(y,-x)
	 */
	public Vector2D normR()
	{
		
		Vector2D retVector = new Vector2D(this);
		double origX = retVector.getX();
		double origY = retVector.getY();
		retVector.setX(origY);
		retVector.setY(origX * - 1);
		return retVector;
	}
	/**
	 * Adds a vector by another vector
	 * @param vectorR The vector on the righthand side to add
	 * @return Returns a copy of the newly formed vector
	 */
	public Vector2D add(Vector2D vectorR)
	{
		Vector2D retVector = new Vector2D(this);
		retVector.x = retVector.getX() + vectorR.getX();
		retVector.y = retVector.getY() + vectorR.getY();
		retVector.magn = Math.hypot(retVector.getX(),retVector.getY());
		retVector.dir = Math.atan2(retVector.getY(), retVector.getX());
		return retVector;
	}
	/**
	 * Subtracts this vector by the vector on the RHS
	 * @param vectorR The vector on the RHS
	 * @return Returns a copy of the newly formed vecto.r
	 */
	public Vector2D sub(Vector2D vectorR)
	{
		Vector2D retVector = this.add(vectorR.mult(-1));
		return retVector;
		
	}
	
	/**
	 * Multiplies a vector by a scalar
	 * @param scalar The value to multi[ply the vector by
	 * @return Returns a copy of the vector
	 */
	public Vector2D mult(double scalar)
	{
		Vector2D retVector = new Vector2D(this);
		retVector.x *= scalar;
		retVector.y *= scalar;
		retVector.magn = Math.hypot(retVector.x, retVector.y);
		return retVector;
	}
	/**
	 * Returns a vector2d divided by a scalar
	 * @param scalar The value to divde a vector by
	 * @return Returns a copy of the divided vector
	 */
	public Vector2D div(double scalar)
	{
		Vector2D retVector = new Vector2D(this);
		retVector.x /= scalar;
		retVector.y /= scalar;
		retVector.magn = Math.hypot(retVector.x, retVector.y);
		return retVector;
	}
	
	/**
	 * Normalizes the vector into a unit vector and returns a copy of it
	 * @return A normalized/unit vector
	 */
	public Vector2D unitVector()
	{
		Vector2D retVector = new Vector2D(this);
		
		double inverseScalar = retVector.getMagnitude();
		if(inverseScalar == 0)
			return this;
		else
			return this.div(inverseScalar);
	}
	/**
	 * Returns the projection of this vector onto vectorB
	 * @param vectorB - The vector to project onto
	 * @return Returns proj this onto vectorB
	 */
	public Vector2D proj(Vector2D vectorB) {
		
		double diffTheta = this.getDirection() - vectorB.getDirection();
		double dotProd = this.dot(vectorB) * Math.cos(diffTheta);
		return new Vector2D(dotProd, (float) vectorB.getDirection());
		
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
