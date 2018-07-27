
/**
 * @author Chow
 *
 */
public class RigidBody extends Transform {
	
	float rig_mass = 0;
	Vector2D rig_veloc = new Vector2D(0,0);
	Actor Mentor = (Actor) super.Mentor;
	private boolean has_collision = true;
	
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
		int actor_width = Mentor.getActorWidth();
		int actor_height = Mentor.getActorHeight();
		
		int x_min = (int) (super.getX() + 0.5);
		int x_max = (x_min + actor_width);
		int y_min = (int) (super.getY() + 0.5);
		int y_max = (y_min + actor_height);
		
		
		if (enable) {
			for (int i = x_min; i <= x_max; ++i) {
				for (int j = y_min; j <= y_max; ++j) {
					System.out.println("setting coords for actor: " + Mentor.getName() + 
							"@x: " + i + " @y: " + j);
					GameWorld.setWorldCoordinate(i, j, Mentor.getName());
					System.out.println(GameWorld.GetWorldCoordinate(i, j));
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
		double bodyA_h = bodyA.Mentor.getActorHeight();
		double bodyA_w = bodyA.Mentor.getActorWidth();
		double bodyB_x = bodyB.getX();
		double bodyB_y = bodyB.getY();
		double bodyB_h = bodyB.Mentor.getActorHeight();
		double bodyB_w = bodyB.Mentor.getActorWidth();

		if (this.isInRange(bodyA_h, bodyB_y, bodyB_h) 
				&& (this.isInRange(bodyA_w, bodyB_x, bodyB_w) 
						|| this.isInRange(bodyA_x, bodyB_x, bodyB_w)))
		{
			System.out.println("Top left collided.");
		}
	
	
	
		
		
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
					System.out.printf("Checking collision with actor: %\n", actor.getName());

					//Check to make sure the current actor we are checking is not OUR actor.
					//if(actor.getID() != this.Mentor.getID()) 
					if(actor.getName() != this.Mentor.getName())
					{
						//CHECK MATH
						this.checkCollision(this, actor.rigidbody);

						
					}

				}
			}
		}
	}
	
	/**
	 * 
	 * @param x The x-coordinate to set the RigidBody to.
	 */
	public void setX(double x) {
		UserInterface test_ui = (UserInterface) GameWorld.game_obj_table.get("ui_test");
		int y_pos = (int) (super.getY() + 0.5); //Calculated here to optimize stack calls.
		int x_pos = (int) (x + 0.5);
		test_ui.setText("x pos: " + x_pos + "y pos: " + y_pos);
		
		if(has_collision) {
			this.setX2(x);
			int actor_width = Mentor.getActorWidth(); // Binding issue
			int actor_height = Mentor.getActorHeight();
			//System.out.println(GameWorld.GetWorldCoordinate(x_pos, y_pos) + " \n");
			//First check from (x,y)->(x,
			//System.out.println("Attempted pos:  " + x_pos + "," + y_pos);
			//System.out.println("This is what is here: " + GameWorld.GetWorldCoordinate(x_pos,y_pos));
			if (x_pos <= GameWorld.getWorldWidth() && x_pos  >= 0 && 
					(GameWorld.GetWorldCoordinate(x_pos,y_pos).equals("-1"))) {//Checks the bounds of the object. If the object exits the gameWorld, it no longer has collision. This is to prevent an out-of-bounds exception.
				super.setX(x_pos);
				
				//super.setY(y_pos);
				for (int i = 0; i <= actor_width; ++i) {
					for (int j =0; j <= actor_height; ++j) {
						GameWorld.setWorldCoordinate(i + (int)(Mentor.transform.getX() + 0.5), j + y_pos, "-1");
						GameWorld.setWorldCoordinate(i + (x_pos), j + y_pos, 
								Mentor.getName());
					}
				}
				
			}
		}
		else { //Rigid body does not have collision enabled so it may move anywhere on the plane.
			super.setX(x_pos);
			System.out.println("NO collision");
			//super.setY(y_pos);
		}
	}
	/**
	 * Overrides the transform setY and if collision is enabled, the GameWorld coordinate spaces will react accordingly.
	 * @param y The y coordinate to set the RigidBody to.
	 */
	
	public void moveY(double y) {
		
		UserInterface ui_test = (UserInterface) GameWorld.game_obj_table.get("ui_test");
		double round = (y <= 0) ? -0.5 : 0.5;
		System.out.println(round);
		int actor_width = Mentor.getActorWidth();
		int actor_height = Mentor.getActorHeight();
		
		int y_pos_cur = (int) (super.getY() + 0.5);
		int x_pos_cur = (int) (super.getX() + 0.5);
		
		int y_pos = y_pos_cur + (int)(y + round);
		int x_pos = (int) (super.getX() + 0.5);

		
		int delta_y = (int) (y+ round);
		
		ui_test.setText("x pos: " + x_pos + "y pos: " + y_pos); //This is for debug

		/**
		 * If the newly desired position is less than the previous position, the object is moving negatively along the axis.
		 * As such, we will need to subtract the Actor's height from its current position. Therefore we multiply by negative 1. 
		 */
		//if (y < 0)
			//actor_height *= -1;
		
		
		int y_pos_next = y_pos_cur + actor_height + delta_y;
		
		
		if(has_collision) {

			if (y_pos > GameWorld.getWorldHeight() || y_pos < 0) {
				System.out.println("Going off the grid!");
				return;
			} else {
				//need to check for negative velocity and positive velocity. So two cases.
				if (delta_y >= 0) {
					if (this.checkPath(x_pos_cur, y_pos_cur + actor_height + 1, 
							x_pos + actor_width, y_pos_next)) {
	
					setPath(x_pos_cur, y_pos_cur, x_pos_cur + actor_width,
							y_pos_cur + actor_height, "-1"); //Clear previous allocated space.
					setPath(x_pos, y_pos, x_pos + actor_width, 
							y_pos + delta_y, Mentor.getName());
					
					super.setY(y_pos);

					}
				} else {
 
					if (this.checkPath(x_pos_cur, y_pos_cur + delta_y - 1, 
							x_pos + actor_width, y_pos_next - actor_height)) {
						
						setPath(x_pos_cur, y_pos_cur, x_pos_cur + actor_width, 
								y_pos_cur + actor_height, "-1"); //Clear previous allocated space.
						
						System.out.println("old y: " + (y_pos_cur - actor_height));
						
						setPath(x_pos, y_pos, x_pos + actor_width, 
								y_pos + delta_y, Mentor.getName());
						super.setY(y_pos);
					}
				}
			} 
		} 
		else  //Rigid body does not have collision so it may be set to anywhere in the plane.
			super.setY(y_pos);
		
	}
	
	
	
	/**
	 * Sets the projected path for the actor with the specified maximums. So it checks from the
	 * [x_min,y_min] to [x_max,y_max] (inclusive). Since it is inclusive, you may have to adjust your bounds
	 * accordingly. (I.E. try adding 1 to your mins or maxs).
	 * @param x_min The minimum x coordinate.
	 * @param y_min The mminimum y coordinate.
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


	//ssssssssssssssssssssssssssswwwwwwwwwwwwwwwwwwwww		}
			
//		}
		
		return true;
	}
	
	
	public boolean hasCollision(){ return has_collision; }
	
	
}
