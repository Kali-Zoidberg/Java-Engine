import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public  class Snake extends SnakeObject{
	
	public Snake(int width, int height, int xpos, int ypos, Color col, Render rend) {
		super(width, height, xpos, ypos, col,-1, rend);
		
	}
	
	protected static boolean isAlive = true;
	
	public boolean isMovingNegY = false; //Convert to enum
	public  boolean isMovingPosY = false;
	public  boolean isMovingNegX = false;
	public  boolean isMovingPosX = false;

	private ArrayList<SnakeObject> snakeobjects = new ArrayList<SnakeObject>();
	private static int speedy;
	private static int speedx;	
	private static int ystat;
	
	private static int xstat;
	
	private static Collisions collis = new Collisions();
	public Graphics2D snak = null;
	private Render render_obj = super.getRenderObj();
	
	public void createGraphics1() {
		snak = render_obj.bi.createGraphics();
		super.ychange = ystat + super.y;
		super.xchange = xstat + super.x;
		snak.setColor(Color.PINK);
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
			
		if (speed > 0) 
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
				isMovingPosY = false;
			if(speed> 0) {
				
				isMovingNegY = true;
			}
			else
				isMovingNegY = false;
			
			System.out.println("is y: " + this.isMovingPosY);
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

	
	public boolean hasCollided (Snake p1, ArrayList<SnakeObject> snos) { // CHANGE TO INT FUNCITON 
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
		checkBounds(p1, super.h, super.w);
		for (SnakeObject sn0 : snos) {
		int[] velocity = {speedx, speedy};
		int[] coordinatep1 = {p1.getX(), p1.getY()};
		int[] sn0coordinate = {sn0.getX(), sn0.getY()};
		//add for loop
		if (collis.findCollision(sn0, p1,velocity,coordinatep1, sn0coordinate)) {
			}
		if (snos.size() > 0) {
			p1.followTheLeader(snos, p1); //update every frame
			
			}
		}
	}
	public void addSnake(SnakeObject so) {
		snakeobjects.add(new SnakeObject(so.getWidth(), so.getHeight(),so.getX() - 9000,so.getY() - 10, Color.WHITE, so.getCount() + 1, render_obj));
		snakeobjects.add(new SnakeObject(so.getWidth(), so.getHeight(),so.getX() - 20000,so.getY() - 20, Color.WHITE, so.getCount() + 1, render_obj));
		snakeobjects.add(new SnakeObject(so.getWidth(), so.getHeight(),so.getX() - 30000,so.getY() - 30, Color.WHITE, so.getCount() + 2, render_obj));
		snakeobjects.add(new SnakeObject(so.getWidth(), so.getHeight(),so.getX() - 40000,so.getY()- 40, Color.WHITE, so.getCount() + 3, render_obj));

	}
	
	public void followTheLeader(ArrayList<SnakeObject> snos, Snake snake) { // makes the follwing snakeobject follow the previous snakeobject
		for (int i = 0; i < snos.size(); i++) {
			if (snos.get(i).hasCollidedSnake(snake, snos.get(i)))
				setAlive(false);
				if (i==0) {
				System.out.println("isMovingNegY" + snake.getDir(1));
				if (snake.getDir(1)) {
					System.out.println("neg y");

					snos.get(i).setY(snake.getY(), i);
					snos.get(i).setX(snake.getX(), i);
				}
				if (snake.getDir(0)) {
					System.out.println("neg y");

					snos.get(i).setY(snake.getY(), i);
					snos.get(i).setX(snake.getX(), i);
				}
				if (snake.getDir(3)) {
					System.out.println("neg y");

					snakeobjects.get(i).setX(snake.getX(), i);
					snakeobjects.get(i).setY(snake.getY(), i);
					} 
				if(snake.getDir(2)) {
					snos.get(i).setX(snake.getX(), i);
					snos.get(i).setY(snake.getY(), i);		
					}
				
				
			} else {
				System.out.println("more than one object");
				if (snake.getDir(1)) {
					System.out.println("neg y2");

					snos.get(i).setX(snos.get(i-1).getX(), i);
					snos.get(i).setY(snos.get(i-1).getY(), i);

				}

				
					if (snake.getDir(0)) {
						System.out.println("neg 3");

						snos.get(i).setX(snos.get(i-1).getX(), i);
						snos.get(i).setY(snos.get(i-1).getY(), i);

					}
					if (snake.getDir(3)) {
						System.out.println("neg yx");

						snos.get(i).setY(snos.get(i-1).getY(), i);
					snos.get(i).setX(snos.get(i-1).getX(), i);
						} 
					if(snake.getDir(2))
						{
						System.out.println("neg yxx");
					
						snos.get(i).setY(snos.get(i-1).getY(), i); 
						snos.get(i).setX(snos.get(i-1).getX(), i);
				}
			} 
		}
	}

	public int getSnakeCount() {
		return snakeobjects.size();
	}
	public void renderSnakeObjects(ArrayList<SnakeObject> snos) { // renders each and every snakeobject
		for (int i = 0; i < snos.size(); i++) {
	
			snos.get(i).createGraphics();
			
			System.out.println("sizeof snakeobjects: " + snos.size());
		}
	}
	/*
	 * Both
	 * 
	 * this should be under the render class
	 */
	private void checkBounds(Snake snake, int height, int width) { 
		if ((snake.getY() <= 0) || snake.getY() + height >= render_obj.bi.getHeight() - 1)
			 isAlive = false;

		if ((super.getX() <= 0) || super.getX() +width >= render_obj.bi.getWidth() - 1)
			isAlive = false;
		
	}
	private void setAlive(boolean living_stat) {
		isAlive = living_stat;
	}
	public boolean isAlive(){
		return isAlive;
	}

}
