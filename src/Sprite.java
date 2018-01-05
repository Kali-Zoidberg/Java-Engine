import java.awt.Color;
import java.awt.Image;

public class Sprite {
	
	private Image sprite_image;
	private int sprite_width = 10;
	private int sprite_height  = 10;
	private Color sprite_color = Color.PINK;
	
	/**
	 * Default Constructor for a sprite.
	 * Defaults to 10x10 pixels in size with the color pink.
	 */

	Sprite() {
		sprite_image = null;
		
	}
	
	/**
	 * Constructs a sprite object with a sprite image as well as a specified height and width dimensions.
	 * @param img Image of the sprite.
	 * @param width Width of the sprite box.
	 * @param height Height of the sprite box.
	 */
	
	Sprite(Image img, int width, int height) {
		sprite_width = width;
		sprite_height = height;
		sprite_image = img;
	}
	
	
	/**
	 * Constructs a sprite object without a sprite image. This will create a box with specified, height, width and color.
	 * @param color Color of the sprite box.
	 * @param width Width of the sprite box.
	 * @param height Height of the sprite box.
	 */
	
	Sprite(Color color, int width, int height) {
		
		sprite_image = null;
		sprite_color = color;
	}
	
	
	/**
	 * Loads a new color into the sprite.
	 * @param color The new color of the sprite.
	 */
	
	public void setColor(Color color) {
		sprite_color = color;
	}
	
	/**
	 * Retrieves the color of the Actor.
	 * @return Returns the color of the Actor/sprite.
	 */
	
	public Color getColor() {
		return sprite_color;
	}
	
	/**
	 * Loads a new image into the sprite.
	 * @param img The image of the sprite to load.
	 * @return Returns true if the operation is successful.
	 */
	
	public boolean setImage(Image img) {
		sprite_image = img;
		return true;
	}
	/**
	 * Retrieves the sprite image.
	 * @return Returns the sprite image.
	 */
	public Image getImage() {
		return sprite_image;
	}
	
	
	/**
	 * Sets the size of the sprite.
	 * @param width The new width of the sprite.
	 * @param height The new height of the sprite.
	 * @return Returns true if the width and height are both >= 0. Otherwise, it returns false.
	 */
	public boolean setSize(int width, int height) {
		if (width < 0 && height < 0)
			return false;
		else {
			sprite_width = width;
			sprite_height = height;
			
			return true;
		}
	}
	
	/**
	 * Retrieves the width of the sprite.
	 * @return Returns the sprite's height as an integer.
	 */
	
	public int getWidth() {
		return sprite_width;
	}

	/**
	 * Retrieves the height of the sprite.
	 * @return Returns the sprite's height as an integer.
	 */
	
	public int getHeight() {
		return sprite_height;
	}
	
	/**
	 * Determines if the sprite has an Image or not.
	 * @return Returns true if the sprite has an image.
	 */
	public boolean hasImage() {
		if (sprite_image != null)
			return true;
		else
			return false;
	}
}
