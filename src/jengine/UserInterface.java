package jengine;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class UserInterface extends GameObject{

	private String ui_text = "Default Text.";
	private Color ui_color = Color.WHITE;
	private Font ui_font = null;
	private Sprite ui_sprite;
	private boolean is_text = false;
	public Graphics2D ui_graphics;
	
	/**
	 * Default constructor for UserInterface object. This object is positioned at (0.0,0.0) with a direction of 0.0 radians.
	 * @param name The name of the UserInterface object.
	 */
	public UserInterface(String name) {
		super(name);
		GameWorld.ui_list.add(this);
		
	}
	
	
	/**
	 * Constructs a UserInterface object without a sprite or specified text.
	 * @param x The x position on the screen to place the UserInterface object.
	 * @param y The y position on the screen to place the UserInterface object.
	 * @param direction The direction/rotation of the UserInterface object on the screen.
	 * @param The name of the UserInterface object.
	 */
	
	public UserInterface(double x, double y, String name) {
		super(x,y, name);
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
	
	public UserInterface(double x, double y, double direction, String text, Color color, Font font, String name) {
		super(x,y,name);
		ui_text = text;
		ui_color = color;
		ui_font = font;
		
		is_text = true;
		GameWorld.game_obj_table.put(name, this);
	}
	/**
	 * Constructs a UserInterface object with a specified position and sprite/image.
	 * @param x The x position on the screen to place the UserInterface object.
	 * @param y The y position on the screen to place the UserInterface object.
	 * @param direction The direction/rotation of the UserInterface object.
	 * @param ui_sprite The Sprite/image of the UserInterface object.
	 * @param name The name of the UserInterface object.
	 */
	
	public UserInterface(double x, double y, double direction, Sprite ui_sprite, String name) {
		super(x,y,name);
		this.ui_sprite = ui_sprite;
		GameWorld.game_obj_table.put(name, this);
		
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
	public UserInterface(Cartesian2D coordinate, double direction, String text, Color color, Font font, String name) {
		super(coordinate.getX(), coordinate.getY(), name);
		ui_text = text;
		ui_color = color;
		ui_font = font;
		
		is_text = true;
		GameWorld.game_obj_table.put(name, this);
	}
	
	
	/**
	 * Constructs a Sprite-Based UserInterface object.
	 * @param coordinate The coordinates on the screen to place the UserInterface object.
	 * @param direction The direction/rotation of the UserInterface object.
	 * @param ui_sprite The sprite/image of the UserInterface object.
	 * @param name The name of the UserInterface object.
	 */
	
	public UserInterface(Cartesian2D coordinate, double direction, Sprite ui_sprite, String name) {
		super(coordinate.getX(), coordinate.getY(), name);
		this.ui_sprite = ui_sprite;
		GameWorld.game_obj_table.put(name, this);
	}
	
	

	/**
	 * Set function for the UserInterface object's text.
	 * @param text The new text for the UserInterface object.
	 */
	
	public void setText(String text) {
		ui_text = text;
		is_text = true;
	}
	
	/**
	 * Set function for the UserInterface object's text's color.
	 * @param color The new color for the UserInterface object.
	 */
	
	public void setColor(Color color) {
		ui_color = color;
	}
	
	
	/**
	 * Sets the UserInterface object's font to the specified Font object.
	 * @param font The new font as a Font object.
	 */
	
	public void setNewFont(Font font) {
		ui_font = font;
	}
	
	/**
	 * Set function for the UserInterface object's sprite.
	 * @param sprite The sprite for the UserInterface object.
	 */
	public void setSprite(Sprite sprite) {
		ui_sprite = sprite;
		is_text = false;

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
				ui_font = temp_font;
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
			ui_font = temp_font;

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
			ui_font = temp_font;
			return true;
		} else
			return false;
	}
	
	/**
	 * Removes an UserInterface object from the GameWorld UserInterface list. Please use this other than the remove function from the array list.
	 */
	public void remove() {			
			GameWorld.game_obj_table.remove(name);
			
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

		ui_graphics.drawString(ui_text, (int)(this.transform.getX() + 0.5),(int) (this.transform.getY() + 0.5));
		ui_graphics.setFont(ui_font);
		ui_graphics.setColor(ui_color);
		
	}
	/**
	 * Renders the Sprite of the UserInterface object instead of the text.
	 */
	public void renderSprite() {
		
		if (ui_sprite != null) {
			ui_graphics.drawImage(ui_sprite.getImage(), (int) (this.transform.getX() + 0.5), (int) (this.transform.getY() + 0.5), ui_sprite.getWidth(), ui_sprite.getHeight(),  null);
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


	@Override
	public void update() {
	}
	

}
