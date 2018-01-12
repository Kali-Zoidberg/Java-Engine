import java.util.ArrayList;


public class GameWorld {
	public static ArrayList<Actor> actor_list = new ArrayList<Actor>();
	public static ArrayList<UserInterface> ui_list = new ArrayList<UserInterface>();
	private static int world_width;
	private static int world_height;
	private static boolean[][] world_coordinate;
	
	/**
	 * Constructs a game world with a specified width and height.
	 * @param world_width The Width of the world in integers. This is suppose to represent a coordinate space.
	 * @param world_height The height on the world in integers. This is suppose to represent a coordinate space.
	 */
	
	GameWorld(int world_width, int world_height) {
		this.world_width = world_width + 1; //Add one just in case the position exists. Might be able to be removed.
		this.world_height = world_height + 1;
		world_coordinate = new boolean[world_width][world_height];
		
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
			if (name.equals(ui_list.get(i).getUIName())) {
				return i;
			}
		}
		
		return -1;
	}
}
