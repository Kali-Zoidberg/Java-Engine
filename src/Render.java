import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Render implements Runnable{
	private static Random random = new Random();
	public static BufferedImage bi;
	public static JLabel PaddlePlayer = new JLabel(""); 
	private static int frames = 0;
	public static ArrayList<SnakeObject> snakeobjects = new ArrayList<SnakeObject>();
	private static ArrayList<SnakeObject> emptysnakeobjects = new ArrayList<SnakeObject>();
	public static ArrayList<Actor> actor_list = new ArrayList<Actor>();
	public static ArrayList<UserInterface> ui_list = new ArrayList<UserInterface>();
	private static int direction = 0;
	private static int velocity = -10;
	private static int totalframes = 0;
	private static int count =0;
	private static boolean GameOver = false;
	protected static int points = 0;
	private static final int SCREEN_WIDTH = 1500;
	private static final int SCREEN_HEIGHT = 1000;
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
	public static Snake sn;

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

	Render(String frame_title, double max_framerate, int initial_window_width, int initial_window_height) {
		
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
			
			if(diff_time >= update_rate) {
				diff_frame = frames - init_frame;
				frames = 0;
				init_frame = frames;
				System.out.println("frame rate: " + diff_frame);
				init_time = System.currentTimeMillis();
			}
			
			if (!game_frame.isFocused())
				game_window.pause(true);
			else
				game_window.pause(false);
			
			if (!game_window.isPaused()) {
				game_window.Render(GameWorld.actor_list, GameWorld.ui_list);
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
		System.out.println("init frame count: " + frames);
		int cur_frame = 0;
		int frame_diff = 0;
		//Thread.sleep(update_rate);
		System.out.println("final frame count: " + frames);
		cur_frame = frames;
		
		frame_diff = cur_frame - init_frame;
		System.out.println("Frame rate: " + frame_diff);
	}
	
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
	
	public static void Renderframes(int frameratecap) throws InterruptedException {
			
			current_frame_rate = frameratecap;
			
			frameratecap = 37;
			Controls controller = new Controls();
			controller.initializeControls();
			JFrame frame = new JFrame();
			Canvas canvas = new Canvas();
			canvas.setIgnoreRepaint(true);
			canvas.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
			frame.add(canvas); //add canvas to jframe
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack(); // pack frame
			
			canvas.createBufferStrategy(2); // render canvas
			BufferStrategy buffer = canvas.getBufferStrategy();
			
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice gd = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gd.getDefaultConfiguration();
			
			bi = gc.createCompatibleImage(SCREEN_WIDTH, SCREEN_HEIGHT); // create background
	
			Graphics graphics = null;
			Graphics2D fpscounter = null; // create fps counter
		
			Color background = Color.BLACK;
			
			
			//Random rand = new Random();
			frame.setVisible(true);
			int fps = 0;
			double totalTime = 0;
			double curTime = System.currentTimeMillis();
			double lastTime = curTime;
			frame.setFocusable(true);
			double totalTime2 = 0;
			fpscounter = bi.createGraphics();
			//Start();
			while (!GameOver) { // increase difficulty loop
				if (count >= 5) {
					if (count >= 5 && count < 10)
					frameratecap = 15;
					if (count <15 && count >= 10) 
						frameratecap = 20;
					
					if (count >= 15) 
						frameratecap = 30;
					
				}
				if (frame.isFocused()){ // if player lcikcs out, game pauses
				try {
				
					lastTime = curTime;
					curTime = System.currentTimeMillis(); 
					totalTime += curTime - lastTime;
					totalTime2 += curTime - lastTime;
					if (totalTime > 1000) {
						System.out.println("framerate: " + frames);

						totalTime -= 1000;
						fps = frames;
		
						frames = 0;
					}
					if (totalTime2 > (1000 / frameratecap)) { // do the frame rate
						totalTime2 -= (1000/frameratecap);
					frames++;
					totalframes++;
					
			
				fpscounter.setColor(background);
				fpscounter.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
			
				//MiscGameLogic();
				//Start();
				renderActors();
				frame.getContentPane().add(Main.focusWindow);		
				fpscounter.setFont(new Font("Courier New", Font.PLAIN, 12));
				fpscounter.setColor(Color.GREEN);
				fpscounter.drawString(String.format( "Points: %s", points), 20, 20);

				graphics = buffer.getDrawGraphics();
				deRenderGameObjects();

				graphics.drawImage(bi,0,0,null);

				if (!buffer.contentsLost()) 
					buffer.show();

				Thread.sleep(10);
					}
				
				} finally {
					if (totalTime2 > (1000 / frameratecap)) {
						
						totalTime2 -= (1000/frameratecap);
					if (graphics != null);					}
				
				if (fpscounter != null)
					if (totalTime2 > (1000 / frameratecap)) {
						
						totalTime2 -= (1000/ frameratecap);
					fpscounter.dispose(); // dispose of fps counter
					}
				}
			} else {
				lastTime = curTime;
				curTime = System.currentTimeMillis();
				totalTime += curTime - lastTime;
				
				if (totalTime > 1000) {
					totalTime -= 1000;
					current_frame_rate = frames;
					fps = frames;
					frames = 0;
					
					}
				}
			}
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
			System.out.println("framerate milli" + frame_rate_milli);
			System.out.println("framerate nano" + frame_rate_nano);
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
		 * Renders all the actors in the scene.
		 */
	private static void renderActors() {
		for (Actor act: actor_list) {
			if (act.isVisible()) {
				act.renderActor();
			}
		}
		for (UserInterface ui: ui_list) {
			ui.renderText();
		}
	}
		
	private static void deRenderGameObjects() {
		for (Actor act: actor_list) {
			//if (act.isVisible())
				act.actor_graphics.dispose();
		}
	}
	/**
		* Used for initializing Actor's at the start of the game.
	 */
	public void test_start() throws IOException{
			
		Transform transform_test = new Transform(750,500,0f);
		File sprite_img = new File("sprite_test.jpg");
		BufferedImage car_bg = ImageIO.read(sprite_img);

		//UserInterface ui_test = new UserInterface(60,60,0.0,"Hello world!",Color.WHITE,	new Font("Courier New", Font.PLAIN, 12));
		Actor player = new Actor(transform_test,Color.GREEN, "test actor",50,50);
		
				
		player.transform.setX(player.transform.getX());
		player.transform.setY(player.transform.getY());
		player.setVisible(true);
		Sprite sprite_test = new Sprite(car_bg,50,50);
		player.setSprite(sprite_test);
		//player.transform.setDirection(0.86f);

		//	ui_list.add(ui_test);
		//GameWorld.actor_list.add(player);
		System.out.println("Actor index: " + GameWorld.getActorIndex("test actor"));

		

			
		//emptysnakeobjects.add(new SnakeObject(10,10,30,50, Color.WHITE, 0)); // add snake object on screen to 'eat'
	
	}
	/*	
		private static void MiscGameLogic() {
			
			Graphics2D gameover = null;

			sn = new Snake(10,10,screenwidth / 2 - 10,screenheight / 2 - 10, Color.WHITE); // center snake on screen
			sn.velocityfunction(velocity, direction); // set snake in motion

			if (sn.isAlive()) { // makesure snake is not dead

				sn.createGraphics1(); // render snake
				makeNewSnake(); // make empty snake object
			
				if (snakeobjects.size() < 1) {
				snakeobjects.add(new SnakeObject(10,10,320,240,Color.WHITE,0)); 
				} 
				
			} else {
				endGame(gameover);
			}

		if (snakeobjects.size() > 0 ) {
			System.out.println("this is being executed");
			sn.renderSnakeObjects(snakeobjects);
		}		
		sn.collisionDetection(sn, snakeobjects); 
			
		}
		

		*/
	/*
	 * checks for collision for the snake and snake food.
	 */
	private static void makeNewSnake() {
		emptysnakeobjects.get(0).createGraphics();
		if (sn.hasCollided(sn, emptysnakeobjects)) { 
			count++; // count how many have been added
			points++; 
			snakeobjects.add(new SnakeObject(10,10,30,40,Color.WHITE, count));
			System.out.println("snakeobj size in the render: " +snakeobjects.size());
			//sn.addSnake();
			randomizeSnakeLocation();
			
		} 
	
	}
	private static void randomizeSnakeLocation() { // randomizes the snake's locaiton
		double tempx = random.nextInt(SCREEN_WIDTH) / 10; // divide screne length by 10, then downcast it
		double tempy = random.nextInt(SCREEN_HEIGHT) / 10;
		int xpos = (int)tempx * 10; // multiplied the now down casted length
		int ypos = (int ) tempy * 10;
			
		while (xpos == sn.getX() && ypos == sn.getY() &&  (xpos !=SCREEN_WIDTH + 10 && xpos != SCREEN_WIDTH - 10) && (ypos != SCREEN_HEIGHT + 10 && ypos != SCREEN_HEIGHT - 10))  { // Keep on looping until we reach a new coordinate
			tempx = random.nextInt(SCREEN_WIDTH) / 10;
			tempy = random.nextInt(SCREEN_HEIGHT) / 10;
			xpos = (int)tempx * 10;
			ypos = (int ) tempy * 10;
				
		}
			
 		if (xpos != sn.getX() && ypos!= sn.getY()) {
				
			emptysnakeobjects.get(0).setX(xpos, 0);
			emptysnakeobjects.get(0).setY(ypos, 0);
		}
	}
		
	public static void setSpeed(int yspeed, int dir) {
		direction = dir;
		velocity = yspeed;
		
		if (dir <=1){
		sn.setSpeedY(yspeed);
		}
		
		else
			sn.setSpeedX(yspeed);
		}
	
		public static int getFrame() {
			return totalframes;
		}
		public static void endGame(Graphics2D gameover) {
			
			gameover = bi.createGraphics();
			gameover.setFont(new Font("Courier New", Font.PLAIN, 48));
			gameover.setColor(Color.WHITE);
			gameover.drawString(String.format( "GAME OVER"), SCREEN_WIDTH/2 - 128, SCREEN_HEIGHT / 2);
			GameOver = true;
		}
		
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


