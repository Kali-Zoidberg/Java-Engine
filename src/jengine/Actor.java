package jengine;
import java.awt.Color;
import java.awt.Graphics2D;

import chowshapes.Circle;
import chowshapes.Hexagon;
import chowshapes.Rectangle;
import chowshapes.Triangle;

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
	
	private int actor_width = 10;
	private int actor_height = 10;
	
	private int converted_pos_x = 0;
	private int converted_pos_y = 0;
	
	public boolean is_visible = false;
	public RigidBody rigidbody;
	
	/**
	 * Constructs a Actor object with 'debug' position and sprite.
	 * The default 'Sprite' is 10x10 box with the color Pink
	 * @param The String name of the actor. Used for searching through the Array list of actors.
	 */
	
	public Actor(String name) {
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
	
	public Actor(double x, double y, int width, int height, String name) {
		super(x,y,name);
		this.actor_width = width;
		this.actor_height = height;
		rigidbody = new RigidBody(transform, this, "rigidbody");
		GameWorld.actor_list.add(this);


	}
	
	
	/**
	 * Constructs an Actor object with a specified transform.
	 * @param transform The transform of the Actor. Through which the Actor's velocity and position 
	 * @param name The string name of the actor. Used for searching through the Array list of actors.
	 * is manipulated and retrieved.
	 */
	
	public Actor(Transform transform, String name){
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
	
	public Actor(Transform transform, Sprite sprite, int width, int height, String name) {
		
		
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
	
	public Actor(Transform transform, Color color, int width, int height, String name) {
	
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
	
	
	public Actor (RigidBody rigidbody, Color color, int width, int height, String name) {
		
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
	
	public boolean setSize(int width, int height) {
		if (width < 0 && height < 0)
			return false; //invalid size.
		else {
			actor_width = width;
			actor_height = height;
			if(this.rigidbody.collisionShape != null)
			{
				this.rigidbody.collisionShape.setHeight(actor_height);
				this.rigidbody.collisionShape.setWidth(actor_width);
			}
				return true;
		}		
	}
	
	/**
	 * Sets the actor width
	 * @param width The amount of pixels to set the actor's width to.
	 * @return Returns false if width <0 else true.
	 */
	public boolean setWidth(int width)
	{
		if(width < 0)
			return false;
		else
		{
			actor_width = width;
			if(this.rigidbody.collisionShape != null)
				this.rigidbody.collisionShape.setWidth(width);
			return true;
		}
	}
	
	/**
	 * Sets the actor height
	 * @param height The amount of pixels to set the actor's height to.
	 * @return Returns false if height < 0 else true.
	 */
	
	public boolean setHeight(int height)
	{
		if(height < 0)
			return false;
		else
		{
			actor_height = height;
			if(this.rigidbody.collisionShape != null)
				this.rigidbody.collisionShape.setHeight(height);
			return true;
		}
	}
	/**
	 * Loads a new sprite onto the actor.
	 * @param sprite The new sprite to load in the actor.
	 */
	
	public void setSprite(Sprite sprite, String name) {
		actor_sprite = sprite;
		actor_sprite.setMentor(this);
		actor_sprite.setName(name);
		this.setHeight(sprite.getHeight());
		this.setWidth(sprite.getWidth());
		if(this.rigidbody.collisionShape != null)
		{
			this.rigidbody.collisionShape.setHeight(sprite.getHeight());
			this.rigidbody.collisionShape.setWidth(sprite.getWidth());
		}
	}
	
	
	/**
	 * Sets the actor to render a rectangle instead of a sprite.
	 * @param width The width of the actor.
	 * @param height The height of the actor.
	 * @param color The color of the actor.
	 */
	
	public void setRect(int width, int height, Color color) {
		actor_sprite = null;
		this.setSize(width, height);
		sprite_color = color;
		if(this.rigidbody.collisionShape != null)
		{
			this.rigidbody.collisionShape.setWidth(actor_width);
			this.rigidbody.collisionShape.setHeight(actor_height);
		}
	}
	
	public void setColor(Color color)
	{
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

		this.converted_pos_x = (int) (rigidbody.getX() * GameWorld.getViewportScaleX() + 0.5f);
		this.converted_pos_y = (int) (rigidbody.getY() * GameWorld.getViewportScaleY() + 0.5f);
		int conv_act_width = (int) (this.actor_width * GameWorld.getViewportScaleX());
		int conv_act_height = (int) (this.actor_height * GameWorld.getViewportScaleY());
		double actor_dir = rigidbody.getDirection();
		
		if (is_visible) {
			Cartesian2D center = new Cartesian2D(converted_pos_x + this.actor_width/2,
					converted_pos_y + this.actor_height/2);
			//actor_graphics = Render.bi.createGraphics();
			if (actor_sprite != null) {
			
				actor_graphics.rotate(this.rigidbody.getDirection(), center.getX(), center.getY() );
				
				actor_graphics.drawImage(actor_sprite.getImage(), (int) (rigidbody.getX()), (int) (rigidbody.getY()), 
						actor_sprite.getWidth(), actor_sprite.getHeight(), null);

			} else {
				actor_graphics.rotate(this.rigidbody.getDirection(),center.getX(), center.getY());

				actor_graphics.setColor(sprite_color);
				if(!(rigidbody.collisionShape instanceof Rectangle) && !(rigidbody.collisionShape instanceof Circle))
				{
					
					Cartesian2D trigPoints[] = rigidbody.collisionShape.getPoints();
					int[] xpoints = new int[rigidbody.collisionShape.getPoints().length - 1];
					int[] ypoints = new int[rigidbody.collisionShape.getPoints().length - 1];
					for(int i = 0; i < xpoints.length; ++i)
					{
						xpoints[i] = (int) (trigPoints[i + 1].getX());
						ypoints[i] = (int) (trigPoints[i+1].getY());
						
					}
					java.awt.Polygon shape = new java.awt.Polygon(xpoints, ypoints, trigPoints.length - 1);
					actor_graphics.fill(shape);
				} else if (!(rigidbody.collisionShape instanceof Circle))
				actor_graphics.fillRect((int) (this.rigidbody.getX() + 0.5), (int) (this.rigidbody.getY() + 0.5), 
						this.getWidth(), this.getHeight());
				else if ((rigidbody.collisionShape instanceof Circle))
					actor_graphics.fillOval((int) this.rigidbody.getX(), (int) this.rigidbody.getY(), (int)((Circle) this.rigidbody.collisionShape).getRadius() * 2, 
							(int)((Circle) this.rigidbody.collisionShape).getRadius() * 2);
			} 
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
	 * Retrieves the Actor's width
	 * @return Returns the width of the Actor's sprite.
	 */
	
	public int getWidth() {
		return actor_width;
	}
	
	
	/**
	 * Returns the Actor's height.
	 * @return Returns the height of the Actor's sprite.
	 */
	
	public int getHeight() {
		return actor_height;
	}


	@Override
	public void update() {
		this.rigidbody.update();
		//System.out.println("Updating actor method. Call rigidbody update.");
	}
	
	

}
