

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
	public static void test_functions()
	{
		test_dbToLinear();
		test_LinearFracToDB();
	}
}
