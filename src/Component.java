/*
 * Component is the base class for all component entities. They have a parent GameObject called Mentor.
 * All Components have an instance ID
 * How should instanceID's be computed? Handled by actor or game object. Should they only be within access of their mentor class?
 */


/*
 * TODO: Component types. Inheritance schemes.
 * 
 *
 */
public class Component {
	
	protected GameObject Mentor;
	private int instanceID;
	private String default_name = "Default";
	protected String name = default_name;
	protected String component_type = "Component";
	
	/**
	 * Default constructor for Components. No mentor is set to the object.
	 */
	
	Component() {
		Mentor = null;
		
	}
	/**
	 * Constructs a component with a specified name and no Mentor
	 * @param name The desired name of the component.
	 */
	Component(String name) {
		this.name = name;
	}
	
	/**
	 *  Constructor for Components with a specified mentor. 
	 * @param mentor The Parent or mentor of the component.
	 */
	
	Component(GameObject mentor) {
		Mentor = mentor;
		name = Mentor.generateNewName(name, Mentor.component_table);
		Mentor.component_table.put(name, this);
	}
	
	/**
	 * Constructs a component with a mentor and name
	 * @param mentor The mentor of the component. 
	 * @param name The name of the component
	 */
	
	Component(GameObject mentor, String name) {
		Mentor = mentor;
		this.name = Mentor.generateNewName(name, Mentor.component_table);
		Mentor.component_table.put(name, this);
	}
	
	
	/**
	 * Retrieves the instance ID of the component.
	 * @return Returns the instanceID of the component.
	 */
	
	public int getInstanceID() {
		return instanceID;
	}
	
	
	/**
	 * Sets the component's instance ID if it has not already been taken by another Mentor's component.
	 * @param id The new instance ID for the component
	 * @return Returns true if the instance ID has not already been taken by another one of the Mentor's components. Otherwise, it returns false.
	 */
	
	public boolean setInstanceID(int id) {
		/*
		 * if mentor.find(instanceid) == false
		 * 	set instance id & return true
		 * else
		 *  return false
		 */
		instanceID = id;
		return true;
	}
	
	
	/**
	 * Sets the Mentor of the Component
	 * @param mentor The mentor of the component (parent)
	 */
	
	public void setMentor(GameObject mentor) {
		Mentor = mentor;
		Mentor.component_table.put(name, this);
	}
	
	/**
	 * Returns the Mentor of the component.
	 * @return Returns the Mentor of the component.
	 */
	
	public GameObject getMentor() {
		
		return Mentor;
	}
	
	/**
	 * Sets the name of the Component
	 * @param name_str The desired name for the component.
	 * @return Returns a modified name if the desired name has already been taken by another Mentor's Component.
	 */
	
	public String setName(String name_str) {
		
		if (Mentor != null) {
			name = Mentor.generateNewName(name_str, Mentor.component_table);
		
		} else
			name = name_str;
		
		return name;
	}
	
	/**
	 * Returns the name of the component.
	 * @return Returns the name of the component.
	 */
	
	public String getName() {
		return name;
	}

	/**
	 * Sets the Component type to the desired type.
	 * @param component_str The desired component type as a String.
	 */
	
	public void setComponentType(String component_str) {
		component_type = component_str;
	}
	
	/**
	 * Returns the type of component that the component is.
	 * @return Returns the type of component.
	 */

	public String getComponentType() {
		return component_type;
	}
	
}
