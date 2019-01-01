package jengine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Render implements Runnable{
	public static BufferedImage bi;
	public static JLabel PaddlePlayer = new JLabel(""); 
	private static int frames = 0;

	public static ArrayList<Actor> actor_list = new ArrayList<Actor>();
	public static ArrayList<UserInterface> ui_list = new ArrayList<UserInterface>();



	private static int current_frame_rate = 0;
	
	public static Controls controller = new Controls();

	private static Thread rend_thread;
	private static JFrame game_frame = new JFrame("Game Window");
	private Image bg_image = null;
	private Color bg_color = Color.BLACK;

	private int frame_rate_nano = 0;
	private long frame_rate_milli = 0;
	private long update_rate = 1000;
	private double max_framerate = 0;
	
	private static boolean is_game_over = false;
	private boolean changed_bg_colors = false;
	//Temporarily public so we can analyze the controls. The control scheme needs to be changed using keyBoard listeners instead of input maps.

	public static GameWindow game_window = new GameWindow(); 
	private static int window_width = 0;
	private static int window_height = 0;
	private static String window_title = "Game Window";
	private static double current_framerate = 0;

/*
 * TODO: 
 * Comment function using javadocs.
 * 
 */
	/**
	 * Constructs a render object with a frame title, frame width and height.
	 * @param frame_title Title of the game window.
	 * @param max_framerate The maximum frame rate the game can run.
	 * @param initial_window_width Width of the game window.
	 * @param initial_window_height Height of the game window.
	 */

	public Render(String frame_title, double max_framerate, int initial_window_width, int initial_window_height) {
		
		window_title = frame_title;
		
		this.max_framerate = max_framerate;
		
		setFrameRate(max_framerate);
		
			if (initial_window_width > 0)
				window_width = initial_window_width;
			else
				window_width = 100;
			
			if (initial_window_height > 0)
				window_height = initial_window_height;
			else
				window_height = 100;
			
	} 
	
	/**
	 * Renders the whole scene containing the actors, uiobjects, etc. This should be the very last method in the main function!
	 * @return
	 */
	public boolean renderScene() {
		long init_time = System.currentTimeMillis();
		long cur_time = 0;
		long diff_time = 0;
		int init_frame = 0;
		int diff_frame = 0;
		game_frame.setTitle(game_window.getWindowTitle());
		game_frame.add(game_window);
		game_frame.setSize(window_width, window_height);
		game_frame.setVisible(true);
		game_frame.setBackground(bg_color);
		game_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while(!is_game_over) {

			if (changed_bg_colors) { // ensures that the background is not constantly updating.
				game_window.setBackground(bg_color);
				
				changed_bg_colors = false;
			}

			cur_time = System.currentTimeMillis();
			diff_time = cur_time - init_time;
			
			/*
			 * If (diff_time >= update_Rate) is used for determining the amount of repaints per second.
			 */
			if(diff_time >= update_rate) {
				diff_frame = frames - init_frame;
				frames = 0;
				init_frame = frames;
				System.out.println("frame rate: " + diff_frame);
				init_time = System.currentTimeMillis();
			}
			
			if (!game_frame.isFocused()) // Pauses the game if the window goes out of ofcus.
				game_window.pause(true);
			else
				game_window.pause(false);
			
			if (!game_window.isPaused()) {
				
				Physics.update();
				game_window.Render(); //renders the actors and ui objects.
			}

			try {
				Thread.sleep(frame_rate_milli, frame_rate_nano); //Controls the update thread.
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Something went wrong!");
				e.printStackTrace();
			}
			frames++;

		}
		return true;
	}
	
	
	/**
	 * Calculates the current frame rate.
 	 * @param update_rate Update rate in milliseconds.
	 * @throws InterruptedException
	 */
	
	public static void calculateFrameRate(long update_rate) throws InterruptedException {
		
		if (update_rate < 0)
			update_rate *= -1;
		
		int init_frame = frames;
		//System.out.println("init frame count: " + frames);
		int cur_frame = 0;
		int frame_diff = 0;
		//Thread.sleep(update_rate);
		//System.out.println("final frame count: " + frames);
		cur_frame = frames;
		
		frame_diff = cur_frame - init_frame;
		//System.out.println("Frame rate: " + frame_diff);
	}
	
	/**
	 * Retrieves the current
	 * @return Returns the frame rate as double
	 */
	public static double getCurrentFrameRate() {
		return current_framerate;
	}
	
	
	/**
	 * Changes the title description of the game window.
	 * @param window_title The title of the game window.
	 */
	
	public static void setTitle(String window_title) {
		game_frame.setTitle(window_title);
	}
	
	/**
	 * Ends the game and stops repainting the scene.
	 */
	
	public void endGame() {
		is_game_over = true;
	}
	
	
	/**
	 * Determines if the game is over.
	 * @return Returns the end status of the game.
	 */
	
	public boolean isGameOver() {
		return is_game_over;
	}
	
	/**
	 * Sets the background to a specified color.
	 * @param color The color to set the background to.
	 */
	
	public void setBackground(Color color) {
		bg_color = color;
		changed_bg_colors = true;
		game_window.setBackground(bg_color);
	//	game_window.
	}
	
	public void setBackground(Image background_image) {
		bg_image = background_image;
		game_window.setBackground(bg_image);
		changed_bg_colors = false;
	}
	
	/**
	 * Sets the max frame rate of the game. By determining how many updates per second the frame needs to be repainted.
	 * @param framerate Desired framerate
	 * @return Returns true if the desired framerate is a valid number.
	 */
		
	public boolean setFrameRate(double framerate) {
			
		framerate = 1000/framerate; //Dividing the framerate by 1000 will allow us to retrieve how many updates per second we need.
			
		double decimal = framerate - (long) framerate; //This removes the whole number from the double and stores the decimal part in decimal.
			System.out.println("this is our decimal:" + decimal);
		if (framerate > 0) {
				
			frame_rate_milli = (long) framerate;
			frame_rate_nano = (int) ((decimal) * 1000);
			//System.out.println("framerate milli" + frame_rate_milli);
		//	System.out.println("framerate nano" + frame_rate_nano);
			return true;
		} else
			return false;
	}
	
	
	/**
	 * Returns the max frame rate.
	 * @return Returns the max frame rate.
	 */
	public double getMaxFrameRate() {
		return max_framerate;
	}
	
	/**
	 * Returns the current amount of frames.
	 * @return Returns the current amount of frames.
	 */
	
	public static int getFrames() {
		return frames;
	}
		
	
	/**
		* Used for initializing Actor's at the start of the game.
	 */


		
	/**
	* Get function for the JFrame width
	* @return Returns the width dimension of the window.
	*/
		
	public static int getScreenWidth() {
		return game_frame.getWidth();
	}
		
		
	/**
	* Get function for the JFrame height
	* @return Returns the height dimension of the window.
	*/
		
	public static int getScreenHeight() {
		return game_frame.getHeight();
	}
	
	
	/**
	 * Retrieves the computer's current frame rate.
	 * @return Returns the computer's current frame rate.
	 */
	
	public static int getFrameRate() {
		return current_frame_rate;
	}

	/**
	 * Implementation of Runnable's abstract method run.
	 */
	@Override
	public void run() {
		renderScene();
		
	}
	/**
	 * Starts the game's rendering on a separate thread. Use this method when initializing the rendering of the scene.
	 */
	public void start() {
		rend_thread = new Thread(new Render(window_title,max_framerate,window_width, window_height));
		rend_thread.start();
	}
	
}


