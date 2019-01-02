package jengine;
//TODO: Add option for rescaling. preferably disable it for now.
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
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

	
	/**
	 * Constructs a GameWindow object. 
	 * @param graphics The Graphics2D object. This is passed as an argument so that the user can manipulate the Graphics2D object themselves.
	 * @param frame The JFrame object for the GameWindow
	 * @param default_width The default frame width upon start up.
	 * @param default_height The default frame height upon start up.
	 * @param frame_title The title of the frame.
	 */
	
	public GameWindow() {

	
	}
	
	public void Render() {
		
		//Change parameters once GameWorld class is made.
		this.removeAll();

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
		
		/*
		 * The for loop runs through each game object and calls the correct method of rendering depending on the type of GameObject.
		 */
		for (GameObject goj: GameWorld.game_obj_table.values()) {
			Actor temp_actor;
			UserInterface temp_ui;
			
			if (goj instanceof Actor) { //Renders all objects that are an actor or subclass of an actor.
				
				temp_actor = (Actor) goj;
				temp_actor.rigidbody.update();
				temp_actor.actor_graphics = graphics_2d;
				temp_actor.renderActor();
				temp_actor.update();
			}
			
			if (goj instanceof UserInterface) { //Renders all objects that are a UserInterface or subclass of a UserInterface.
				temp_ui = (UserInterface) goj;
				
				temp_ui.ui_graphics = graphics_2d;
				temp_ui.renderText();
				temp_ui.update();
			}
		}
		
	}
	
	/**
	 * Checks the bounds of a specified x,y position to see if it can be rendered on screen.
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @return Returns true if the position is within bounds of the specified screen
	 */
	public boolean checkWindowBounds(int x, int y)
	{
		return (x >= 0 && y >= 0 && 
				x <= this.getCurrentWidth() && y <= this.getCurrentHeight());
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
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
		return screenSize.width;
	}
	
	
	/**
	 * Returns the current height of the GameWindow frame.
	 * @return Returns the height width of the GameWindow frame.
	 */
	
	public int getCurrentHeight() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return screenSize.height;
	}
	
	/**
	 * Returns a scaled double to convert coordinate positions to screen coordinates.
	 * @return Returns a scaled double.
	 */
	
	public double getViewportScaleX()
	{
		double world_width = GameWorld.getWorldWidth();
		return (world_width / this.getCurrentWidth());
	}
	/**
	 * Returns a scaled double to convert coordinate positions to screen coordinates.
	 * @return Returns a scaled double.
	 */
	
	public double getViewPortScaleY()
	{
		double world_height =  GameWorld.getWorldHeight();
		return (world_height / this.getCurrentHeight());
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
