package jengine;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Set;

/**
 * 
 * @author Nicholas Chow
 *	Credits to Kah Shiu Chong for his tutorial on the Separating Axis Theorem tutorial and psuedocode.
 *	You may find the tutorial here: https://gamedevelopment.tutsplus.com/tutorials/collision-detection-using-the-separating-axis-theorem--gamedev-169
 */
public class Physics {

	
	public static Hashtable <String, Boolean> checkedActorPairsHash = new Hashtable<String, Boolean>();
	
	/**
	 * Calculates the min and max points of a shape by projecting them onto an axis
	 * @param shape The shape to calculate the projection
	 * @return Returns the min and max points of the shape in the form a Vector2D array.
	 */
	public static double[] minMaxProj(Vector2D[] normals, Vector2D axis)
	{
		
		int normLen = normals.length;
		Vector2D axisUnit = axis.unitVector();
		Vector2D minPointVect = new Vector2D(normals[0].getX(), normals[0].getY());
		Vector2D maxPointVect = new Vector2D(normals[0].getX(), normals[0].getY());
		
		//calc initial min and max dot products
		double maxDotProd = maxPointVect.dot(axisUnit);
		double minDotProd = minPointVect.dot(axisUnit);
		
		//start at index 1 because the min/max index is first 0.
		for (int i = 1; i < normLen; ++i)
		{
			double curDotProd = normals[i].dot(axisUnit);
			
			
			//find minimum point
			if(curDotProd < minDotProd )
			{
				minPointVect = normals[i];
				minDotProd = curDotProd;
			} //find max dot prod
			else if(curDotProd > maxDotProd)
			{
				maxPointVect = normals[i];
				maxDotProd = curDotProd;
			}
		}
		double minAndMax[] = {minDotProd, maxDotProd};
		return minAndMax;
	}
	
	public static boolean projOverLap(double[] minMaxA, double minMaxB[])
	{
		return (minMaxA[0] > minMaxB[1] || minMaxB[0] > minMaxA[1]);
	}
	/**
	 * Calculates the final velocity, of the first object, in an elastic collision
	 * @param mass1 The mass of the first object
	 * @param velocity1 The velocity of the first object
	 * @param mass2 The mass of the second object
	 * @param velocity2 The velocity of the second object
	 * @return Returns the final velocity of the first object.
	 */
	public static double[] elasticCollision1D(double mass1, double velocity1, double mass2, double velocity2)
	{
		//vf1 = [(m1 - m2)·vi1 + 2 m2·vi2]/(m1 + m2)
		//vf2 = [2 m1·vi1 - (m1 - m2)·vi2]/(m1 + m2)
		double v1s = ((mass1 - mass2)*velocity1 + (2*mass2*velocity2)) / (mass1+mass2);
		double v2s = (velocity2 * (mass2 - mass1) + (2 * mass1 * velocity1))/(mass1+mass2);
		double retVeloc[] = {v1s, v2s};
		//add initial vector with second vector to get final
		return retVeloc;
	}
	/*
	public static Vector2D[] elasticCollision(double mass1, Vector2D velocity1, double mass2, Vector2D velocity2)
	{
		//vf1 = [(m1 - m2)·vi1 + 2 m2·vi2]/(m1 + m2)
		//vf2 = [2 m1·vi1 - (m1 - m2)·vi2]/(m1 + m2)
		double v1p = velocity1.getMagnitude() * Math.cos(velocity1.getDirection());
		double v2p = velocity2.getMagnitude() * Math.cos(velocity2.getDirection());
		double v1s = ((mass1 - mass2)* v1p)+ (2*mass2*v2p) / (mass1+mass2);
		double v2s = ((2 * mass1 * v1p) - (mass1 - mass2) * v2p)/(mass1+mass2);
		double retVeloc[] = {v1s, v2s};
		//add initial vector with second vector to get final
		return retVeloc;
	}
	*/
	public static Vector2D[] elasticCollision2D(double m1, RigidBody rigidA, double m2, RigidBody rigidB)
	{
		//find a normal vector. find the centers between the coordinates and create a vector
		Cartesian2D rigidACent = rigidA.collisionShape.getCenter();
		Cartesian2D rigidBCent = rigidB.collisionShape.getCenter();
		
		//Find the mutual normal vector by getting the vector between the centers of the shapes.
		Vector2D mutualNormVect = new Vector2D(rigidBCent.getX() - rigidACent.getX(), rigidBCent.getY() - rigidACent.getY());
		//normalize the vector
		Vector2D mutualNormUnitVect = mutualNormVect.unitVector();
		//find the tangent vector by finding the left normal
		Vector2D mutualTanVect = mutualNormUnitVect.normL();
		Vector2D v1 = rigidA.getVelocity();
		Vector2D v2 = rigidB.getVelocity();
		
		//project the respective velocity vectors onto the mutualNormal and mutualTangent unit vectors.
		double v1n = v1.dot2(mutualNormUnitVect);
		double v1t = v1.dot2(mutualTanVect);
		double v2n = v2.dot2(mutualNormUnitVect);
		double v2t = v2.dot2(mutualTanVect);
		//Solve for the scalars of the normal velocities after the collision
		double vnPrimes[] = elasticCollision1D(m1, v1n, m2, v2n);
		//Find the new normal velocities by multiplying the scalars of vnPrimes by the mutual Normal unit vector
		Vector2D v1NormFinal = mutualNormUnitVect.mult(vnPrimes[0]);
		Vector2D v2NormFinal = mutualNormUnitVect.mult(vnPrimes[1]);
		
		//the tangential velocities do not change after collision so just use the previous calculated dot product.
		Vector2D v1TanFinal = mutualTanVect.mult(v1t);
		Vector2D v2TanFinal = mutualTanVect.mult(v2t);
		
		//find the vinal velocity by adding the vectors of the new normals, and the new tangents (which aren't really new).
		
		Vector2D velFinals[] = {v1NormFinal.add(v1TanFinal), v2NormFinal.add(v2TanFinal)};
		System.out.println(velFinals[0].toString() + "\n" + velFinals[1].toString());
		return velFinals;
		
		
	}
	public static Vector2D[] elasticCollision2D(double m1, Vector2D v1, double m2, Vector2D v2)
	{

		double secondVelX[] = elasticCollision1D(m1, v1.getX(), m2, v2.getX());
		double secondVelY[] = elasticCollision1D(m1, v1.getY(), m2, v2.getY());
		Vector2D secondVelA = new Vector2D(secondVelX[0], secondVelY[0]);
		Vector2D secondVelB = new Vector2D(secondVelX[1], secondVelY[1]);

		
		Vector2D retVectors[] = {secondVelA, secondVelB};
		return retVectors;
	}

	public static void update()
	{
		//clear checkd actor pair hash
		checkedActorPairsHash.clear();
		//first convert all the keys to a set 
		Hashtable<String, GameObject> gameObjTable = GameWorld.game_obj_table; 
		Set<String> gameObjKeys = gameObjTable.keySet();
		gameObjKeys.removeIf(k -> !(gameObjTable.get(k) instanceof Actor));
		String actorKeys[] = new String[gameObjKeys.size()];
		int index = 0;
		//convert set to array in order to use checking algoirthm and also udpate their bounding box while we're at it.
		for (String s: gameObjKeys)
		{
			//update all bounding boxes here before calculating the collisions
			((Actor) gameObjTable.get(s)).rigidbody.updateBoundingBox();
			//Convert to Array
			actorKeys[index++] = s;
		}
		//Do Actor collision calculations
		//O(N * (N-i)) Still resembles O(N^2) but it get better
		for (int i = 0; i < index - 1; ++i)
			for (int j = i + 1; j < index; ++j)
			{
				//Basically get the current actor, calc collision with a reference to the current actor and the next actor
				((Actor)gameObjTable.get(actorKeys[i])).rigidbody.checkCollision((Actor) gameObjTable.get(actorKeys[j]));
				
			}
	}
	
	
}
