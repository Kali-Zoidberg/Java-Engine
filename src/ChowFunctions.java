

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
		Vector2D test_normals[] = getNormals(test_shape);
		for (Vector2D normal: test_normals)
		{
			System.out.println(normal.toString());
		}
	}
	
	/**
	 * Calculates the normals of a shape and returns them as an array based on the nuber of segments of the shape.
	 * @param shape
	 * @return
	 */
	
	public static Vector2D[] getNormals(Shape shape)
	{
		Cartesian2D[] points = shape.getPoints();
		int pointsLen = shape.getPoints().length;
		Vector2D normals[] = new Vector2D[pointsLen];
		for (int i = 0; i < pointsLen - 1; ++i)
		{
			Vector2D vectorSegment = new Vector2D(points[i+1].getX() - points[i].getX(),
													points[i+1].getY() - points[i].getY());
			
			System.out.println("Our vector segment: " + points[i].getX());
			System.out.println("points[i + 1]: " + points[i+1].getX());
			
			double normalX = vectorSegment.getX() * Math.cos(vectorSegment.getDirection()) - vectorSegment.getY() * Math.sin(vectorSegment.getDirection()); 
			double normalY = vectorSegment.getX() * Math.sin(vectorSegment.getDirection()) + vectorSegment.getY() * Math.cos(vectorSegment.getDirection());
			normals[i] = new Vector2D(normalX, normalY);
		}
		
		Vector2D vectorSegment = new Vector2D(points[0].getX() - points[pointsLen - 1].getX(),
				points[0].getY() - points[pointsLen - 1].getY());
		double normalX = vectorSegment.getX() * Math.cos(vectorSegment.getDirection()) - vectorSegment.getY() * Math.sin(vectorSegment.getDirection()); 
		double normalY = vectorSegment.getX() * Math.sin(vectorSegment.getDirection()) + vectorSegment.getY() * Math.cos(vectorSegment.getDirection());
		normals[pointsLen - 1] = new Vector2D(normalX, normalY);

		return normals;
	}
	
	public static void test_functions()
	{
		test_dbToLinear();
		test_LinearFracToDB();
	}
}

class Rectangle extends Shape {
	
	private double width, height;
	private Cartesian2D points[] = new Cartesian2D[4];
	
	Rectangle(double x, double y, double width, double height)
	{
		super(x,y);
		this.width = width;
		this.height = height;
		genPoints();
	}
	private void genPoints()
	{
		points[3] = new Cartesian2D(x,y);
		points[0] = new Cartesian2D(x + width, y);
		points[2] = new Cartesian2D(x, y + height);
		points[1] = new Cartesian2D(x + width, y + height);
		
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

	@Override
	public Cartesian2D[] getPoints() {
		return points;
	}
}
