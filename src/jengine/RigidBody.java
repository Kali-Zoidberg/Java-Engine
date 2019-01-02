package jengine;

import chowshapes.Circle;
import chowshapes.Rectangle;
import chowshapes.Shape;

/**
 * @author Chow
 *
 */

public class RigidBody extends Transform {
	
	protected double mass = 1;
	Actor Mentor = (Actor) super.Mentor;
	public Shape collisionShape = null;
	private boolean isMovable = true;
	private boolean has_collision = true;
	private boolean hasInteresected = false; 
	enum CollisionEnum
	{
		ISOLATED, PENETRAaTED, TOUCHED;
	}
	
	/**
	 * Constructs a RigidBody object with 'debug' position and sprite.
	 * The default 'Sprite' is 10x10 box with the color Pink
	 * @param The String name of the RigidBody. Used for searching through the Array list of actors.
	 */
	
	public RigidBody(Actor mentor, String name) {
		super.Mentor = mentor;
		Mentor.transform = this;
		
	}
	
	/**
	 * Constructs a RigidBody with specified x and y positions.
	 * @param x The X position of the rigid body
	 * @param y The y position of the rigid body.
	 * @param mentor The mentor of the RigidBody
	 * @param name The name of the RigidBody.
	 */
	public RigidBody(double x, double y, Actor mentor, String name) {
		super(x,y, mentor, name);
		Mentor.transform = this;
		collisionShape = new Rectangle(x,y, mentor.getWidth(), mentor.getHeight());
	}
	/**
	 * Constructs a RigidBody using a transform.
	 * @param transform The transform to use in the RigidBody.
	 * @param mentor The mentor of the RigidBody.
	 * @param name The name of the RigidBody.
	 */
	public RigidBody(Transform transform, Actor mentor, String name) {
		super(transform.getX(), transform.getY(), mentor, name);
		Mentor.transform = this;
		collisionShape = new Rectangle(transform.getX(),transform.getY(), mentor.getWidth(), mentor.getHeight());

	}
	
	/**
	 * Constructs a RigidBody using a transform.
	 * @param transform The transform to use in the RigidBody.
	 * @param mentor The mentor of the RigidBody.
	 * @param shape the collision shape
	 * @param name The name of the RigidBody.
	 */
	public RigidBody(Transform transform, Shape collisionShape, Actor mentor, String name) {
		super(transform.getX(), transform.getY(), mentor, name);
		Mentor.transform = this;
		this.collisionShape = collisionShape;

	}
	
	/**
	 * Constructs a RigidBody object with a transform, color, width, and height.
	 * The actor will appear as a box on screen.
	 * @param transform The Transform of the RigidBody. Through which the velocity and position are manipulated
	 * and retrieved.
	 * @param name The String name of the RigidBody. Used for searching through the Array list of actors.
	 * @param color The color of the RigidBody. 
	 * @param width The width of the RigidBody and box.
	 * @param height The height of the RigidBody and box.
	 */
	/*public RigidBody(Transform transform, Color color, String name, int width, int height) {
		super(transform, color, name, width, height);
	} */

	public void setCollision(boolean enable) {
		has_collision = enable;
		int actor_width = Mentor.getWidth();
		int actor_height = Mentor.getHeight();
		
		int x_min = (int) (super.getX() + 0.5);
		int x_max = (x_min + actor_width);
		int y_min = (int) (super.getY() + 0.5);
		int y_max = (y_min + actor_height);
		
		
		if (enable) {
			for (int i = x_min; i <= x_max; ++i) {
				for (int j = y_min; j <= y_max; ++j) {
					
					GameWorld.setWorldCoordinate(i, j, Mentor.getName());
				}
			}
		}
	}
	
	
	
	
	/**
	 * Checks to see if two rigidbodies have collided.
	 * @param bodyA - The First body to compare
	 * @param bodyB - The second body to compare.
	 * @return - Returns true if the two rigid bodies have collided.
	 */
	private boolean checkCollision(RigidBody bodyA, RigidBody bodyB)
	{
		//if(bodyA.Mentor.hasCollision() && bodyB.Mentor.hasCollision()) {
		
		//Initialize all x,y, width and height parameters for both objects
		double bodyA_x = bodyA.getX();
		double bodyA_y = bodyA.getY();
		double bodyA_h = bodyA.Mentor.getHeight();
		double bodyA_w = bodyA.Mentor.getWidth();
		double bodyB_x = bodyB.getX();
		double bodyB_y = bodyB.getY();
		double bodyB_h = bodyB.Mentor.getHeight();
		double bodyB_w = bodyB.Mentor.getWidth();
		if (bodyA_x < bodyB_x + bodyB_w && bodyA_x + bodyA_w > bodyB_x &&
			bodyA_y < bodyB_y + bodyB_h && bodyA_y + bodyA_h > bodyB_y)
			return true;
		else
			return false;
	
		
	}
	

	
	/**
	 * Sets the projected path for the actor with the specified maximums. So it checks from the
	 * [x_min,y_min] to [x_max,y_max] (inclusive). Since it is inclusive, you may have to adjust your bounds
	 * accordingly. (I.E. try adding 1 to your mins or maxs).
	 * @param x_min The minimum x coordinate.
	 * @param y_min The minimum y coordinate.
	 * @param y_max The maximum y coordinate
	 * @param obj_name The name of the GameObject that will exist in this space. If clearing up existing space, set to "-1"
	 * @return Returns true if the operation is successful
	 */
	public boolean setPath(int x_min, int y_min, int x_max, int y_max, String obj_name) {
		
		if (GameWorld.isInWorldCoordinateBounds(x_max, y_max) && 
				GameWorld.isInWorldCoordinateBounds(x_min, y_min)) {
			for (int i = x_min; i <= x_max; ++i) {
				for (int j = y_min; j <= y_max; ++j) {
					GameWorld.setWorldCoordinate(i, j, obj_name); //set previous spot to -1.
				}
			}
		}
		
		return true;
		
	}
	/**
	 * Checks the GameWorld's world coordinates from a range of [x_min, y_min] to [x_max, y_max]. Use this when determining the future position of an object.
	 * @param x_min The minimum Y point to check.
	 * @param y_min The minimum Y point to check.
	 * @param x_max the maximum X point to check.
	 * @param y_max The maximum Y point to check.
	 * @return Returns true if the future position of the object does not interfere with other objects.
	 */
	public boolean checkPath(int x_min, int y_min, int x_max, int y_max) {
		this.setX(x_min);
		
		return true;
	}
	public double calcProjection()
	{
		return 0.0;
	}
	
	public boolean hasCollidedCircle(Actor actorA, Actor actorB)
	{
		if (!(actorA.rigidbody.collisionShape instanceof Circle) || !(actorB.rigidbody.collisionShape instanceof Circle))
			return false;
		
		//get references to collision shapes
		Circle circleA = (Circle) actorA.rigidbody.collisionShape;
		Circle circleB = (Circle) actorB.rigidbody.collisionShape;
		
		//get radii
		
		double radiusA = circleA.getRadius();
		double radiusB = circleB.getRadius();
		
		//get centers
		Cartesian2D centerA = circleA.getCenter();
		Cartesian2D centerB = circleB.getCenter();
		
		//calc distances
		double distance = Math.hypot(Math.abs(centerA.getX() - centerB.getX()), Math.abs(centerA.getY() - centerB.getY()));
		return !(distance < radiusA + radiusB);
		
	}
	
	public boolean hasCollidedPolygonAndCircle(Actor actorA, Actor actorB)
	{
		Actor circleActor = null;
		Actor polygonActor = null;
		if(actorA.rigidbody.collisionShape instanceof Circle)
		{
			circleActor = actorA;
			polygonActor = actorB;
		}
		else if (actorB.rigidbody.collisionShape instanceof Circle)
		{
			circleActor = actorB;
			polygonActor = actorA;
		} 
		
		if (polygonActor.rigidbody.collisionShape instanceof Circle)
			return true;
		
		//should probably throw exception
		
		Cartesian2D circleCenter = circleActor.rigidbody.collisionShape.getCenter();
		double circleRadius = ((Circle) circleActor.rigidbody.collisionShape).getRadius();
		Cartesian2D polygonVectors[] = polygonActor.rigidbody.collisionShape.getPoints();
		Cartesian2D polygonCenter = polygonActor.rigidbody.collisionShape.getCenter();
		Vector2D circleVector = new Vector2D(circleCenter.getX() - polygonCenter.getX(), circleCenter.getY() - polygonCenter.getY());

		Vector2D circleNormal = circleVector.unitVector();
		
		Vector2D tempVec = new Vector2D(polygonVectors[1].getX() - polygonCenter.getX(), polygonVectors[1].getY() - polygonCenter.getY());
		double curMax = tempVec.dot(circleNormal);
		for (int i = 2; i <  polygonVectors.length; ++i)
		{
			tempVec = new Vector2D(polygonVectors[i].getX() - polygonCenter.getX(),
								polygonVectors[i].getY() - polygonCenter.getY());
			double curProd = tempVec.dot(circleNormal);
			if (curProd > curMax)
				curMax = curProd;
				
		}
		
		return ((circleVector.getMagnitude() - curMax) > circleRadius && circleVector.getMagnitude() > 0);
		
	}
	/**
	 * Calculates the distance between two actors.
	 * @param actorA The first actor
	 * @param actorB The second actor
	 * @return Returns the gap between the two actors.
	 */
	public boolean hasCollided(Actor actorA, Actor actorB)
	{
				if (actorA.rigidbody.collisionShape instanceof Circle && actorB.rigidbody.collisionShape instanceof Circle)
					return hasCollidedCircle(actorA, actorB);
				//if either one of the collision shapes belonging to the actors is a circle, then return the collision for circle and polygon method
				else if(actorA.rigidbody.collisionShape instanceof Circle || actorB.rigidbody.collisionShape instanceof Circle)
					return hasCollidedPolygonAndCircle(actorA, actorB);
				boolean isSeparated = false;
				Vector2D[] normActorA = actorA.rigidbody.collisionShape.getNormals();
				Vector2D[] normActorB = actorA.rigidbody.collisionShape.getNormals();
				
				Vector2D[] actorAShapeVects = actorA.rigidbody.collisionShape.getVectors();
				Vector2D[] actorBShapeVects = actorB.rigidbody.collisionShape.getVectors();
				for (int i = 0; i < normActorB.length; ++i)
				{
				double[] minMaxShapeA = Physics.minMaxProj(actorAShapeVects, normActorB[i]);
				double[] minMaxShapeB = Physics.minMaxProj(actorBShapeVects, normActorB[i]);
				isSeparated = (minMaxShapeA[0] > minMaxShapeB[1] || minMaxShapeB[0] > minMaxShapeA[1]);
				
				if(isSeparated)
					return true;
				}
				
				//Check the normals of the second object and project the first object onto the second normals.
				if (!isSeparated)
				{
					
					for (int i = 0; i < normActorA.length; ++i)
					{
						double[] minMaxShapeA = Physics.minMaxProj(actorAShapeVects, normActorA[i]);
						double[] minMaxShapeB = Physics.minMaxProj(actorBShapeVects, normActorA[i]);
						isSeparated = (minMaxShapeA[0] > minMaxShapeB[1] || minMaxShapeB[0] > minMaxShapeA[1]);
						if (isSeparated)
							return true;
					}
				}
				//If we reach this point, then there was at least ONE axis that had an overlapping projection
				return false;
				
			
	}
	
	public void calcCollision(Actor actorA, Actor actorB)
	{

		RigidBody rigidA = actorA.rigidbody;
		RigidBody rigidB = actorB.rigidbody;
		Vector2D actorVels[] = new Vector2D[2];
		
		//check if they are movable first
		//if movable
			//do some different physics, negate the direction? 
		
		if(rigidA.collisionShape instanceof Circle || rigidB.collisionShape instanceof Circle)
			actorVels = Physics.elasticCollision2D(rigidA.getMass(), rigidA, rigidB.getMass(), rigidB);
		else
		{
			actorVels = Physics.elasticCollision2D(rigidA.getMass(), rigidA.getVelocity(), rigidB.getMass(), rigidB.getVelocity());
			//This is a vice versa if statement. If one is not movable and the other is, then recalculate their velocities.
			if(!rigidB.isMovable() && rigidA.isMovable())
			{
				actorVels[0] = new Vector2D(rigidA.getVelocity().getX() * - 1, rigidB.getVelocity().getY() * -1);
			}
			if(!rigidA.isMovable() && rigidB.isMovable())
			{
				actorVels[1] = new Vector2D(rigidB.getVelocity().getX() * -1, rigidB.getVelocity().getY() * -1);
			}
		}
		if(rigidA.isMovable())
			rigidA.setVelocity(actorVels[0]);
		if(!ChowFunctions.isInRange(actorVels[1].getMagnitude(), -0.1, 0.1) )	
			rigidB.setVelocity(actorVels[1]);
		
	}
	public boolean hasCollision(){ return has_collision; }
	public void checkCollision(Actor actorB)
	{
		//Have to call each time in case it changes over iteration
			if (actorB != this.Mentor && actorB.rigidbody != null && this.Mentor.rigidbody != null)
			{
				if(hasCollided(this.Mentor, actorB))
				{
				}
				else
				{	
					this.hasInteresected = true;
					actorB.rigidbody.hasInteresected = true;
					if(this.hasCollision())
						calcCollision(this.Mentor, actorB);
				}
			}
	}
	
	
	public void updateBoundingBox()
	{
		//Include velocity to calculate future projection such that the object doesnt penetrate. Need to over estimate as well 
		//so maybe a range owuld be useful at some point.
		this.collisionShape.setX(this.getX() + this.getVelocity().getX());
		this.collisionShape.setY(this.getY() + this.getVelocity().getY());
		this.collisionShape.setHeight(this.Mentor.getHeight());
		this.collisionShape.setWidth(this.Mentor.getWidth());
		
		this.hasInteresected = false;

	}
	
	/**
	 * Calls necessary methods at synchronization tick time.
	 */
	public void update() 
	{
		super.update();
		//call collision ethods
		
	}
	
	/**
	 * Sets the mass of the rigid body.
	 * @param mass The new mass of the rigid body. If the value is <= to 0, the mass is set to 1.
	 */
	public void setMass(double mass)
	{
		this.mass = mass <= 0 ? 1 : mass;
	}
	
	/**
	 * Getter function for the mass of the rigid body
	 * @return Returns the mass of the rigid body.
	 */
	public double getMass()
	{
		return this.mass;
	}

	public boolean isMovable() {
		return isMovable;
	}
	/**
	 * Set to movable if you want the object to have a velocity upon collision
	 * @param isMovable
	 */
	public void setMovable(boolean isMovable) {
		this.isMovable = isMovable;
	}

	public boolean hasInteresected() {
		return hasInteresected;
	}

	public void setHasInteresected(boolean hasInteresected) {
		this.hasInteresected = hasInteresected;
	}
	
}
