import java.awt.Color;
import java.awt.Font;


public class UserInterface extends Transform{

	private String ui_text = "Default Text.";
	private Color ui_color = Color.WHITE;
	private Font ui_font = null;
	private Sprite ui_sprite;
	/*
	 * Default constructor for UserInterface object. This object is positioned at (0.0,0.0) with a direction of 0.0 radians.
	 */
	UserInterface() {
		super(0.0,0.0,0.0);
	}
	/**
	 * Constructs a UserInterface object without a sprite or specified text.
	 * @param x The x position on the screen to place the UserInterface object.
	 * @param y The y position on the screen to place the UserInterface object.
	 * @param direction The direction/rotation of the UserInterface object on the screen.
	 */
	UserInterface(double x, double y, double direction) {
		super(x,y,direction);
	}
	
	/**
	 * Constructs a UserInterface object with a specified position, text, color and font.
	 * @param x The x position on the screen to place the UserInterface object.
	 * @param y The y position on the screen to place the UserInterface object.
	 * @param direction The direction/rotation of the UserInterface object.
	 * @param text The text of the UserInterface object.
	 * @param color The color of the text.
	 * @param font The font of the text.
	 */
	UserInterface(double x, double y, double direction, String text, Color color, Font font) {
		super(x,y,direction);
		ui_text = text;
		ui_color = color;
		ui_font = font;
	}
	/**
	 * Constructs a UserInterface object with a specified position and sprite/image.
	 * @param x The x position on the screen to place the UserInterface object.
	 * @param y The y position on the screen to place the UserInterface object.
	 * @param direction The direction/rotation of the UserInterface object.
	 * @param ui_sprite The Sprite/image of the UserInterface object.
	 */
	
	UserInterface(double x, double y, double direction, Sprite ui_sprite) {
		super(x,y,direction);
		this.ui_sprite = ui_sprite;
	}
	/**
	 * Constructs a Text-Based user interface object.
	 * @param coordinate The coordinates on the screen to place the UserInterface object.
	 * @param direction The direction/rotation of the UserInterface object.
	 * @param text The text of the UserInterface object.
	 * @param color The color of the text.
	 * @param font The font of the text.
	 */
	UserInterface(Cartesian2D coordinate, double direction, String text, Color color, Font font) {
		super(coordinate, direction);
		ui_text = text;
		ui_color = color;
		ui_font = font;
	}
	
	
	/**
	 * Constructs a Sprite-Based UserInterface object.
	 * @param coordinate The coordinates on the screen to place the UserInterface object.
	 * @param direction The direction/rotation of the UserInterface object.
	 * @param ui_sprite The sprite/image of the UserInterface object.
	 */
	
	UserInterface(Cartesian2D coordinate, double direction, Sprite ui_sprite) {
		super(coordinate, direction);
		this.ui_sprite = ui_sprite;
	}
	
	/**
	 * Set function for the UserInterface object's text.
	 * @param text The new text for the UserInterface object.
	 */
	
	public void setText(String text) {
		ui_text = text;
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
	
	/**
	 * Renders the text of the UserInterface object instead of the sprite.
	 */
	public void renderText() {
		
	}
	/**
	 * Renders the Sprite of the UserInterface object instead of the text.
	 */
	public void renderSprite() {
		
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
	
}
