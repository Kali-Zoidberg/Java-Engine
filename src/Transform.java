
public class Transform extends Cartesian2D {

	private int width = 10;
	private int height = 10;
	
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
		
		x_convert_pos = x - x_center + screen_avg_width;
		y_convert_pos = y - y_center + screen_avg_height;

		
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
		
		x_convert_pos = x_unconvert_pos - x_center + screen_avg_width;
		y_convert_pos = y_unconvert_pos - y_center + screen_avg_height;
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
		x_convert_pos = x - x_com + screen_avg_width;
	}
	
	
	/** 
	 * Sets the transform's y coordinate.
	 * @param y Y coordinate in space.
	 */
	
	public void setY(double y) {
		y_unconvert_pos = y;
		y_convert_pos = y - y_com + screen_avg_height;
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
