import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Render {
	private static Random random = new Random();
	public static BufferedImage bi;
	public static JLabel PaddlePlayer = new JLabel(""); 
	private static int frames = 0;
	public static ArrayList<SnakeObject> snakeobjects = new ArrayList<SnakeObject>();
	private static ArrayList<SnakeObject> emptysnakeobjects = new ArrayList<SnakeObject>();
	private static int direction = 0;
	private static int velocity = -10;
	private static int totalframes = 0;
	private static int count =0;
	private static boolean GameOver = false;
	protected static int points = 0;
	private static final int SCREEN_WIDTH = 800;
	private static final int SCREEN_HEIGHT = 600;
	public static Snake sn;

/*
 * TODO: Fix Framerate dependency. Optimize game.
 * Comment function using javadocs.
 * 
 * Fix snake objects not being rendered. Then seperate the classes so snake becomes only player controller and snake object
 * is the tail
 */

	
	public static void Renderframes(int frameratecap) throws NullPointerException {
		
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
			long totalTime = 0;
			long curTime = System.currentTimeMillis();
			long lastTime = curTime;
			frame.setFocusable(true);
			long totalTime2 = 0;

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
						
						totalTime -= 1000;
						fps = frames;
		
						frames = 0;
					}
					if (totalTime2 > (1000 / frameratecap)) { // do the frame rate
						totalTime2 -= (1000/frameratecap);
					frames++;
					totalframes++;
					
				fpscounter = bi.createGraphics();
			
				fpscounter.setColor(background);
				fpscounter.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
			
				//MiscGameLogic();
				Start();

				frame.getContentPane().add(Main.focusWindow);		
				fpscounter.setFont(new Font("Courier New", Font.PLAIN, 12));
				fpscounter.setColor(Color.GREEN);
				fpscounter.drawString(String.format( "Points: %s", points), 20, 20);
				
				graphics = buffer.getDrawGraphics();
				graphics.drawImage(bi,0,0,null);

				if (!buffer.contentsLost()) 
					buffer.show();
				
				Thread.yield();
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
					fps = frames;
					frames = 0;
					
					}
				}
			}
		}
	
		public static int getFrames() {
			return frames;
		}
	
		private static void Start() {
			
			Transform transform_test = new Transform(0,0,25,25);
			Actor player = new Actor(transform_test,Color.GREEN,50,50);
			player.transform.setX(0);
			player.transform.setY(0);
			player.setVisible(true);
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
			
 			if (xpos != sn.getX() && ypos!= sn.getY()) 
			{
				
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
		 * Accessor function for the Jframe width
		 * @return Returns the width dimension of the window.
		 */
		
		public static int getScreenWidth() {
			return SCREEN_WIDTH;
		}
		
		
		/**
		 * Accessor function for the Jframe height
		 * @return Returns the height dimension of the window.
		 */
		
		public static int getScreenHeight() {
			return SCREEN_HEIGHT;
		}
	}

