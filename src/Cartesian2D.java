
public class Cartesian2D extends Component {

	private double x_pos = 0;
	private double y_pos = 0;

	/**
	 * Default constructor. Sets x and y axis to 0,0 (debug area)
	 */

	Cartesian2D() {
		
		x_pos = 0;
		y_pos = 0;
	}
	
	
	/**
	 * Constructs a transformation with the specified coordinates
	 * @param x The x position in the Cartesian coordinate.
	 * @param y The y position in the Cartesian coordinate.
	 */
	
	Cartesian2D(double x, double y) {
		x_pos = x;
		y_pos = y;
	}
	/**
	 * Constructs a Cartesian2D component with a mentor and x-y position.
	 * @param x X position of the Cartesian 2D coordinate
	 * @param y Y position of the Cartesian 2D coordinate
	 * @param mentor The Cartesian2D's Mentor.
	 */
	Cartesian2D(GameObject mentor, double x, double y) {
		super(mentor);
		x_pos = x;
		y_pos = y;
		
	}
	/**
	 * Constructs a  Cartesian2D with a mentor.
	 * @param mentor The mentor for the Cartesian 2D
	 */
	Cartesian2D(GameObject mentor) {
		super(mentor);
	}
	
	/**
	 * Alters the x position in the Cartesian coordinate specified by the parameter.
	 * @param x Defines the new x position
	 */
	
	public void setX(double x) {
		x_pos = x;
	}
	

	/**
	 * Alters the x position in the Cartesian coordinate specified by the parameter.
	 * @param x Defines the new x position
	 */
	
	public void setY(double y) {
		
		y_pos = y;
	}
	
	
	/** 
	 * Accesses the x coordinate of the Cartesian object.
	 * @return Returns the x Cartesian coordinate
	 */
	
	public double getX() {
		return x_pos;
	}
	
	
	/**
	 * Accesses the y coordinate of the Cartesian object
	 * @return Returns the y Cartesian coordinate.
	 */
	
	public double getY() {
		return y_pos;
	}
}
