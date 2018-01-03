import java.awt.Color;
import java.awt.Font;

public class UserInterface extends Transform{

	private String ui_text = "";
	private Color ui_color = Color.WHITE;
	private Font ui_font = new Font("Courier New", Font.PLAIN, 12);
	
	/*
	 * Default constructor for UserInterface object. This object is positioned at (0.0,0.0) with a direction of 0.0 radians.
	 */
	UserInterface() {
		super(0.0,0.0,0.0);
	}
}
