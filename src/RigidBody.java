import java.awt.Color;


public class RigidBody extends Transform {
	
	float rig_mass = 0;
	Vector2D rig_veloc = new Vector2D(0,0);
	Actor Mentor = (Actor) super.Mentor;
	private boolean has_collision = false;
	
	/**
	 * Constructs a RigidBody object with 'debug' position and sprite.
	 * The default 'Sprite' is 10x10 box with the color Pink
	 * @param The String name of the RigidBody. Used for searching through the Array list of actors.
	 */
	
	RigidBody(Actor mentor) {
		super.Mentor = mentor;
	}

	/**
	 * Constructs a RigidBody object with
	 * @param transform The transform of the RigidBody. Through which the Actor's velocity and position 
	 * is manipulated and retrieved.
	 * @param sprite The Image of the sprite.
	 * @param name The String name of the RigidBody. Used for searching through the Array list of actors.
	 * @param width The width of the RigidBody, and consequently, the sprite.
	 * @param height The height of the RigidBody, and consequently, the sprite.
	 */
	RigidBody(String name, Transform transform, Sprite sprite, int width, int height) {
		super(name, transform, sprite, width, height);
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
	RigidBody(Transform transform, Color color, String name, int width, int height) {
		super(transform, color, name, width, height);
	}

	public void setCollision(boolean enable) {
		has_collision = enable;
		int actor_width = Mentor.getActorWidth();
		int actor_height = Mentor.getActorHeight();
		
		if (enable) {
			for (int i = 0; i <= actor_width; ++i) {
				for (int j =0; j <= actor_height; ++j) {
					GameWorld.setWorldCoordinate(i + (int)(super.getX() + 0.5), j + (int)(super.getY() + 0.5), true);
				}
			}
		}
	}
	
	/**
	 * 
	 * @param x The x-coordinate to set the RigidBody to.
	 */
	public void setX(double x) {
		
		int y_pos = (int) (super.getY() + 0.5); //Calculated here to optimize stack calls.
		int x_pos = (int) (x + 0.5);
		GameWorld.ui_list.get(0).setText("x pos: " + x_pos + "y pos: " + y_pos);
		
		if(has_collision) {
			int actor_width = Mentor.getActorWidth(); // Binding issue
			int actor_height = Mentor.getActorHeight();
			System.out.println(GameWorld.GetWorldCoordinate(x_pos, y_pos) + " \n");
			//First check from (x,y)->(x,
			if (x_pos <= GameWorld.getWorldWidth() && x_pos  >= 0 && !(GameWorld.GetWorldCoordinate(x_pos,y_pos))) {//Checks the bounds of the object. If the object exits the gameWorld, it no longer has collision. This is to prevent an out-of-bounds exception.
				super.setX(x_pos);
				//super.setY(y_pos);
				for (int i = 0; i <= actor_width; ++i) {
					for (int j =0; j <= actor_height; ++j) {
						GameWorld.setWorldCoordinate(i + (int)(super.getX() + 0.5), j + y_pos, false);
						GameWorld.setWorldCoordinate(i + (x_pos), j + y_pos,true);
					}
				}
				
			}
		}
		else { //Rigid body does not have collision enabled so it may move anywhere on the plane.
			super.setX(x_pos);
			//super.setY(y_pos);
		}
	}
	/**
	 * Overrides the transform setY and if collision is enabled, the GameWorld coordinate spaces will react accordingly.
	 * @param y The y coordinate to set the RigidBody to.
	 */
	public void setY(double y) {
		
		int y_pos = (int) (y + 0.5);
		int x_pos = (int) (super.getX() + 0.5);
		GameWorld.ui_list.get(0).setText("x pos: " + x_pos + "y pos: " + y_pos); //This is for debug

		if(has_collision) {

			int actor_width = Mentor.getActorWidth();
			int actor_height = Mentor.getActorHeight();
			if (y_pos <= GameWorld.getWorldHeight() && y_pos >= 0 && !(GameWorld.GetWorldCoordinate(x_pos,y_pos))) { 
				//Checks the bounds of the object. If the object exits the Game World, it no longer has collision. This is to prevent an out-of-bounds exception.
				//Check edge of rectangle in direction of y. So 
				super.setY(y_pos);
				for (int i = 0; i <= actor_width; ++i) {
					for (int j =0; j <= actor_height; ++j) {
						GameWorld.setWorldCoordinate(i + x_pos, j + (int) (super.getY() + 0.5), false);
						GameWorld.setWorldCoordinate(i + x_pos, j + y_pos,true);
					}
				}
				
			}
			
		} else { //Rigid body does not have collision so it may be set to anywhere in the plane.
			super.setY(y_pos);
		}
	}
}
