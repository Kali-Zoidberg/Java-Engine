
public class Transform extends Cartesian2D {
	private double x_pos = 0;
	private double y_pos = 0;
	

	
	private double dir = 0;

//	private static int screen_width = Render.getScreenWidth();
//	private static int screen_height = Render.getScreenHeight();

	
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
	 * @param direction The direction of the Actor.
	 */
	
	Transform(Cartesian2D coordinate, double direction) {
		super(coordinate.getX(),coordinate.getY());
		dir = direction;
	}
	
	
	/**
	 * Constructs a transform object with specified x and y coordinates and a center of mass
	 * @param x Coordinate X as as a double.
	 * @param y Coordinate Y as a double.

	 * @param direction The direction of the Actor.
	 */
	
	Transform(double x, double y, double direction) {
		super(x,y);

		x_pos = x;
		y_pos = y;
		direction = dir;
	}
	
	
	/**
	 * Moves the Transform to the specified position.
	 * NOTE: This currently uses a while loop and needs to be resourced off to a separate thread.
	 * 		- The speed is currently inverted.
	 * @param target The position in space to move the Transform to.
	 * @param speed The updates per frame?
	 * @param delta The allowed precision error between the actor's old position and new position.
	 * @throws InterruptedException 
	 */
	
	public boolean moveTo(Cartesian2D target, double speed, float delta) throws InterruptedException {
		
		double x_cur_pos = this.getX();
		double y_cur_pos = this.getY();
		double x_targ_pos = target.getX();
		double y_targ_pos = target.getY();

		double current_pos = Math.hypot(this.getX(), this.getY());
		double target_pos = Math.hypot(x_targ_pos, y_targ_pos);
		double x_diff = x_targ_pos - x_cur_pos;
		double y_diff = y_targ_pos - y_cur_pos;
		double x_speed = x_diff / speed;
		double y_speed = y_diff / speed;
		double pos_diff = target_pos;
		double pos_diff_x = x_targ_pos;
		double pos_diff_y = y_targ_pos;		
			
			if (speed < 1)
				speed = 1;
			
			do {
					
				//if (pos_diff_x > delta) {	//If X has been reached, stop updating it.
					x_cur_pos = this.getX();
					this.setX(x_cur_pos + x_speed);
				//}
				
				//if (pos_diff_y > delta) {		//if Y has been reached, stop updating it.
					this.setY(y_cur_pos + y_speed);
					y_cur_pos = this.getY();
				//}
				
				current_pos = Math.hypot(this.getX(), this.getY());	
	
				pos_diff = Math.abs(target_pos - current_pos);
				pos_diff_x = Math.abs(x_targ_pos - x_cur_pos);
				pos_diff_y = Math.abs(y_targ_pos - y_cur_pos);
				System.out.printf("x diff: %f. y diff: %f \n", pos_diff_x,pos_diff_y);
				System.out.println("x_pos " + this.getX() + " y_pos " + this.getY() + " last_pos: " + target_pos + "delta: " + delta + "pos_diff " + pos_diff);
				Thread.sleep(30);
				//Thread.yield();
				
			} while (pos_diff > delta);
			return true;

		}
	
	/**
	 * Sets the direction of the transform.
	 * @param direction The direction of the transform in radians.
	 */

	public void setDirection(double direction) {
		
		this.dir = direction;
		
	}
	
	/**
	 * Retrieves the direction in radians.
	 * @return Return the direction in radians.
	 */
	public double getDirection() {
		return dir;
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
	
	
	/** 
	 * Sets the transform's x coordinate.
	 * @param x X coordinate in space.
	 */
	
	public void setX(double x) {
		x_pos = x;
	}
	
	
	/** 
	 * Sets the transform's y coordinate.
	 * @param y Y coordinate in space.
	 */
	
	public void setY(double y) {
		y_pos = y;
	}

	/**
	 * The non-converted X coordinate. Use for easier understanding of the coordinate system. 
	 * @return Returns the non-converted X coordinate. Used for easier understanding of the coordinate system.
	 */
	public double getX() {
		return x_pos;
	}
	/***
	 * The non-converted Y coordinate. Use for easier understanding of the coordinate system. 
	 * @return Returns the non-converted Y coordinate. Used for easier understanding of the coordinate system.
	 */
	public double getY() {
		return y_pos;
	}
}
