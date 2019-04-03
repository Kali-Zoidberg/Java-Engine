# OldSnakey
This game was created in 2015. Over time I will optimize and improve the re-usability of this code as well as properly comment the source code.



The following write up will be updated over the course of the next two months.

**Component Based Architecture:**</br>
The base class for all parent objects is a game object. Parents are parameterized as mentors. </br>
The base class for child objects is component. Children are parameterized as component.</br>
Below is a code snippet for the actual implementation of the component based architecture.</br>
![](https://i.imgur.com/0u7bTDk.png)
This architecture allows for both the parent (mentor) and child (component) to have direct access to one another and invoke their appropiate functions.</br>



Re-Write: Over the course of this program, I will rewrite the code with re-usability in mind. I am hoping to achieve a
basic game engine in Java using JFrames. However, parallel computing using multi-threads may be implemented to improve the optimization.
The focus of the multi-threading/parallel computing will be to handle the updated game states such as Vectors, Transforms and Game Logic.

To do: On resize, recalculate averages and have any function that uses average window height call on the average function.
		Make sprite easier to use for setImage. setImage should have a string then it declares a file name and makes a buffered image.
		Resizeable functions.
						
		Physics Class - Implement on a separate thread.
		
		Switch controls to keyListener instead of action map.
		
		Allow the setBackground methods to swap each other. So if we have an image first we should be able to then swap for a color.
		
		Add a setPosition method for transform and override for rigidbody.
		
Bugs: Framerate cap doesn't cap it to the user's specifications. Not too much of a problem right now though.
		moveTo function is just plain broken but we can probably remove it.

Potential ideas:
 Have an array of coordinates and make this the game world. When a object moves or is placed somewhere it makes the position in space occupied.
 Issues: array Size and access time.
Note: Coordinates in space are stored as doubles and then down casted/truncated to integers.

Thoughts: How to deal with rigidbodies and actors. How will Rigidbody interact with Actor? Will it simply be constructed with an actor object and then pass the actor's parameters to actor's transformations?
Change log:

	Update 3:
		- When the actor is rendered on the screen, it's position is now rounded to improve precision.
		- The moveTo function for the transform update is now working correctly and includes a delta, speed and position.
		- Actors are now stored in a ArrayList in the Rendered class. Accordingly so, on each frame update, the positions of the Actors are retrieved
		and rendered to the screen.
		- Transform center of mass removed. (Will be implemented with Rigid Body)
		- Removed conversions for sprites being set in the middle of the window. Instead the coordinate system for transform is now 1:1 with the Jframe coordinate system.
		
	Update 4:
		-Included getDirection and setDirection functions in the transform class.
		-Sprite's (excluding sprite's without images) can now be rotated.
		-Included definition for default Cartesian2D constructor.
		
	Update 5:
		-Rewrite of reprogram class.
		-Inclusion of GameWorld class which is static and contains all game objects.
		- Inclusion of a GameWindow class that handles the rendering of all game objects.
		-New method for rendering game objects onto the canvas: GameWindow is now repainted and handles all game object rendering.
		- Included an Updaterunnable class that implements runnable and can be as a separate thread.
		
		Notes: The render method seems to be executed on the main thread and is leading to some problems. I will try my best to offset it to another thread.
		
	Update 6:
		-Actors and UserInterface objects are now automatically pushed to the GameWorld array lists and their respective element in the array list is modified accordingly.
		-Included remove functions for Actor and UserInterface objects. This automatically adjusts the indexes of Actor and UserInterface objects in the GameWorld lists.
		-Game now has a separate thread for rendering. 
		
	Update 6.A:
	-Cleaned up Render class code.
	-GameWorld.world_coorindates[][] now correctly represents the locations of all rigid bodies. This may be changed from boolean to another datatype such that.
		
Needs testing: UI_list, remove functions for both UserInterface and Actor
NOTE: *The following outlines are for my own outlining and not meant to be official documentation of the classes and functions provided in this engine and game.

Cartesian2D class: The Cartesian representation of space in the game.
				Functions:
				Cartesian2D() - Default constructor. Sets x and y axis to 0,0 (debug area)
				Cartesian2D(double x, double y) - Constructs a transformation with the specified coordinates
				getPosition_X() - Returns the x-axis position
				getPosition_Y() - returns the y-axis position
				setPosition_X(int x) - Sets the x-axis position
					Parameters: x - The x position
				setPosition_Y() - Sets the y-axis position
				
				Members:
				double x_pos
				double y_pos
				
Transform class: The Transformation of an Actor.
				May need to add size to transformations. yes we have to.
				Functions:
				Transform(Cartesian2D) - Constructs a transformation with the specified Cartesian coordinates.
				Transform() - Constructs a transformation with a default Cartesian coordinate.
				
				getVelocity_X() - Returns the x velocity of the object as a float.
				getVelocity_Y() - Returns the y velocity of the object as a float.
				setVelocity_X(double velocity) - Sets the X velocity of the transformation.
					Parameters: - The x position
				getVelocity
				MoveTo(Cartesian2D target, Vector2D velocity)
				rotateTo(double dir)
				
Vector class: Represents a position in space as a Vector.

Actor class: Base class for all entities like sprites, players, N.P.C.s, etc.
			 Can only exist in space and be moved back and forth.
			 This class may only store the properties to be rendered.
			 Functions:
			 Constructor(Transform) - Constructs the object with the specified transform
			 Constructor() - Constructs the object without any coordinates and is set to null.
			 load_sprite() - Loads a sprite into the Actor
			 define_graphics()
			 perhaps actor needs a removable class
			  setVisible(boolean enable)- If enable == true, render image and check sprite_image for null. If sprite_image == null
			  render a box. Otherwise render the sprite with its image. If enable == false, delete the sprite from the canvas.
			  
Sprite class: uses jawa.awt.*
			  Contains images, width sizes etc for rendering.
			  
			  Functions:
			  

UI class: 
	Extends Transform
	
	Functions:
	Text
	setText(string text) - Sets the text
	setFont(Font font)
	
	Members:
	text
	font
	color		
RigidBody class:
	Extends Actor
	Has velocity and mass.
	Should be used if you want a collidable object.
	goals: Collision detection using classical mechanics
			Velocity moves the object using transform.set
			members: center of mass, mass, size dimensions and collision bounds?
			

GameWorld class:
	Background setting.
	Contains an array of all elements on scene.
	

			
			 
