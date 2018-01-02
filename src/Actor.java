import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics2D;


public class Actor {
	
	public Transform transform;
	public Sprite actor_sprite;
	
	private Color sprite_color = Color.PINK;
	private Graphics2D actor_graphics;
	private Render rend_obj; //WILL BE REMOVED WHEN RENDER IS CHANGED BACK TO STATIC
	
	private int actor_width = 10;
	private int actor_height = 10;
	private int actor_avg_width = actor_width / 2;
	private int actor_avg_height = actor_height / 2;
	
	private boolean is_visible = false;
	
	
	
	/**
	 * Constructs a Actor object with 'debug' position and sprite.
	 * The default 'Sprite' is 10x10 box with the color Pink
	 */
	
	Actor() {
		transform = new Transform();
		actor_sprite = null;
	}
	
	
	/**
	 * Constructs an Actor object with a specified transform.
	 * @param transform The transform of the Actor. Through which the Actor's velocity and position 
	 * is manipulated and retrieved.
	 */
	
	Actor(Transform transform) {
		transform.setX(transform.getX() + actor_avg_width);
		transform.setY(transform.getY() - actor_avg_height);
		this.transform = transform;
		actor_sprite = null;
	}
	
	
	/**
	 * Constructs an Actor object with
	 * @param transform The transform of the Actor. Through which the Actor's velocity and position 
	 * is manipulated and retrieved.
	 * @param sprite The Image of the sprite.
	 * @param width The width of the Actor, and consequently, the sprite.
	 * @param height The height of the Actor, and consequently, the sprite.
	 */
	
	Actor(Transform transform, Sprite sprite, int width, int height) {
		
		actor_sprite = sprite;
		actor_width = width;
		actor_height = height;
		sprite_color = sprite.getColor();
		
		actor_avg_width = actor_width / 2;
		actor_avg_height = actor_height / 2;
		
		
		transform.setX(transform.getX() + actor_avg_width);
		transform.setY(transform.getY() - actor_avg_height);
		this.transform = transform;

	}
	
	
	/**
	 * Constructs and Actor object with a transform, color, width, and height.
	 * The actor will appear as a box on screen.
	 * @param transform The Transform of the Actor. Through which the velocity and position are manipulated
	 * and retrieved.
	 * @param color The color of the Actor. 
	 * @param width The width of the Actor and box.
	 * @param height The height of the Actor and box.
	 */
	
	Actor(Transform transform, Color color, int width, int height) {
	
		actor_sprite = null;
		sprite_color = color;
		actor_width = width;
		actor_height = height;
		actor_avg_width = actor_width / 2;
		actor_avg_height = actor_height / 2;
		
		
		transform.setX(transform.getX() - actor_avg_width);
		transform.setY(transform.getY() - actor_avg_height);
		this.transform = transform;
	}
	
	
	/**
	 * Retrieves the Actor's sprite's width
	 * @return Returns the width of the Actor's sprite.
	 */
	
	public int getSpriteSize_Width() {
		return actor_width;
	}
	
	
	/**
	 * Returns the Actor's sprite's height.
	 * @return Returns the height of the Actor's sprite.
	 */
	
	public int getSpriteSize_Height() {
		return actor_height;
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
	}
	
	
	/**
	 * Renders the actor onto the Jframe.
	 * @param enable Set to true if you want the actor to be visible and in the jframe/scene.
	 */
	public void setVisible(boolean enable) {
		
		int x_position = (int) (this.transform.getConvertX() + 0.5f);
		int y_position = (int) (this.transform.getConvertY() + 0.5f);
		
		if (enable) {
			
			is_visible = true;
			actor_graphics = Render.bi.createGraphics();
			
			if (actor_sprite!=null) {
				actor_graphics.drawImage(actor_sprite.getImage(), x_position, y_position, null);
			} else {

				actor_graphics.setColor(sprite_color);
				actor_graphics.fillRect(x_position, y_position, actor_width, actor_height);
			}
		} else {
			if (actor_sprite != null) {
				
			} else {
				actor_graphics.clearRect(x_position, y_position, actor_width, actor_height);
			}
			//unreder the actor.
		}
		
	}
	/**
	 * If the actor has been set to visible, this function returns true.
	 * @return Returns true is suppose to be rendered onto the scene.
	 */
	public boolean isVisible() {
		return is_visible;
	}
}
