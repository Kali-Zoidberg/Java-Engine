# OldSnakey
This game was created in 2015. Over time I will optimize and improve the re-usability of this code as well as properly comment the source code.

Notes: The more recent code 2017+ will contain some resemblance to c++ etiquette. For this, I apologize as I have been using it more than other languages.
		This etiquette is most noticeable in the way that variables are declared and defined and in the constructors.

Re-Write: Over the course of this program, I will rewrite the code with re-usability in mind. With re-usability in mind, I am hoping to achieve a
basic game engine in Java using JFrames. However, parallel computing using multi-threads may be implemented to improve the optimization.
The focus of the multi-threading/parallel computing will be to handle the updated game states such as Vectors, Transforms and Game Logic.

Cartesian2D class: The Cartesian representation of space in the game.
				Functions:
				Cartesian2D() - Default constructor. Sets x and y axis to 0,0 (debug area)
				Cartesian2D(int x, int y) - Constructs a transformation with the specified coordinates
				getPosition_X() - Returns the x-axis position
				getPosition_Y() - returns the y-axis position
				setPosition_X(int x) - Sets the x-axis position
					Parameters: x - The x position
				setPosition_Y() - Sets the y-axis position
				
				Members:
				int x_pos
				int y_pos
				
Transform class: The Transformation of an Actor.
				Functions:
				Transform(Cartesian2D) - Constructs a transformation with the specified Cartesian coordinates.
				Transform() - Constructs a transformation with a default Cartesian coordinate.
				
				getVelocity_X() - Returns the x velocity of the object as a float.
				getVelocity_Y() - Returns the y velocity of the object as a float.
				setVelocity_X(double velocity) - Sets the X velocity of the transformation.
					Parameters: - The x position
				getVelocity
				
Vector class: Represents a position in space as a Vector.
Actor class: Base class for all entities like sprites, players, N.P.C.s, etc.
			 Can only exist in space and be moved back and forth.
			 This class may only store the properties to be rendered.
			 Functions:
			 Constructor(Transform) - Constructs the object with the specified transform
			 Constructor() - Constructs the object without any coordinates and is set to null.
			 load_sprite() - Loads a sprite into the Actor
			 define_graphics()
			 
Sprite class: uses jawa.awt.*
			  Contains images, width sizes etc for rendering.
			  
			  Functions:
			  