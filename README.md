# OldSnakey
This game was created in 2015. Over time I will optimize and improve the re-usability of this code as well as properly comment the source code.


Re-Write: Over the course of this program, I will rewrite the code with re-usability in mind. I am hoping to achieve a
basic game engine in Java using JFrames. However, parallel computing using multi-threads may be implemented to improve the optimization.
The focus of the multi-threading/parallel computing will be to handle the updated game states such as Vectors, Transforms and Game Logic.


To do: On resize, recalculate averages and have any function that uses average window height call on the average function.
		Multi-threading.
		Think about the static behavior of the screen_Width in the transform class.
		UIclass
		Reprogram Render class. Render class should exist on it's own thread.
		Render class should contain all actors. Ignore the move to functions until we can get multi-threading/parallel working
		Include an update function.
		Rotate function in transform class.
		moveTo function needs a preventive condition for when the speed is too fast.
Note: Coordinates in space are stored as doubles and then down casted/truncated to integers.

Change log:

	Update 3:
		- When the actor is rendered on the screen, it's position is now rounded to improve precision.
		- The moveTo function for the transform update is now working correctly and includes a delta, speed and position.
		- Actors are now stored in a ArrayList in the Rendered class. Accordingly so, on each frame update, the positions of the Actors are retrieved
		and rendered to the screen.
		- Transform center of mass removed. (Will be implemented with Rigid Body)
		- Removed conversions for sprites being set in the middle of the window. Instead the coordinate system for transform is now 1:1 with the Jframe coordinate system.
		
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
	Extends Cartesian2D
	
	Functions:
	Text
	setText(string text) - Sets the text
	setFont(Font font)
	
RigidBody class:
	goals: Collision detection using classical mechanics
			Velocity moves the object using transform.set
			members: center of mass, mass, size dimensions and collision bounds?
			
			 