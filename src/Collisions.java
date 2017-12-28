
//TODO: Create a temp position, calculate velocity and if it has passed it within 1 frame.
public class Collisions {


	private static int[] ycur = new int[2];
	private static int[] xcur = new int[2];

	public boolean findCollision (SnakeObject snO, Snake sn, int[] velocity, int[] pos1, int[] pos2) {
		
		xcur[0] = pos1[0];
		xcur[1] = pos2[0];
		ycur[0] = pos1[1];
		ycur[1] = pos2[1];
		
				if ((ycur[0]== ycur[1])&&(xcur[0]== xcur[1])) {
					return true;
					} else {
						return false;
					}
	}

	/* LEGACY COLLISION (Uses mass) 
	 * 	public int[] newX2 (Ball obj1, Paddle obj2, int velocityx1, int velocityx2, int mass1, int mass2, int x1, int x2, int[] width) {
		double tempnewx;
		double diff = velocityx1 - velocityx2;
		double tempdiff = Math.round(diff);
		double tempvelocity2 = Math.round(velocityx2);
		double tempvelocity1 = Math.round(velocityx1);
		double a = mass1 * velocityx1;
		double b = mass2 * velocityx2;
		
		newx2speed[0] = velocityx1;
		newx2speed[1] = velocityx2;
		if ((x1 + (width[0])) > ((x2 - (width[1]))) && ((x1 - (width[0])) < (x2 + (width[1])))) {
			System.out.println("Collided!");
				double newy = ((a + b) - mass2 * diff)/ (mass1 + mass2);
			tempnewx = Math.round(newy);
				newx2speed[0] = (int)tempnewx;
				newx2speed[1] = (int) (newy + (int) tempdiff);
				System.out.println("Speed 1x: " + newx2speed[0] + " Speed2: " + newx2speed[1] );
				hasCollide = true;
				//System.out.println(newy2speed);
		}
			obj1.setSpeedX(newx2speed[0]);
			return newx2speed;
		
	} */
	 
	
}
