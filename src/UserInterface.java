import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class UserInterface extends Transform{

	private String ui_text = "Default Text.";
	private String ui_name;
	private int ui_index = GameWorld.ui_list.size(); // Since ui_index is initialized before adding the object to the list, the object is correctly indexed.
	private Color ui_color = Color.WHITE;
	private Font ui_font = null;
	private Sprite ui_sprite;
	private boolean is_text = false;
	public Graphics2D ui_graphics;
	
	/**
	 * Default constructor for UserInterface object. This object is positioned at (0.0,0.0) with a direction of 0.0 radians.
	 * @param name The name of the UserInterface object.
	 */
	UserInterface(String name) {
		super(0.0,0.0,0.0);
		ui_name = name;
		GameWorld.ui_list.add(this);
		
	}
	
	
	/**
	 * Constructs a UserInterface object without a sprite or specified text.
	 * @param x The x position on the screen to place the UserInterface object.
	 * @param y The y position on the screen to place the UserInterface object.
	 * @param direction The direction/rotation of the UserInterface object on the screen.
	 * @param The name of the UserInterface object.
	 */
	
	UserInterface(double x, double y, double direction, String name) {
		super(x,y,direction);
		ui_name = name;
		is_text = true;
		GameWorld.ui_list.add(this);
	}
	
	
	/**
	 * Constructs a UserInterface object with a specified position, text, color and font.
	 * @param x The x position on the screen to place the UserInterface object.
	 * @param y The y position on the screen to place the UserInterface object.
	 * @param direction The direction/rotation of the UserInterface object.
	 * @param text The text of the UserInterface object.
	 * @param color The color of the text.
	 * @param font The font of the text.
	 * @param name The name of the UserInterface object.
	 */
	
	UserInterface(double x, double y, double direction, String text, Color color, Font font, String name) {
		super(x,y,direction);
		ui_text = text;
		ui_color = color;
		ui_font = font;
		ui_name = name;
		
		is_text = true;
		GameWorld.ui_list.add(this);
	}
	/**
	 * Constructs a UserInterface object with a specified position and sprite/image.
	 * @param x The x position on the screen to place the UserInterface object.
	 * @param y The y position on the screen to place the UserInterface object.
	 * @param direction The direction/rotation of the UserInterface object.
	 * @param ui_sprite The Sprite/image of the UserInterface object.
	 * @param name The name of the UserInterface object.
	 */
	
	UserInterface(double x, double y, double direction, Sprite ui_sprite, String name) {
		super(x,y,direction);
		this.ui_sprite = ui_sprite;
		ui_name = name;
		GameWorld.ui_list.add(this);
		
	}
	/**
	 * Constructs a Text-Based user interface object.
	 * @param coordinate The coordinates on the screen to place the UserInterface object.
	 * @param direction The direction/rotation of the UserInterface object.
	 * @param text The text of the UserInterface object.
	 * @param color The color of the text.
	 * @param font The font of the text.
	 * @param name The name of the UserInterface object.
	 */
	UserInterface(Cartesian2D coordinate, double direction, String text, Color color, Font font, String name) {
		super(coordinate, direction);
		ui_text = text;
		ui_color = color;
		ui_font = font;
		ui_name = name;
		
		is_text = true;
		GameWorld.ui_list.add(this);
	}
	
	
	/**
	 * Constructs a Sprite-Based UserInterface object.
	 * @param coordinate The coordinates on the screen to place the UserInterface object.
	 * @param direction The direction/rotation of the UserInterface object.
	 * @param ui_sprite The sprite/image of the UserInterface object.
	 * @param name The name of the UserInterface object.
	 */
	
	UserInterface(Cartesian2D coordinate, double direction, Sprite ui_sprite, String name) {
		super(coordinate, direction);
		this.ui_sprite = ui_sprite;
		ui_name = name;
		GameWorld.ui_list.add(this);
	}
	
	

	/**
	 * Set function for the UserInterface object's text.
	 * @param text The new text for the UserInterface object.
	 */
	
	public void setText(String text) {
		GameWorld.ui_list.get(ui_index).ui_text = text;
		GameWorld.ui_list.get(ui_index).is_text = true;
	}
	
	/**
	 * Set function for the UserInterface object's text's color.
	 * @param color The new color for the UserInterface object.
	 */
	
	public void setColor(Color color) {
		GameWorld.ui_list.get(ui_index).ui_color = color;
	}
	
	
	/**
	 * Sets the UserInterface object's font to the specified Font object.
	 * @param font The new font as a Font object.
	 */
	
	public void setNewFont(Font font) {
		GameWorld.ui_list.get(ui_index).ui_font = font;
	}
	
	/**
	 * Set function for the UserInterface object's sprite.
	 * @param sprite The sprite for the UserInterface object.
	 */
	public void setSprite(Sprite sprite) {
		GameWorld.ui_list.get(ui_index).ui_sprite = sprite;
		GameWorld.ui_list.get(ui_index).is_text = false;

	}
	
	/**
	 * Sets the name of the UserInterface object.
	 * @param name The name of the UserInterFace object.
	 * @return Returns true if the name has not already been assigned to another UserInterface object in the GameWorld ui_list.
	 */
	
	public boolean setName(String name) {
		
		if (GameWorld.getUIIndex(name) != -1) { //The name has not been taken yet.
			GameWorld.ui_list.get(ui_index).ui_name = name;
			return true;
		} else
			return false;
	}
	
	/**
	 * Sets the Font type and checks to see if it is a valid font.
	 * @param fontType The Font type as a string.
	 * @return Returns true if the font type exists. Otherwise, it returns false.
	 */
	
	public boolean setFontType(String fontType) {
		String[] fonts =  java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		
		int fonts_size = fonts.length;
		
		for (int i = 0; i < fonts_size; ++i) { // Checks array of Font names and compares to the desired fontType.
			if (fontType.equals(fonts[i])) {
				Font temp_font = new Font(fontType, ui_font.getStyle(), ui_font.getSize());
				GameWorld.ui_list.get(ui_index).ui_font = temp_font;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Sets a new font size for the UserInterface's object font size.
	 * @param font_size The size of the font.
	 * @return Returns true if the font_size is >= 0. Otherwise, it returns false.
	 */
	
	public boolean setFontSize(int font_size) {
		if (font_size >= 0) {
			Font temp_font = new Font(ui_font.getFontName(), ui_font.getStyle(), font_size);
			GameWorld.ui_list.get(ui_index).ui_font = temp_font;

			return true;
		} else
			return false;
	}
	
	/**
	 * Sets the font style. 
	 * @param fontStyle Font style constants are as follows: Plain: 0, Bold: 1, Italic: 2, Italic+Bold: 3
	 * @return Returns true if the fontStyle is between [0,3]. Otherwise it returns false.
	 */
	
	public boolean setFontStyle(int fontStyle) {
		if (fontStyle >= 0 && fontStyle <=3) {
			Font temp_font = new Font(ui_font.getFontName(), fontStyle, ui_font.getSize());
			GameWorld.ui_list.get(ui_index).ui_font = temp_font;
			return true;
		} else
			return false;
	}
	
	/**
	 * Removes an UserInterface object from the GameWorld UserInterface list. Please use this other than the remove function from the array list.
	 */
	public void remove() {
		int list_size = GameWorld.ui_list.size();
		if (ui_index < list_size - 1) { //Ensures that the current object being removed is not at the end of the list.
			
			for (int i = this.ui_index + 1; i < list_size; ++i) 
				GameWorld.ui_list.get(i).ui_index = i - 1;
			
			GameWorld.ui_list.remove(ui_index);
			
		} else {
			GameWorld.ui_list.remove(ui_index); // If this is the last elements, then we can just remove if from the array list without having to re-index the others.
		}
	}
	
	/**
	 * Get function for the UserInterface's text string.
	 * @return Returns the UserInterface's text as a string.
	 */
	public String getText() {
		return ui_text;
	}
	
	/**
	 * Get function for the UserInterface's text's color.
	 * @return Returns the UserInterface's text's color.
	 */
	public Color getColor() {
		return ui_color;
	}
	
	/**
	 * Returns the UserInterface object's sprite.
	 * @return Returns the UserInterface object's sprite.
	 */
	
	public Sprite getSprite() {
		return ui_sprite;
	}
	
	/**
	 * Get function for the UserInterface's text's font.
	 * @return Returns the UserInterface's text's font
	 */
	
	public Font getFont() {
		return ui_font;
	}
	
	
	/*public void render() {
		if ()
	}
	*/
	/**
	 * Renders the text of the UserInterface object instead of the sprite.
	 */
	
	public void renderText() {

		ui_graphics.setFont(ui_font);
		ui_graphics.setColor(ui_color);
		ui_graphics.drawString(ui_text, (int)(this.getX() + 0.5),(int) (this.getY() + 0.5));
		
	}
	/**
	 * Renders the Sprite of the UserInterface object instead of the text.
	 */
	public void renderSprite() {
		
		if (ui_sprite != null) {
			ui_graphics.drawImage(ui_sprite.getImage(), (int) (this.getX() + 0.5), (int) (this.getY() + 0.5), ui_sprite.getWidth(), ui_sprite.getHeight(),  null);
		}
		
	}
		
	/**
	 * Returns the font style as an integer. Plain: 0, Bold: 1, Italic: 2, Italic + Bold: 3.
	 * @return Returns the font style as an integer. Plain: 0, Bold: 1, Italic: 2, Italic + Bold: 3.
	 */
	public int getFontStyle() {
		return ui_font.getStyle();
	}
	
	/**
	 * Returns the font type.
	 * @return Returns the font type as a string.
	 */
	
	public String getFontType() {
		return ui_font.getFontName();
	}
	
	/**
	 * Returns the font size.
	 * @return Returns the font size.
	 */
	public int getFontSize() {
		return ui_font.getSize();
	}
	
	/**
	 * Retrieves the name of the UserInterface object.
	 * @return Returns the name of the UserInterface object as a string.
	 */
	public String getUIName() {
		return ui_name;
	}
	/**
	 * Retrieves the current index of the user_interface object.
	 * @return Returns the UserInterface object's index.
	 */
	public int getUIIndex() {
		return ui_index;
	}
}
