package jengine;


/**
 * A generic class for commonly used functions across classes.
 * @author Chow
 *
 */
public class ChowFunctions {
	
	/**
	 * Returns a converted decibel amount from a specified linear fraction.
	 * @param fraction The fractional decibel amount you wish to convert.
	 * @return Returns a float type decibel amount.
	 */
	public static float LinearFracToDB(float fraction) 
	{
		fraction = volFracInBounds(fraction);
		
		return (float) (Math.log10(fraction) * 20); 
	}
	
	/**
	 * Converts decibel amount to a linear fraction.
	 * @param db The decibel amount to convert to a linear fraction
	 * @return Returns the linear fraction of the specified decibel amount.
	 */
	public static float dbToLinearFrac(float db)
	{
		return (float) Math.pow(10, db/20);
	}
	
	public static void test_dbToLinear()
	{
		System.out.println("DB: 0 LinearFrac: " + dbToLinearFrac(0));
		for (int i = 1; i < 20; ++i)
		{
			float test_db = -86f / i;
			System.out.printf("DB: %f LinearFrac: %f \n", test_db, dbToLinearFrac(test_db));
			System.out.printf("LinearFrac: %f DB: %f \n", dbToLinearFrac(test_db), test_db);
		}
	}
	
	/**
	 * Determines if a volume fraction is within bounds.
	 * @param volFrac The volume fraction to check
	 * @return Returns 0.0005f if the volFrac is <= 0. Otherwise
	 * it returns the original volFrac volume.
	 */
	public static float volFracInBounds(float volFrac)
	{
		return (volFrac <= 0 ? 0.0005f : volFrac);
	}
	public static void test_LinearFracToDB()
	{
		System.out.println("LinearFrac: 0 DB: " + LinearFracToDB(0));
		for (int i = 1; i < 10; ++i)
		{
			float test_frac =  1f / i;
			System.out.printf("LinearFrac: %f DB: %f\n", test_frac, LinearFracToDB(test_frac));
		}
	}
	
	public static void testNormals()
	{
		Rectangle test_shape = new Rectangle(0,0,5,6);
		Vector2D test_normals[] = Physics.getNormals(test_shape);
		for (Vector2D normal: test_normals)
		{
			System.out.println(normal.toString());
		}
	}
	/**
	 * gets the Coterminal angle.
	 * @param angle The angle to calculate the coterminal angle with
	 * @return Returns the coterminal angle. it adds 360 if it is less than 0 and subtracts 360 if it is greater than 0
	 */
	public static double getCoterminalAngle(double angle)
	{
		if(angle < 0)
			System.out.println("Math: " + Math.ceil(angle/360.0f));
		return (angle > 0 ? angle - (Math.ceil((angle/360.0)) * 360.0): angle + ((Math.abs(Math.ceil(angle/360.0)) + 1) * 360.0));
	}
	
	public static void test_functions()
	{
		test_dbToLinear();
		test_LinearFracToDB();
	}
}

class Rectangle extends Shape {
	
	private Cartesian2D points[] = new Cartesian2D[5];
	
	Rectangle(double x, double y, double width, double height)
	{
		super(x,y,width,height);
		genPoints();
		printPoints();
	}
	/**
	 * 4------1
	 * |      |
	 * |   0  |
	 * |	  |
	 * 3------2
	 */
	private void genPoints()
	{
		points[0] = getCenter();
		points[1] = new Cartesian2D(x + width, y);
		points[2] = new Cartesian2D(x + width, y + height);
		points[3] = new Cartesian2D(x,y+height);
		points[4] = new Cartesian2D(x,y);

	//	printPoints();
		
	}
	public void setX(double x)
	{
		super.setX(x);
		genPoints();
	}
	public void setY(double y)
	{
		super.setY(y);
		genPoints();
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
		genPoints();
	}
	public double getHeight() {
		return height;
		
	}
	public void setHeight(double height) {
		this.height = height;
		genPoints();
	}
	public Cartesian2D getCenter()
	{
		return new Cartesian2D( (this.getX() + this.getWidth())/2, (this.getY() + this.getHeight()) / 2);
	}
	@Override
	public Cartesian2D[] getPoints() {
		return points;
	}
	public Vector2D[] getVectors()
	{
		//create an array of size points -1 because initioal point is center
		Vector2D[] retVectors = new Vector2D[points.length - 1];
		for (int i = 1; i < points.length; ++i)
		{
			//start at index one because the first point is the center and we do not want its vector
			retVectors[i - 1] = new Vector2D(points[i].getX(), points[i].getY());
		}
		return retVectors;
	}
	
	public void printPoints()
	{
		System.out.println("\n*****\n");
		for (int i = 0; i< points.length; ++i)
			System.out.printf("Point[%d]. X: %f Y: %f \n", i, points[i].getX(), points[i].getY());
	}
}
