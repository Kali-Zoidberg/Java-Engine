
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;


import javax.swing.JPanel;
import java.awt.Image;

public class GameWindow extends JPanel {

	private Graphics window_graphics;

	private Image background_image = null;
	
	private int default_frame_width  = 0;
	private int default_frame_height = 0;
	private int current_frame_width = 0;
	private int current_frame_height = 0;
	private boolean is_paused = false;
	private String window_title = "Default_title";
	private ArrayList<Actor> game_actors = Render.actor_list;
	private ArrayList<UserInterface> game_ui_objects = Render.ui_list;
	
	/**
	 * Constructs a GameWindow object. 
	 * @param graphics The Graphics2D object. This is passed as an argument so that the user can manipulate the Graphics2D object themselves.
	 * @param frame The JFrame object for the GameWindow
	 * @param default_width The default frame width upon start up.
	 * @param default_height The default frame height upon start up.
	 * @param frame_title The title of the frame.
	 */
	
	GameWindow() {

	
	}
	
	public void Render(ArrayList<Actor> actors, ArrayList<UserInterface> ui_objs) {
		game_actors = actors;
		game_ui_objects = ui_objs;
		//Change parameters once GameWorld class is made.
		this.repaint();
	}
	
	
	/**
	 * Paint function for the Jframe.
	 * @param graphics
	 */
	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		Graphics2D graphics_2d = (Graphics2D) graphics;
		//graphics_2d.setBackground(Color.BLACK);
		if (background_image != null) { //Background image set, change the imge.
			graphics_2d.drawImage(background_image, 0, 0, Render.getScreenWidth(), Render.getScreenHeight(), null);
		}
		
		for (Actor act: game_actors) {
			act.actor_graphics = graphics_2d;
			act.renderActor();
			
		}
		for (UserInterface ui: game_ui_objects) {
			ui.ui_graphics = graphics_2d;
			ui.renderText();
		}
		//window_frame.setTitle(window_title);
		//window_frame.setSize(current_frame_width, current_frame_height);
		
	}
	
	/**
	 * Pauses the game.
	 * @param pause_status set to True if you wish to pause the game. Otherwise, set it to false.	
	 */
	public void pause(boolean pause_status) {
		is_paused = pause_status;
	}
	
	public boolean setBackground(Image bg_image) {
		background_image = bg_image;
		return true;
	}
	
	/**
	 * Sets the default width (upon game start) of the frame.
	 * @param width The default width of the frame.
	 * @return Returns true if the width and height is > 0.
	 */
	
	public boolean setDefaultSize(int width, int height) {
		if (width > 0 && height > 0) {
			default_frame_width = width;
			default_frame_height = height;
			return true;
		} else
			return false;
	}
	
	
	
	/**
	 * Sets the current width of the frame.
	 * @param width The current width of the frame.
	 * @return Returns true if the width and height is > 0.
	 */
	
	public boolean setCurrentSize(int width, int height) {
		if (width > 0) {
			current_frame_width = width;
			current_frame_height = height;
			return true;
		} else
			return false;
	}
	
	
	/**
	 * Sets the title of the frame.
	 * @param title The new title of the frame.
	 */
	
	public void setWindowTitle(String title) {
		window_title = title;
	}
	
	
	/**
	 * Changes the GameWindow object's Graphic2D object to the specified parameter.
	 * @param graphic_2d The new Graphic2D object.
	 * @return Returns true if the graphic_2d is not null.
	 */
	public boolean setGraphicsObject(Graphics2D graphic_2d) {
		if (graphic_2d != null) {
			window_graphics = graphic_2d;
			return true;
		} else
			return false;
	}
	
	
	/**
	 * Returns the default width of the GameWindow frame.
	 * @return Returns the default width of the GameWindow frame.
	 */
	
	public int getDefaultWidth() {
		return default_frame_width;
	}
	
	
	/**
	 * Returns the default height of the GameWindow frame.
	 * @return Returns the default height of the GameWindow frame.
	 */
	
	public int getDefaultHeight() {
		return default_frame_height;
	}
	
	
	/**
	 * Returns the current width of the GameWindow frame.
	 * @return Returns the current width of the GameWindow frame.
	 */
	
	public int getCurrentWidth() {
		return current_frame_width;
	}
	
	
	/**
	 * Returns the current height of the GameWindow frame.
	 * @return Returns the height width of the GameWindow frame.
	 */
	
	public int getCurrentHeight() {
		return current_frame_height;
	}
	
	
	/**
	 * Returns the title of the GameWindow frame.
	 * @return Returns the title width of the GameWindow frame.
	 */
	
	public String getWindowTitle() {
		return window_title;
	}
	
	/**
	 * Returns the GameWindow's Graphic object.
	 * @return Returns the GameWindow's Graphic object.
	 */
	
	public Graphics getGraphicsObject() {
		return window_graphics;
	}
	/**
	 * Get function to determine if the game is paused.
	 * @return Returns true if the game is paused. Otherwise, false.
	 */
	public boolean isPaused() {
		return is_paused;
	}
	
}
