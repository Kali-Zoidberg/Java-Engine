package jengine;

public class Trigger extends GameObject{
	private double radius = 1;
	private boolean hasEntered = false;
	private Runnable enteredFunction = null;
	private Runnable exitFunction = null;
	
	public Trigger(double x, double y, double radius, String name) {
		super(x, y, name);
		this.setRadius(radius);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		//use SAT to figure out if the player has entered
		/*
		  if (isInside(player) && enteredFunction != null)
		  {
		  		hasEntered = true;
		  		enteredFunction.run()
		  } else
		  {
		  	if (hasEntered && exitFunction != null)
		  	{
		  			exitFunction.run()
		  			hasEntered = false;
		  	}
		  }
		 */
	}

	public boolean isInside(Actor actorA)
	{
		return false;
	}
	
	public double getRadius() {
		return radius;
	}

	/**
	 * The radius for the trigger
	 * @param radius The radius for the trigger
	 */
	public void setRadius(double radius) {
		
		this.radius = radius < 1 ? 1: radius;
	}

	/**
	 * Returns the state on whether or not the trigger has been activated by entrance.
	 * @return
	 */
	public boolean isHasEntered() {
		return hasEntered;
	}
	
	/**
	 * Sets the enter function/runnable for the trigger. This function pointer/runnable is called upon entrance of the trigger
	 * @return Returns the runnable object that contains the code for what to do upon entrance
	 */
	public Runnable getEnteredFunction() {
		return enteredFunction;
	}
	/**
	 * Sets the enter function/runnable for the trigger. This function pointer/runnable is called upon entraced or start of the trigger.
	 * @param enteredFunction A runnable object that contains your code for what to do upon entrance of the trigger.
	 */
	public void setEnteredFunction(Runnable enteredFunction) {
		this.enteredFunction = enteredFunction;
	}
	/**
	 * Returns the Runnable exit function object. This function is called within exit of the trigger
	 * @return Returns the runnable exit function
	 */
	public Runnable getExitFunction() {
		return exitFunction;
	}
	/**
	 * Sets the runnable exit function object. this runnable is executed upon the exit of the trigger.
	 * @param exitFunction
	 */
	public void setExitFunction(Runnable exitFunction) {
		this.exitFunction = exitFunction;
	}

}
