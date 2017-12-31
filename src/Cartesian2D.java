
public class Cartesian2D {

	private int x_pos = 0;
	private int y_pos = 0;
	
	/**
	 * Default constructor. Sets x and y axis to 0,0 (debug area)
	 */

	Cartesian2D() {

	}
	
	
	/**
	 * Constructs a transformation with the specified coordinates
	 * @param x The x position in the Cartesian coordinate.
	 * @param y The y position in the Cartesian coordinate.
	 */
	
	Cartesian2D(int x, int y) {
		x_pos = x;
		y_pos = y;
	}
	
	
	/**
	 * Alters the x position in the Cartesian coordinate specified by the parameter.
	 * @param x Defines the new x position
	 */
	
	public void setPosition_X(int x) {
		x_pos = x;
	}
	

	/**
	 * Alters the x position in the Cartesian coordinate specified by the parameter.
	 * @param x Defines the new x position
	 */
	
	public void setPosition_Y(int y) {
		
		y_pos = y;
	}
	
	
	/** 
	 * Accesses the x coordinate of the Cartesian object.
	 * @return Returns the x Cartesian coordinate
	 */
	
	public int getPosition_X() {
		return x_pos;
	}
	
	
	/**
	 * Accesses the y coordinate of the Cartesian object
	 * @return Returns the y Cartesian coordinate.
	 */
	
	public int getPosition_Y() {
		return y_pos;
	}
}
