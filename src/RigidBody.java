import java.awt.Color;


public class RigidBody extends Actor {
	
	float rig_mass = 0;
	Vector2D rig_veloc = new Vector2D(0,0);
	
	private boolean has_collision = false;
	
	/**
	 * Constructs a RigidBody object with 'debug' position and sprite.
	 * The default 'Sprite' is 10x10 box with the color Pink
	 * @param The String name of the RigidBody. Used for searching through the Array list of actors.
	 */
	
	RigidBody(String name) {
		super(name);
	}
	
	
	/**
	 * 
	 * @param transform
	 * @param name
	 */
	RigidBody(Transform transform, String name) {
		super(transform, name);
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
	RigidBody(Transform transform, Sprite sprite, String name, int width, int height) {
		super(transform, sprite, name, width, height);
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
		int actor_width = this.getActorWidth();
		int actor_height = this.getActorHeight();
		
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
		
		super.setX(x);
		int y_pos = (int) (super.getY() + 0.5); //Calculated here to optimize stack calls.
		
		if(has_collision) {
			int actor_width = this.getActorWidth();
			int actor_height = this.getActorHeight();
			
			if (x <= GameWorld.getWorldWidth() && x  >= 0 && has_collision) {//Checks the bounds of the object. If the object exits the gameWorld, it no longer has collision. This is to prevent an out-of-bounds exception.

				for (int i = 0; i <= actor_width; ++i) {
					for (int j =0; j <= actor_height; ++j) {
						GameWorld.setWorldCoordinate(i + (int)(super.getX() + 0.5), j + y_pos, false);
						GameWorld.setWorldCoordinate(i + (int) (x+ 0.5), j + y_pos,true);
					}
				}
				
			}
		}
	}
	
	/**
	 * Overrides the transform setY and if collision is enabled, the GameWorld coordinate spaces will react accordingly.
	 * @param y The y coordinate to set the RigidBody to.
	 */
	public void setY(double y) {
		
		super.setY(y);
		int x_pos = (int) (super.getX() + 0.5);
		
		if(has_collision) {
			int actor_width = this.getActorWidth();
			int actor_height = this.getActorHeight();
			
			if (y <= GameWorld.getWorldHeight() && y >= 0 && has_collision) { //Checks the bounds of the object. If the object exits the Game World, it no longer has collision. This is to prevent an out-of-bounds exception.
				
				for (int i = 0; i <= actor_width; ++i) {
					for (int j =0; j <= actor_height; ++j) {
						GameWorld.setWorldCoordinate(i + x_pos, j + (int) (super.getY() + 0.5), false);
						GameWorld.setWorldCoordinate(i + x_pos, j + (int) (y + 0.5),true);
					}
				}
				
			}
		}
	}
}
