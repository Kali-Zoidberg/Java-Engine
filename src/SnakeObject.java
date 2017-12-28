import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;




public  class SnakeObject {
	
	protected  int y = 0;
	protected  int x = 0;
	protected int h = 0;
	private  int xp;
	private  int yp;
	protected int w = 0;
	public int ychange;
	public int xchange;
	private int count;
	private static ArrayList<PositionsArray> xpos = new ArrayList<PositionsArray>();
	private static ArrayList<PositionsArray> ypos = new ArrayList<PositionsArray>();

	protected Color color;
	protected  Graphics2D snakgraph2d;
	private static Collisions collis = new Collisions();
	
	public SnakeObject(int width, int height, int xpos, int ypos, Color col, int count) {
		h = height;
		w = width;
		x = xpos;
		y = ypos;
		ychange = y;
		xchange = x;
		color = col;
		if (count != -1) {
		this.count = count;
		SnakeObject.xpos.add(new PositionsArray(xpos));
		SnakeObject.ypos.add(new PositionsArray(ypos));
		
		}
	}
		
	public void createGraphics() { 
		snakgraph2d = Render.bi.createGraphics();
		
		ychange = yp + x;
		xchange = xp + y;
		snakgraph2d.setColor(color);
		snakgraph2d.fillRect(xchange,ychange,w,h);	
	}
	public static boolean hasCollidedSnake (Snake sn, SnakeObject sno) { // CHANGE TO INT FUNCITON
			int[] velocity = {sn.getSpeedX(), sn.getSpeedY()};
			int[] coordinatep1 = {sn.getX(), sn.getY()};
			int[] sn0coordinate = {sno.getX(), sno.getY()};
			//add for loop
		if (collis.findCollision(sno, sn,velocity,coordinatep1, sn0coordinate)) {
			
			return true;
			} else {
				return false;
			}
		}
	
	public int getCount() {
		return count;
	}
	public void setX(int x, int index) {
		this.x = 0;
		xp = x;
	}
	public void setY(int y, int index) {
		this.y = 0;
		yp = y; 
	}
	public int getY() {
		return this.ychange;
	}
	public int getX() {
		return this.xchange;
	}
	public int getWidth() {
		return w;
	}
	public int getHeight() {
		return h;
	}
}
