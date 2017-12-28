import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public  class Snake extends SnakeObject{
	
	public Snake(int width, int height, int xpos, int ypos, Color col) {
		super(width, height, xpos, ypos, col,-1);
	}
	
	protected static boolean isAlive = true;
	
	private static boolean isMovingNegY = false;
	private static boolean isMovingPosY = false;
	private static boolean isMovingNegX = false;
	private static boolean isMovingPosX = false;

	private static ArrayList<SnakeObject> snakeobjects = new ArrayList<SnakeObject>();
	private static int speedy;
	private static int speedx;	
	private static int ystat;
	
	private static int xstat;
	
	private static Collisions collis = new Collisions();
	public 	Graphics2D snak = null;

	public void createGraphics1() {
		snak = Render.bi.createGraphics();
		super.ychange =ystat + super.y;
		super.xchange = xstat + super.x;
		snak.setColor(Color.ORANGE);
		snak.fillRect(super.xchange,super.ychange,w,h);	
	}
	
	public void velocityfunction(int speed, int dir) {
		
		if (dir == 0){
			speedy = speed;
			ystat+= speedy;
		}
		if(dir ==1) {
			speedy = speed;	
			ystat += speedy;
		}
		if(dir == 2) {
			speedx = speed;
			xstat+= speedx;
		}
		if(dir == 3) 
		{
			speedx = speed;
			xstat +=speedx;
		}
	}
	public int getSpeedY() {
		return speedy;
		
	}
	public int getSpeedX() {
		return speedx;
	}
	private void moveX() {
		x += speedx;
	}
	private void moveY() {
		y += speedy;
	}
	public void setSpeedX(int speed) {	
		speedx = speed;
		isMovingPosY = false;
		isMovingPosY = false;
			
		if(speed > 0) 
				isMovingPosX = true;
			else
				isMovingPosX =false;
			if(speed< 0)
				isMovingNegX = true;
			else
				isMovingNegX = false;
		moveX();

	}	
	
	public void setSpeedY(int speed) {
		speedy = speed;
		isMovingPosX = false;
		isMovingNegX = false;
			if(speed < 0)  {
				
				isMovingPosY = true;
			}else
				isMovingPosY =false;
			if(speed> 0) {
				
				isMovingNegY = true;
			}
			else
				isMovingNegY = false;
		moveY();

	}	
	public boolean getDir(int i) {
		switch(i) 
		{
		case 0:
			return isMovingPosY;
		case 1:
			return isMovingNegY;
		case 2:
			return isMovingPosX;
		case 3:
			return isMovingNegX;
		}
		return false;
	}

	
	public  boolean hasCollided (Snake p1, ArrayList<SnakeObject> snos) { // CHANGE TO INT FUNCITON 
		for (SnakeObject sn0 : snos) {
			int[] velocity = {speedx, speedy};
			int[] coordinatep1 = {p1.getX(), p1.getY()};
			int[] sn0coordinate = {sn0.getX(), sn0.getY()};
			//add for loop
			if (collis.findCollision(sn0, p1,velocity,coordinatep1, sn0coordinate)) {
			return true;
				
				} else {
					return false;
				}
		}
		return false;
	}
	public void collisionDetection(Snake p1, ArrayList<SnakeObject> snos) { // make this an arraylist of existing snake object
		checkBounds(this, super.h, super.w);
		for (SnakeObject sn0 : snos) {
		int[] velocity = {speedx, speedy};
		int[] coordinatep1 = {p1.getX(), p1.getY()};
		int[] sn0coordinate = {sn0.getX(), sn0.getY()};
		//add for loop
		if (collis.findCollision(sn0, p1,velocity,coordinatep1, sn0coordinate)) {
			}
		if (snakeobjects.size() > 0) {
			followTheLeader(snakeobjects, this);
			}
		}
	}
	public static void addSnake(SnakeObject so) {
		snakeobjects.add(new SnakeObject(so.getWidth(), so.getHeight(),so.getX() - 9000,so.getY() - 10, Color.WHITE, so.getCount() + 1));
		snakeobjects.add(new SnakeObject(so.getWidth(), so.getHeight(),so.getX() - 20000,so.getY() - 20, Color.WHITE, so.getCount() + 1));
		snakeobjects.add(new SnakeObject(so.getWidth(), so.getHeight(),so.getX() - 30000,so.getY() - 30, Color.WHITE, so.getCount() + 2));
		snakeobjects.add(new SnakeObject(so.getWidth(), so.getHeight(),so.getX()-40000,so.getY()- 40, Color.WHITE, so.getCount() + 3));

	}
	private void followTheLeader(ArrayList<SnakeObject> snos, Snake snake) { // makes the follwing snakeobject follow the previous snakeobject
		for (int i = 0; i < snakeobjects.size(); i++) {
			if (snakeobjects.get(i).hasCollidedSnake(this,snakeobjects.get(i)))
				Render.endGame(null);
			if (i==0) {
				if (isMovingNegY) {
					snakeobjects.get(i).setY(this.getY(), i);
					snakeobjects.get(i).setX(this.getX(), i);
				}
				if (isMovingPosY) {
					snakeobjects.get(i).setY(this.getY(), i);
					snakeobjects.get(i).setX(this.getX(), i);
				}
				if (isMovingNegX) {
					snakeobjects.get(i).setX(this.getX(), i);
					snakeobjects.get(i).setY(this.getY(), i);
					} 
				if(isMovingPosX) {
					snakeobjects.get(i).setX(this.getX(), i);
					snakeobjects.get(i).setY(this.getY(), i);		
					}
				
				
			} else {
				if (isMovingNegY) {

					snakeobjects.get(i).setX(snakeobjects.get(i-1).getX(), i);
					snakeobjects.get(i).setY(snakeobjects.get(i-1).getY(), i);

				}

				
					if (isMovingPosY) {

						snakeobjects.get(i).setX(snakeobjects.get(i-1).getX(), i);
						snakeobjects.get(i).setY(snakeobjects.get(i-1).getY(), i);

					}
					if (isMovingNegX) {

					snakeobjects.get(i).setY(snakeobjects.get(i-1).getY(), i);
					snakeobjects.get(i).setX(snakeobjects.get(i-1).getX(), i);
						} 
					if(isMovingPosX)
						{
					
						snakeobjects.get(i).setY(snakeobjects.get(i-1).getY(), i); 
						snakeobjects.get(i).setX(snakeobjects.get(i-1).getX(), i);
				}
			} 
		}
	}

	public int getSnakeCount() {
		return snakeobjects.size();
	}
	public void renderSnakeObjects() { // renders each and every snakeobject
		for (int i = 0; i < snakeobjects.size(); i++) 
			snakeobjects.get(i).createGraphics();
	}
	private void checkBounds(Snake snake, int height, int width) {
		if ((super.getY() <= 0) || super.getY() + height >= Render.bi.getHeight() - 1)
			isAlive = false;

		if ((super.getX() <= 0) || super.getX() +width >= Render.bi.getWidth() - 1)
			isAlive = false;
		
	}
	public boolean isAlive(){
		return isAlive;
	}
}
