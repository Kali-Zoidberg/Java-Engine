package jengine;

import chowshapes.Rectangle;

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
	
	public static boolean isInRange(double val, double a, double b)
	{
		return (val <= a && val >= b);
		
	}
	
	public static void testNormals()
	{
		Rectangle test_shape = new Rectangle(0,0,5,6);
		Vector2D test_normals[] = test_shape.getNormals();
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
		return (angle > 0 ? angle - (Math.ceil((angle/360.0)) * 360.0): angle + ((Math.abs(Math.ceil(angle/360.0)) + 1) * 360.0));
	}
	
	public static void test_functions()
	{
		test_dbToLinear();
		test_LinearFracToDB();
	}
}

