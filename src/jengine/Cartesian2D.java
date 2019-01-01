package jengine;

public class Cartesian2D extends Component {

	protected double x = 0;
	protected double y = 0;
	private static final String default_name = "Cartesian2D";
	
	/**
	 * Default constructor. Sets x and y axis to 0,0 (debug area)
	 */

	public Cartesian2D() {
		super(default_name);
		x = 0;
		y = 0;
	}
	
	
	/**
	 * Constructs a transformation with the specified coordinates
	 * @param x The x position in the Cartesian coordinate.
	 * @param y The y position in the Cartesian coordinate.
	 */
	
	public Cartesian2D(double x, double y) {
		super(default_name);
		this.x = x;
		this.y = y;
	}
	/**
	 * Constructs a Cartesian 2D with specified coordinates and name.
	 * @param x The x position in the 2D plane
	 * @param y The y position in the 2D plane
	 * @param name The name of the component.
	 */
	public Cartesian2D(double x, double y, String name) {
		super(name);
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Constructs a Cartesian 2D with specified coordinates, mentor and name.
	 * @param x The X position in the 2D plane.
	 * @param y The Y position in the 2D plane.
	 * @param Mentor The Mentor of the component.
	 * @param name The name of the component.
	 */
	public Cartesian2D(double x, double y, GameObject Mentor, String name) {
		super(Mentor, name);
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Constructs a Cartesian2D component with a mentor and x-y position.
	 * @param x X position of the Cartesian 2D coordinate
	 * @param y Y position of the Cartesian 2D coordinate
	 * @param mentor The Cartesian2D's Mentor.
	 */
	
	public Cartesian2D(double x, double y, GameObject mentor) {
		super(mentor, default_name);
		this.x = x;
		this.y = y;
		
	}
	
	/**
	 * Constructs a  Cartesian2D with a mentor.
	 * @param mentor The mentor for the Cartesian 2D
	 */
	public Cartesian2D(GameObject mentor) {
		super(mentor, default_name);
	}
	
	public Cartesian2D(GameObject mentor, String name) {
		super(mentor,name);
		
	}
	/**
	 * Alters the x position in the Cartesian coordinate specified by the parameter.
	 * @param x Defines the new x position
	 */
	
	public void setX(double x) {
		this.x = x;
	}
	

	/**
	 * Alters the x position in the Cartesian coordinate specified by the parameter.
	 * @param x Defines the new x position
	 */
	
	public void setY(double y) {
		
		this.y = y;
	}
	
	
	/** 
	 * Accesses the x coordinate of the Cartesian object.
	 * @return Returns the x Cartesian coordinate
	 */
	
	public double getX() {
		return x;
	}
	
	
	/**
	 * Accesses the y coordinate of the Cartesian object
	 * @return Returns the y Cartesian coordinate.
	 */
	
	public double getY() {
		return y;
	}
}
