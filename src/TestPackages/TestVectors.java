package TestPackages;
import jengine.Vector2D;
public class TestVectors {
	public static void testNormalize()
	{
		Vector2D test_a = new Vector2D(3,5);
		
		System.out.println("test_a: " + test_a.toString() + "\nnormalized" + test_a.normL().toString());
		
	}
	
	public static void testUnitVector()
	{
		Vector2D[] tests = { new Vector2D(1,-1), new Vector2D(1,1), new Vector2D(0,0),
				new Vector2D(10,15), new Vector2D(100,100), new Vector2D(1000000, 100000),
				new Vector2D(12345, 98765)};
		for (int i = 0 ; i < tests.length; ++i)
		{
			double unitVectorMagn = tests[i].unitVector().getMagnitude();
			if(!(unitVectorMagn >= 0 && unitVectorMagn < 1.1))
				System.out.printf("Unit vector test is incorrect: \n %s\n magn is: %f\n", tests[i].toString(), unitVectorMagn);
	
		}
	}
		
}

