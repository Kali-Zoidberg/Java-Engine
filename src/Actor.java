import java.awt.Color;
import java.awt.Graphics2D;

/**
 * 
 * Inheritance changes: Actor is a Game Object
 * 	- Actor has a Transform or RigiBody.
 * 	- Actor cannot move
 *  - Rigid bodies and transforms can move.
 *  -Actor moves based on transform. When transform is called to move, the actor's locaiton is updated?
 */

public class Actor extends GameObject{
	
	public Sprite actor_sprite;
	private Color sprite_color = Color.PINK;
	public Graphics2D actor_graphics;
	
	//private Actor instanceOfActorInTable;
	private int actor_width = 10;
	private int actor_height = 10;
	
	private int converted_pos_x = 0;
	private int converted_pos_y = 0;
	
	private boolean has_collision = false;
	public boolean is_visible = false;
	public RigidBody rigidbody;
	
	/**
	 * Constructs a Actor object with 'debug' position and sprite.
	 * The default 'Sprite' is 10x10 box with the color Pink
	 * @param The String name of the actor. Used for searching through the Array list of actors.
	 */
	
	Actor(String name) {
		super(name);
		actor_sprite = null;
		GameWorld.actor_list.add(this);

	}
	
	
	/**
	 * Constructs an Actor object with the specified position.
	 * @param x The X position of the Actor.
	 * @param y The Y position of the Actor.
	 * @param name the name of the Actor.
	 */
	
	Actor(double x, double y, String name) {
		super(x,y,name);
		rigidbody = new RigidBody(transform, this, "rigidbody");
		GameWorld.actor_list.add(this);


	}
	
	
	/**
	 * Constructs an Actor object with a specified transform.
	 * @param transform The transform of the Actor. Through which the Actor's velocity and position 
	 * @param name The string name of the actor. Used for searching through the Array list of actors.
	 * is manipulated and retrieved.
	 */
	
	Actor(Transform transform, String name){
		super(name, transform);
		transform.setMentor(this);
		transform.setX(transform.getX() - actor_width);
		transform.setY(transform.getY() - actor_height);
		
		this.transform = transform;
		rigidbody = new RigidBody(this.transform, this, "rigidbody");

		actor_sprite = null;
		GameWorld.actor_list.add(this);
	}
	
	
	/**
	 * Constructs an Actor object with
	 * @param transform The transform of the Actor. Through which the Actor's velocity and position 
	 * is manipulated and retrieved.
	 * @param sprite The Image of the sprite.
	 * @param name The String name of the actor. Used for searching through the Array list of actors.
	 * @param width The width of the Actor, and consequently, the sprite.
	 * @param height The height of the Actor, and consequently, the sprite.
	 */
	
	Actor(Transform transform, Sprite sprite, int width, int height, String name) {
		
		
		super(name, transform);
		actor_sprite = sprite;
	
		actor_width = width;
		actor_height = height;
		sprite_color = sprite.getColor();
		
		transform.setX(transform.getX() - actor_width);
		transform.setY(transform.getY() - actor_height);
		this.transform = transform;
		rigidbody = new RigidBody(this.transform, this, "rigidbody");

		GameWorld.actor_list.add(this);

	}
	
	
	/**
	 * Constructs an Actor object with a transform, color, width, and height.
	 * The actor will appear as a box on screen.
	 * @param transform The Transform of the Actor. Through which the velocity and position are manipulated
	 * and retrieved.
	 * @param name The String name of the actor. Used for searching through the Array list of actors.
	 * @param color The color of the Actor. 
	 * @param width The width of the Actor and box.
	 * @param height The height of the Actor and box.
	 */
	
	Actor(Transform transform, Color color, int width, int height, String name) {
	
		super(name, transform);
		
		actor_sprite = null;
		sprite_color = color;
		
		actor_width = width;
		actor_height = height;
		
		transform.setX(transform.getX() - actor_width);
		transform.setY(transform.getY() - actor_height);
		this.transform = transform;
		rigidbody = new RigidBody(this.transform, this, "rigidbody");
		GameWorld.actor_list.add(this);
	}
	
	
	Actor (RigidBody rigidbody, Color color, int width, int height, String name) {
		
		super(name, rigidbody);
		
		actor_sprite = null;
		sprite_color = color;
	
		actor_width = width;
		actor_height = height;
	
		rigidbody.setX(rigidbody.getX() - actor_width);
		rigidbody.setY(rigidbody.getY() - actor_height);
		
		this.rigidbody = rigidbody;
		this.addComponent(this.rigidbody);
		rigidbody = new RigidBody(this.transform, this, "rigidbody");

		GameWorld.actor_list.add(this);
	}
	/**
	 * Sets the bounds in the world coordinate array located in GameWorld.
	 * May need to use calculus for this problem...
	 * @param x 
	 * @param y
	 * @return
	 */
	public boolean setWorldCoordinates(int x, int y) {
		
		return true;
	}
	
	
	/**
	 * Sets the Actor's size.
	 * @param width The width of the Actor.
	 * @param height The height of the Actor.
	 * @return Returns true as long as width and height are >= 0. Otherwise, it returns false.
	 */
	
	public boolean setActorSize(int width, int height) {
		if (width < 0 && height < 0)
			return false; //invalid size.
		else {
			actor_width = width;
			actor_height = height;
			return true;
		}		
	}
	
	
	/**
	 * Loads a new sprite onto the actor.
	 * @param sprite The new sprite to load in the actor.
	 */
	
	public void setSprite(Sprite sprite) {
		actor_sprite = sprite;
		actor_width = sprite.getWidth();
		actor_height = sprite.getHeight();
	}
	
	
	/**
	 * Sets the actor to render a rectangle instead of a sprite.
	 * @param width The width of the actor.
	 * @param height The height of the actor.
	 * @param color The color of the actor.
	 */
	
	public void setRect(int width, int height, Color color) {
		actor_sprite = null;
		actor_width = width;
		actor_height = height;
		sprite_color = color;
		
	}
	
	
	/**
	 * Sets the actor to render a rectangle instead of a sprite.
	 * @param color The color of the rectangle.
	 */
	
	public void setRect(Color color) {
		
		actor_sprite = null;
		sprite_color = color;
		
	}
	
	
	/**
	 * Renders an actor on screen if set to visible.
	 */
	public void renderActor() {

		this.converted_pos_x = (int) (transform.getX() * GameWorld.getViewportScaleX() + 0.5f);
		this.converted_pos_y = (int) (transform.getY() * GameWorld.getViewPortScaleY() + 0.5f);
		int conv_act_width = (int) (this.actor_width * GameWorld.getViewportScaleX());
		int conv_act_height = (int) (this.actor_height * GameWorld.getViewPortScaleY());
		double actor_dir = transform.getDirection();
		
		if (is_visible) {
			//actor_graphics = Render.bi.createGraphics();
			if (actor_sprite != null) {
				actor_graphics.drawImage(actor_sprite.getImage(), converted_pos_x, converted_pos_y, 
						actor_sprite.getWidth(), actor_sprite.getHeight(), null);
				actor_graphics.rotate(actor_dir);
			} else {

				actor_graphics.setColor(sprite_color);
				actor_graphics.fillRect(converted_pos_x, converted_pos_y, 
						conv_act_width, conv_act_height);
				actor_graphics.rotate(actor_dir);
				
			}
		} else {
			//if (actor_sprite != null) {
				
			//} else {
			//	actor_graphics.dispose();
			//	actor_graphics.clearRect(x_position, y_position, actor_width, actor_height);
		//	}
			//unrender the actor.
		}
	}
	
	
	/**
	 * Sets an actor visible on the scene.
	 * @param enable Set to true if you want the actor to be visible and in the jframe/scene.
	 */
	
	public void setVisible(boolean enable) {

		is_visible = enable;
		
	}
	
	
	
	/**
	 * Removes an Actor object from the GameWorld Actor list. Please use this other than the remove function from the array list.
	 */
	
	public void remove() {
					
		GameWorld.game_obj_table.remove(name);
			
	}
	
	
	/**
	 * If the actor has been set to visible, this function returns true.
	 * @return Returns true is suppose to be rendered onto the scene.
	 */
	
	public boolean isVisible() {
		return is_visible;
	}
	
	
	/**
	 * Get function for the actor's name. Use this to search through the Array string of actors.
	 * @return Returns the name of the actor.
	 */
	
	public String getActorName() {
		return name;
	}
	
	
	
	/**
	 * Retrieves the Actor's width
	 * @return Returns the width of the Actor's sprite.
	 */
	
	public int getActorWidth() {
		return actor_width;
	}
	
	
	/**
	 * Returns the Actor's height.
	 * @return Returns the height of the Actor's sprite.
	 */
	
	public int getActorHeight() {
		return actor_height;
	}
	
	

}
