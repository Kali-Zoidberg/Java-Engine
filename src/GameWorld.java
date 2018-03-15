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
	private static int world_width = 1920;
	private static int world_height = 1080;
	private static boolean[][] world_coordinate = new boolean [world_width][world_height];
	
	/**
	 * Constructs a game world with a specified width and height.
	 * @param world_width The Width of the world in integers. This is suppose to represent a coordinate space.
	 * @param world_height The height on the world in integers. This is suppose to represent a coordinate space.
	 */
	
	GameWorld(int world_width, int world_height) {
		GameWorld.world_width = world_width + 1; //Add one just in case the position exists. Might be able to be removed.
		GameWorld.world_height = world_height + 1;
		world_coordinate = new boolean[world_width][world_height];
		
	}
	
	public static void printWorldCoordinates() {
		
		for (int i = 0; i < world_width; ++i) {
			for (int j = 0; j < world_height; ++j) {
				if (world_coordinate[i][j] == true) {
					System.out.print(world_coordinate[i][j] + " ");
					if ((j % 10) == 0)
						System.out.print("\n");
				}
			}
		}
		
	}
	
	/**
	 * Sets the specified world coordinate to true such that an object is represented.
	 * @param x The x-coordinate.
	 * @param y The y-coordinate.
	 * @param occupied Set to true if you want the specified xy coordinate to be occupied.
	 */
	
	public static void setWorldCoordinate(int x, int y, boolean occupied) {
		world_coordinate[x][y] = occupied;
	}
	
	/**
	 * Determines if a specified position in the game world is occupied by another actor.
	 * @param x The X-coordinate in the game world
	 * @param y The Y-coordinate in the game world.
	 * @return Returns the occupation status of the coordinate represented in the game world.
	 */
	
	public static boolean GetWorldCoordinate(int x, int y) {
		return world_coordinate[x][y];
	}
	
	/**
	 * Initializes the world coordinates all to false.
	 */
	
	public void initializeWorldCoordinates() {
		for (int i = 0; i < world_width; ++i)
			for (int j = 0; j < world_height; ++j) {
				world_coordinate[i ][j] = false;
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
			if (name.equals(actor_list.get(i).getActorName())) {
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
	
}
