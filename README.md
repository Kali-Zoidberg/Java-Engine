

# JEngine
JEngine spawned after wanting to rewrite a version of Snakey I created in high school. A few days after optimization and reorganization, I decided to create a full fledged 2D game engine. There are still a few bugs to iron out, and areas I need to address but regardless, it is possible for anyone to make a video game utilizing this engine.
<br>
The following write up will be updated over the course of the next two months.
<br><br>
**How to Create a Scene**<br>

1] Create a static Render Object <br>

    public static int frameRate = 60;
    public static int screenWidth = 500;
    public static int screenHeight = 500;
    public static Render renderObj = new Render("Window Title", frameRate, screenWidth, screenHeight);
    
    public static void main(String[] args)
    {
	    initializeScene();
    }
    
    public static initializeScene()
    {
	    //renderObj.setBackground(Image img); // Set the background using an image
	    renderObj.setBackground(Color.RED);
	    
	    /*
	    Your scene intialization code here.
	    */
    }
    
   <br>**The following code is to be placed in the initialization function.**<br>
2] Create an actor<br>

    Actor squareActor = new Actor(x_pos, y_pos, width, height, "Actor Name");

<br>3] Assign a collision shape to the actor's rigid body and set the actor's visibility and collision to true.<br>

    squareActor.rigidbody.collisionShape = new Rectangle(x_pos, y_pos, width, height);
    squareActor.setCollision(true);
    squareActor.setVisible(true);
<br>4] Set the sprite for the Actor

    squareActor.setSprite(new Sprite(Image sprite_image, width, height);
**OR**
Set the color for the Actor.

    squareActor.setColor(Color.RED);
<br>5]Create the update object, set the update method and start the scene.<br>

    MyUpdate updateMethod = new MyUpdate();
    renderObj.setUpdateMethod(updateMethod);
    renderObj.start();
<br>**The Initialization code all together**<br>

    public static int frameRate = 60;
    public static int screenWidth = 500;
    public static int screenHeight = 500;
    public static Render renderObj = new Render("Window Title", frameRate, screenWidth, screenHeight);
    
    public static void main(String[] args)
    {
	    initializeScene();
    }
    
    public static void initializeScene()
    {
	    //renderObj.setBackground(Image img); // Set the background using an image
	    renderObj.setBackground(Color.WHITE);
	    
	    int x_pos = 50;
	    int y_pos = 100;
	    int width = 50, height = 50;
	    
	    Actor squareActor = new Actor(x_pos, y_pos, width, height, "Actor Name"); //Create the actor
	    squareActor.rigidbody.collisionShape = new Rectangle(x_pos, y_pos, width, height); // Set the collision of the actor.
	    //squareActor.setSprite(new Sprite(new Image("Filepath"), width, height); //Give the actor a sprite and set the image of the sprite.
	    squareActor.setColor(Color.RED); //Set the color of the actor
	    squareActor.setCollision(true);
	    squareActor.setVisible(true);
	    
	    MyUpdate updateMethod = new MyUpdate();
    renderObj.setUpdateMethod(updateMethod);
	    renderObj.start();
    }

<br>
6] Edit/Extend Runnable to create an update method<br>
The update method will be called each game tick. This method, in particular changes the actor's colors randomly.

    static class MyUpdate implements Runnable 	
	{
		
		public void run()
		{ 
			
			for (GameObject goj: GameWorld.game_obj_table.values())
			{
				Actor someActor= (Actor) goj;
				changeColor(someActor);
			}
		
		}
		public void changeColor(Actor actorA)
		{
			Random rand = new Random(System.nanoTime());
			rand.setSeed(System.nanoTime());
			//Calculate random r,g,b colors
			float r = rand.nextFloat();
			float g = rand.nextFloat();
			float b = rand.nextFloat();
			
			Color randomColor = new Color(r, g, b); //Initalize the new color
			actorA.setColor(randomColor); //Set the color.
		}
		
	}
<br><br>
![Your game should look like this after completion.](https://i.imgur.com/Z0sbegz.png)
<br>
<br>

**Component Based Architecture:**<br>
The base class for all parent objects is a game object. Parents are parameterized as mentors. <br>
The base class for child objects is component. Children are parameterized as component.<br>
Below is a code snippet for the actual implementation of the component based architecture.<br>
![](https://i.imgur.com/0u7bTDk.png)

<br>This architecture allows for both the parent (mentor) and child (component) to have direct access to one another and invoke their appropriate functions. <br><br>
**Dynamic Audio Scaling**<br>
Dynamic Audio Scaling is a feature in this engine to allow creators to tag/categorize game sounds, music, etc. as lines or categories. <br>
Through these categories, creators may create a new line or category on the AudioMaster by calling the static function:

    AudioLine audioLine = new AudioLine("Your line name");

The following code shows how to set the volume of an audio line and how to adjust all game sounds.

	   audioLine.setVolLinear(float fraction); // Scales the volume based on a fraction or percentage.
	   audioLine.setVolDB(float db); //Sets the volume of the line to a specified DB.
	   audioLine.scaleVol(float fraction); //Scales the volume fractionally. 

To scale down all volumes simply call the following function.

	    AudioMaster.scaleVolume(float volFrac); //Scales all audio lines (and subsequent game sounds) based on the given fraction/percentage.


