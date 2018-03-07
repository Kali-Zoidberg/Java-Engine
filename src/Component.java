/*
 * Component is the base class for all component entities. They have a parent GameObject called Mentor.
 * All Components have an instance ID
 * How should instanceID's be computed? Handled by actor or game object. Should they only be within access of their mentor class?
 */
public class Component {
	protected GameObject Mentor;
	private int instanceID;
	protected String name = "Default";
	
	/**
	 * Default constructor for Components. No mentor is set to the object.
	 */
	Component() {
		Mentor = null;
	}
	
	/**
	 *  Constructor for Components with a specified mentor. 
	 * @param mentor The Parent or mentor of the component.
	 */
	Component(GameObject mentor) {
		Mentor = mentor;
	}
	
	
	/**
	 * Retreives the instance ID of the component.
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
	}
	
	public GameObject getMentor() {
		
		return Mentor;
	}
}
