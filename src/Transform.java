
public class Transform extends Cartesian2D {
	
	private double x_convert_pos = 0;
	private double y_convert_pos = 0;
	private double x_unconvert_pos = 0;
	private double y_unconvert_pos = 0;
	private double x_com = 5;
	private double y_com = 5;
	
	private static int screen_width = Render.getScreenWidth();
	private static int screen_height = Render.getScreenHeight();
	private static int screen_avg_width = screen_width / 2;
	private static int screen_avg_height = screen_height / 2;
	
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
		super(coordinate.getX(),coordinate.getY());
	}
	
	
	/**
	 * Constructs a transform object with the specified x and y coordinate.
	 * @param x  Coordinate X as a double.
	 * @param y  Coordinate Y as a double.
	 */
	
	Transform(double x, double y) {
		super(x + screen_avg_width,y+screen_avg_height);
	}
	
	/**
	 * Constructs a transform object with specified x and y coordinates and a center of mass
	 * @param x Coordinate X as as a double.
	 * @param y Coordinate Y as a double.
	 * @param x_center X center of mass as a double.
	 * @param y_center Y center of mass as a double.
	 */
	
	Transform(double x, double y, double x_center, double y_center) {
		super(x - x_center + screen_avg_width,y - y_center + screen_avg_height);
		
		x_unconvert_pos = x;
		y_unconvert_pos = y;
		x_com = x_center;
		y_com = y_center;
		
		//x_convert_pos = x - x_center + screen_avg_width;
		x_convert_pos = x;
		//y_convert_pos = y - y_center + screen_avg_height;
		y_convert_pos = y;
	}
	
	/**
	 * Constructs a transform object with specified Cartesian2D coordinate and center of mass.
	 * @param coordinate The coordinate of the object.
	 * @param x_center X center of mass as a double.
	 * @param y_center Y center of mass as a double.
	 */
	
	Transform(Cartesian2D coordinate, double x_center, double y_center) {
		super(coordinate.getX() - x_center + screen_avg_width, coordinate.getY() - y_center + screen_avg_width);
		
		x_unconvert_pos = coordinate.getX();
		y_unconvert_pos = coordinate.getY();
		x_com = x_center;
		y_com = y_center;
		
		//x_convert_pos = x_unconvert_pos - x_center + screen_avg_width;
		x_convert_pos = x_unconvert_pos;
		//y_convert_pos = y_unconvert_pos - y_center + screen_avg_height;
		y_convert_pos = y_unconvert_pos;
	}
	
	
	/**
	 * Sets the transformation's velocity with a 2D vector.
	 * @param velocity The velocity of the transform as a 2D vector.
	 */
	 
	public void setVelocity(Vector2D velocity) {
		veloc = velocity;
	}
	
	/**
	 * Moves the Transform to the specified position.
	 * NOTE: This currently uses a while loop and should probably be resourced off to a thread.
	 * @param target The position in space to move the Transform to.
	 * @param speed  The speed at which to move the Transform. 
	 */
	
	public void moveTo(Cartesian2D target, double speed, float delta) {
		double x_cur_pos = this.getConvertX();
		double y_cur_pos = this.getConvertY();
		double x_targ_pos = target.getX();
		double y_targ_pos = target.getY();

		double current_pos = Math.hypot(this.getX(), this.getY());
		double target_pos = Math.hypot(x_targ_pos, y_targ_pos);
		double x_diff = target.getX() - x_cur_pos;
		double y_diff = target.getY() - y_cur_pos;
		double x_speed = x_diff / speed;
		double y_speed = y_diff / speed;
		double pos_diff = target_pos;
		double pos_diff_x = x_targ_pos;
		double pos_diff_y = y_targ_pos;
		//int frame_rate = Render.getFrameRate();
		
		
		do {
				
			if (pos_diff_x > delta) {	//If X has been reached, stop updating it.
				x_cur_pos = this.getConvertX();
				this.setX(x_cur_pos + x_speed);
			}
			
			if (pos_diff_y > delta) {		//if Y has been reached, stop updating it.
				this.setY(y_cur_pos + y_speed);
				y_cur_pos = this.getConvertY();
			}
			
			current_pos = Math.hypot(this.getConvertX(), this.getConvertY());	

			pos_diff = Math.abs(target_pos - current_pos);
			pos_diff_x = Math.abs(x_targ_pos - x_cur_pos);
			pos_diff_y = Math.abs(y_targ_pos - y_cur_pos);
			System.out.printf("x diff: %f. y diff: %f \n", pos_diff_x,pos_diff_y);
			System.out.println("x_pos " + this.getX() + " y_pos " + this.getY() + " last_pos: " + target_pos + "delta: " + delta + "pos_diff " + pos_diff);

			//Thread.yield();
			System.out.println(pos_diff);
		} while (pos_diff > delta);
		
	}
	
	/**
	 * Returns the velocity as a 2D vector.
	 * @return Returns the velocity.
	 */
	
	public Vector2D getVelocity() {
		return veloc;
	}
	
	
	/**
	 * Sets the Transform's x center of mass.
	 * @param x_center The position of the center of mass.
	 */
	
	public void setXCOM(double x_center) {
		x_com = x_center;
	}
	
	
	/**
	 * Sets the Transform's y center of mass.
	 * @param y_center The position of the center of mass.
	 */
	
	public void setYCOM(double y_center) {
		y_com = y_center;
	}
	
	/** 
	 * Sets the transform's x coordinate.
	 * @param x X coordinate in space.
	 */
	
	public void setX(double x) {
		x_unconvert_pos = x;
		x_convert_pos = x ;
	}
	
	
	/** 
	 * Sets the transform's y coordinate.
	 * @param y Y coordinate in space.
	 */
	
	public void setY(double y) {
		y_unconvert_pos = y;
		y_convert_pos = y ;
	}
	
	
	/**
	 * Returns the converted X coordinate used for rendering the actor.
	 * @return Returns the converted X coordinate used for rendering the actor.
	 */
	public double getConvertX() {
		return x_convert_pos;
	}
	
	/**
	 * Returns the converted Y coordinate used for rendering the actor.
	 * @return Returns the converted Y coordinate used for rednering the actor.
	 */
	public double getConvertY() {
		return y_convert_pos;
	}
	/**
	 * The non-converted X coordinate. Use for easier understanding of the coordinate system. 
	 * @return Returns the non-converted X coordinate. Used for easier understanding of the coordinate system.
	 */
	public double getX() {
		return x_unconvert_pos;
	}
	/***
	 * The non-converted Y coordinate. Use for easier understanding of the coordinate system. 
	 * @return Returns the non-converted Y coordinate. Used for easier understanding of the coordinate system.
	 */
	public double getY() {
		return y_unconvert_pos;
	}
}
