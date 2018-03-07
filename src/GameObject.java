import java.util.Hashtable;

/**
 * All Actors and subsequent entities inherit from this class.
 * All GameObjects contain a name, a string representing the object type, a transform, and an instance ID (within the game world)
 * Name : A string to represent the game object.
 * 		-Note: Duplicate names are allowed as instance ID's and names will be generated using hash functions.
 * Instance ID: 
 * All game objects have a list of components but have also have specified components like Transform. 
 * GameObject names CANNOT be changed during runtime. This prevents invalid hash assignments.
 * Currently ID is uninitialized as names are sufficient keys as of right now. We can, however implement ID's based on the order at
 * which objects are placed into the Hashtable.
 * All GameObjects have a hashtable of components as well.
 * @author Nicholas Chow
 * 
 */
public class GameObject {
	private int id;
	protected String name;
	private String obj_type = "GameObject";
	private int component_count = 1;
	protected Transform transform = new Transform();
	public Hashtable<String, Component> component_table = new Hashtable<String,Component>();
	
	/**
	 * Constructs a GameObject with a givne name.
	 * @param name The name to give the string object.
	 */
	
	GameObject(String name) {
		this.name = generateNewName(name, GameWorld.game_obj_table);
		GameWorld.game_obj_table.put(this.name, this);
	}
	/**
	 * Constructs a GameObject with a name and transform.
	 * @param name
	 * @param transform
	 */
	GameObject(String name, Transform transform) {
		
		this.name = generateNewName(name, GameWorld.game_obj_table);
		this.transform = transform;
		
		GameWorld.game_obj_table.put(this.name, this);
	//	id = GameWorld.game_obj_table.hashCode();
	}
	
	/**
	 * Generates a new name for the object is a duplicate name exists.
	 * @param name_str The original name of the GameObject
	 * @return Returns the modified name for the GameObject
	 */
	
	public String generateNewName(String name_str, @SuppressWarnings("rawtypes") Hashtable table) {
		int instance_count = 1;
		
		while (table.containsKey(name_str)) {
			name_str = name_str + " (" + instance_count + ")";
		}
		
		return name_str;
	}
	
	/**
	 * Returns the id of the object.
	 * @return Returns the id of the object.
	 */
	
	public int getID() {
		return id;
	}
	
	/**
	 * Returns the name of the object. This is also the key value for the object.
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	public String getObjType() {
		return obj_type;
	}
	/**
	 * Sets a new name for the GameObject.
	 * @param name_str The new name for the GameObject
	 * @return Returns the new name for the GameObject. If it has already been taken, it is renamed.
	 */
	
	public String setName(String name_str) {
		name = generateNewName(name_str, GameWorld.game_obj_table);
		return name;
	}
	
	/**
	 * Returns the number of components of the GameObject.
	 * @return Returns the number of components of the GameObject.
	 */
	public int getComponentCount() {
		return component_count;
	}
	
	/**
	 * Using this method you may set a new count or simply increment the component count.
	 * @param updated_count The new updated number of Components
	 * @return Returns true if the updated count is greater than or equal to 0.
	 */
	public boolean setComponentCount(int updated_count) {
		if (updated_count >= 0) {
			component_count = updated_count;
			return true;
		} else
			return false;
	}
	
}
