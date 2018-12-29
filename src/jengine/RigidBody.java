package jengine;

import java.awt.Color;

/**
 * @author Chow
 *
 */

public class RigidBody extends Transform {
	
	protected double mass = 1;
	Actor Mentor = (Actor) super.Mentor;
	Shape collisionShape = null;
	private boolean has_collision = true;
	
	enum CollisionEnum
	{
		ISOLATED, PENETRATED, TOUCHED;
	}
	
	/**
	 * Constructs a RigidBody object with 'debug' position and sprite.
	 * The default 'Sprite' is 10x10 box with the color Pink
	 * @param The String name of the RigidBody. Used for searching through the Array list of actors.
	 */
	
	RigidBody(Actor mentor, String name) {
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
	RigidBody(double x, double y, Actor mentor, String name) {
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
	RigidBody(Transform transform, Actor mentor, String name) {
		super(transform.getX(), transform.getY(), mentor, name);
		Mentor.transform = this;
		collisionShape = new Rectangle(transform.getX(),transform.getY(), mentor.getWidth(), mentor.getHeight());
		System.out.printf("CollisionShape width: %f collisionshape height: %f\n", ((Rectangle) collisionShape).getWidth(), ((Rectangle) collisionShape).getHeight());

	}
	
	/**
	 * Constructs a RigidBody using a transform.
	 * @param transform The transform to use in the RigidBody.
	 * @param mentor The mentor of the RigidBody.
	 * @param shape the collision shape
	 * @param name The name of the RigidBody.
	 */
	RigidBody(Transform transform, Shape collisionShape, Actor mentor, String name) {
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
	/*RigidBody(Transform transform, Color color, String name, int width, int height) {
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
	
	
	
	private boolean isInRange(double a0, double b1, double b2)
	{
		return (a0 > b1 && a0 < b2);
		
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
	
	public void setX2(double x) 
	{
		if (has_collision)
		{
			for (Actor actor: GameWorld.actor_list)
			{
				if(actor.rigidbody.hasCollision())
				{

					//Check to make sure the current actor we are checking is not OUR actor.
					//if(actor.getID() != this.Mentor.getID()) 
					if(actor.getName() != this.Mentor.getName())
					{
						//CHECK MATH
						if(this.checkCollision(this, actor.rigidbody))
							System.out.printf("Collision occured between %s and %s \n", this.getName(), actor.getName());
						
						
					}

				}
			}
		}
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
		this.setX2(x_min);
		//System.out.printf(" Checking from [%d, %d] to [%d,%d]",x_min,y_min,x_max,y_max);
	//	for (int i = x_min; i <= x_max; ++i) { 
		//	for (int j = y_min; j <= y_max; ++j) {
			//	if (!GameWorld.GetWorldCoordinate(i, j).equals("-1")) {
				//	System.out.println("\n x:" + this.getX() + " y: " + this.getY());
				//	System.out.println("i: " + i + " j: " + j + " " + GameWorld.GetWorldCoordinate(i, j));
				//	return false;
					
			//	}


	//	}
			
//		}
		
		return true;
	}
	public double calcProjection()
	{
		return 0.0;
	}
	
	/**
	 * Calculates the distance between two actors.
	 * @param actorA The first actor
	 * @param actorB The second actor
	 * @return Returns the gap between the two actors.
	 */
	public boolean hasCollided(Actor actorA, Actor actorB)
	{

				boolean isSeparated = false;
				Vector2D[] normActorA = actorA.rigidbody.collisionShape.getNormals();
				Vector2D[] normActorB = actorA.rigidbody.collisionShape.getNormals();
				
				Vector2D[] actorAShapeVects = actorA.rigidbody.collisionShape.getVectors();
				Vector2D[] actorBShapeVects = actorB.rigidbody.collisionShape.getVectors();
				
				for (int i = 0; i < normActorA.length; ++i)
				{
					double[] minMaxShapeA = Physics.minMaxProj(actorAShapeVects, normActorA[i]);
					double[] minMaxShapeB = Physics.minMaxProj(actorBShapeVects, normActorA[i]);
					isSeparated = (minMaxShapeA[0] > minMaxShapeB[1] || minMaxShapeB[0] > minMaxShapeA[1]);
					if (isSeparated)
						return true;
				}
				
				double minOverlap = 0;
				if (!isSeparated)
				{
					
					for (int i = 0; i < normActorB.length; ++i)
					{
					double[] minMaxShapeA = Physics.minMaxProj(actorAShapeVects, normActorB[i]);
					double[] minMaxShapeB = Physics.minMaxProj(actorBShapeVects, normActorB[i]);
					isSeparated = Physics.projOverLap(minMaxShapeA, minMaxShapeB);
					
					//FIXME
					//Need to make sure the ogbjects no longer overlap by pushing them outward. http://www.dyn4j.org/2010/01/sat/#sat-mtv
					if(isSeparated)
						return true;
					}
					
				}
				return false;
				
			
	}
	
	public void calcCollision(Actor actorA, Actor actorB)
	{
		RigidBody rigidA = actorA.rigidbody;
		RigidBody rigidB = actorB.rigidbody;
		Vector2D actorVels[] = Physics.elasticCollision2D(rigidA.getMass(), rigidA, rigidB.getMass(), rigidB);
		rigidA.setVelocity(actorVels[0]);
		//if(ChowFunctions.isInRange(actorVels[1].getMagnitude(), -0.1, 0.1))	
			rigidB.setVelocity(actorVels[1]);
	
	}
	public boolean hasCollision(){ return has_collision; }
	public void checkAllCollisions()
	{
		//Have to call each time in case it changes over iteration
		for (int i = 0; i < GameWorld.actor_list.size(); ++i)
		{
			Actor curActor = GameWorld.actor_list.get(i);
			if (curActor != this.Mentor && curActor.rigidbody != null && this.Mentor.rigidbody != null && 
					curActor.rigidbody.hasCollision() && (!this.Mentor.getName().equals("box1") || !this.Mentor.getName().contentEquals("box2")))
			{
				//System.out.printf("Checking : %s and %s \n", this.Mentor.getName(), curActor.getName());
				if(hasCollided(this.Mentor, curActor))
				{
				}
				else
				{		
					calcCollision(this.Mentor, curActor);
				}
				
				
				//else if (collisionStatus == CollisionEnum.PENETRATED)
				//	System.out.printf("Actors penetrated: %s, %s \n", this.Mentor.getName(), curActor.getName());
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
		
	}
	
	/**
	 * Calls necessary methods at synchronization tick time.
	 */
	public void update() 
	{

		updateBoundingBox();
		checkAllCollisions();
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
	
}
