
public class Transform extends Cartesian2D {
	
	private int width = 10;
	private int height = 10;
	
	public Vector2D veloc;
	
	/**
	 * Default constructor for transform. Creates a Cartesian coordinate with the parameters (0,0)
	 */
	
	Transform() {
		super(0,0);
	}
	
	
	/**
	 * Constructor for the transform. Invokes the super class with a specified Cartesian coordinate.
	 * @param coordinate  The Cartesian2D coordinate of the desired transform.
	 */
	
	Transform(Cartesian2D coordinate) {
		super(coordinate.getPosition_X(),coordinate.getPosition_Y());
	}
	
	
	/**
	 * Constructs a transform object with the specified x and y coordinate.
	 * @param x  Coordinate X as an integer.
	 * @param y  Coordinate Y as an integer
	 */
	
	Transform(int x, int y) {
		super(x,y);
	}
	
	/**
	 * Sets the transformation's velocity with a 2D vector.
	 * @param velocity The velocity of the transform as a 2D vector.
	 */
	 
	public void setVelocity(Vector2D velocity) {
		
		veloc = velocity;
		
	}
	
	
	/**
	 * Returns the velocity as a 2D vector.
	 * @return Returns the velocity.
	 */
	
	public Vector2D getVelocity() {
		return veloc;
	}

}
