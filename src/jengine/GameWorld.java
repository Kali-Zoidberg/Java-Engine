package jengine;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * 
 * Implement hash function for game_object list.
 */

public class GameWorld {
	
	public static ArrayList<Actor> actor_list = new ArrayList<Actor>();
	public static ArrayList<UserInterface> ui_list = new ArrayList<UserInterface>();
	public static Hashtable<String, GameObject> game_obj_table = new Hashtable<String, GameObject>();
	private static int world_width = 1920 + 1;
	private static int world_height = 1080 + 1;
	private static String[][] world_coordinate = new String [world_width][world_height];
	
	/**
	 * Constructs a game world with a specified width and height.
	 * @param world_width The Width of the world in integers. This is suppose to represent a coordinate space.
	 * @param world_height The height on the world in integers. This is suppose to represent a coordinate space.
	 */
	
	public GameWorld(int world_width, int world_height) {
		GameWorld.world_width = world_width + 1; //Add one just in case the position exists. Might be able to be removed.
		GameWorld.world_height = world_height + 1;
		world_coordinate = new String[world_width][world_height];
		
	}
		
	public static void printWorldCoordinates() {
		
		for (int i = 0; i < world_width; ++i) {
			for (int j = 0; j < world_height; ++j) {
				if (world_coordinate[i][j] != "-1") {
					System.out.print(world_coordinate[i][j] + " ");
					if ((j % 10) == 0)
						System.out.print("\n");
				}
			}
		}
		
	}
	
	/**
	 * Checks to see if the specified coordinates are out of bounds.
	 * @param x The x position to check.
	 * @param y The y position to check.
	 * @return Returns true if the object is not out of bounds. Otherwise, it returns false.
	 */
	public static boolean isInWorldCoordinateBounds(int x, int y) {
		if ((x > world_width - 1) || (y > world_height - 1) || x < 0 || y < 0)
			return false;
		else
			return true;
		}	
	
	/**
	 * Sets the specified world coordinate to true such that an object is represented.
	 * @param x The x-coordinate.
	 * @param y The y-coordinate.
	 * @param occupied Set to true if you want the specified xy coordinate to be occupied.
	 */
	
	public static void setWorldCoordinate(int x, int y, String obj_name) {
		
		if (!isInWorldCoordinateBounds(x,y))  {
			return;
		} else
			world_coordinate[x][y] = obj_name;

	}
	
	/**
	 * Determines if a specified position in the game world is occupied by another actor.
	 * @param x The X-coordinate in the game world
	 * @param y The Y-coordinate in the game world.
	 * @return Returns the occupation status of the coordinate represented in the game world. Returns "Error, out of bounds." if the desired location 
	 * cannot exist on the array.
	 */
	
	public static String GetWorldCoordinate(int x, int y) {
	
		if (!isInWorldCoordinateBounds(x,y)) 
			return "Error, out of bounds.";
		else
			return world_coordinate[x][y];
	
	}
	
	/**
	 * Initializes the world coordinates to -1.
	 */
	
	public static void initializeWorldCoordinates() {
		for (int i = 0; i < world_width; ++i)
			for (int j = 0; j < world_height; ++j) {
				world_coordinate[i][j] = "-1";
			}
	}
	
	//public void setSpace(int)
	
	/**
	 * Retrieves the index of the specified actor name.
	 * @param name
	 * @return Returns the index of the Actor in the array list. Returns -1 if the actor is not found.
	 */
	
	public static int getActorIndex(String name) {
		int list_size = actor_list.size();
		
		for (int i = 0; i < list_size; ++i) {
			if (name.equals(actor_list.get(i).getName())) {
				return i;
			}
		}
		
		return -1;
	}
	
	
	/**
	 * Retrieves the index of the specified UserInterface object name.
	 * @param name
	 * @return Returns the index of the UserInterface object in the array list. Returns -1 if the UserInterface object is not found.
	 */
	
	public static int getUIIndex(String name) {
		int list_size = ui_list.size();
		
		for (int i = 0; i < list_size; ++i) {
			if (name.equals(ui_list.get(i).getName())) {
				return i;
			}
		}
		
		return -1;
	}
	
	
	/**
	 * Returns the world width.
	 * @return Returns world width.
	 */
	
	public static int getWorldWidth() {
		return world_width;
	}
	
	
	/**
	 * Returns the world height.
	 * @return Returns the world height.
	 */
	
	public static int getWorldHeight() {
		return world_height;
	}
	
	
	/**
	 * Returns the current width of the GameWindow frame.
	 * @return Returns the current width of the GameWindow frame.
	 */
	
	public static int getCurrentWidth() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
		return screenSize.width;
	}
	
	
	/**
	 * Returns the current height of the GameWindow frame.
	 * @return Returns the height width of the GameWindow frame.
	 */
	
	public static int getCurrentHeight() {
		int height = Render.getScreenHeight();
		return height;
	}
	
	/**
	 * Returns a scaled double to convert coordinate positions to screen coordinates.
	 * @return Returns a scaled double.
	 */
	
	public static double getViewportScaleX()
	{
		double world_width = getWorldWidth();
		return (Render.getScreenWidth() / world_width);
	}
	/**
	 * Returns a scaled double to convert coordinate positions to screen coordinates.
	 * @return Returns a scaled double.
	 */
	
	public static double getViewportScaleY()
	{
		double world_height = getWorldHeight();
		return (Render.getScreenHeight()/ world_height);
	}
	
	
	
}
