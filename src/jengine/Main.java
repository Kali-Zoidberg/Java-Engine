package jengine;

import java.awt.Color;
import java.util.Random;

import chowshapes.*;
public class Main {
	private static int screenWidth = 500;
	private static int screenHeight = 500;
	public static Render renderObj = new Render("Circle Collisions", 60,screenHeight,screenWidth);

	public static void main(String[] args) {
		initializeScene();
	}
	
	public static void initializeScene()
	{
		int numOfBalls = 300;
		int x_pos = 50;
		int x_pos2 = 20;
		int y_pos = 100;
		int y_pos2 = screenHeight - 20;
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		renderObj.setBackground(Color.WHITE);
		for (int i = 0; i < numOfBalls; ++i)
		{
			Actor some_circle = null;
			if (i < numOfBalls/2)
			{
				some_circle = new Actor(x_pos,y_pos,10,10, "top_circle");
				some_circle.rigidbody.collisionShape = new Circle(x_pos,y_pos, 5);
				some_circle.setColor(Color.RED);
				x_pos += 10;
			} else
			{
				some_circle = new Actor(x_pos2, y_pos2, 20, 20, "bot_circle");
				some_circle.rigidbody.collisionShape = new Circle(x_pos,y_pos, 10);
				some_circle.setColor(Color.BLUE);
				x_pos2 += 20;
			
			}
			some_circle.rigidbody.setCollision(true);
			some_circle.setVisible(true);
			some_circle.rigidbody.setVelocity(new Vector2D((double) rand.nextInt(2) + -2, (double) rand.nextInt(2) + -2));

		}
		renderObj.start();
		MyUpdate updateMethod = new MyUpdate();
		renderObj.setUpdateMethod(updateMethod);
		//Actor boxes[] = {some_box, some_box2, some_box3, some_box4};
		
		
	}
	
	
	static class MyUpdate implements Runnable 	
	{
		
		public void run()
		{ 
			
			for (GameObject goj: GameWorld.game_obj_table.values())
			{
				Actor my_act = (Actor) goj;
				checkBounds(my_act);
			}
		
		}
		public void checkBounds(Actor actorA)
		{
			double posX = actorA.rigidbody.getX() + actorA.rigidbody.getVelocity().getX();
			double posY = actorA.rigidbody.getY() + actorA.rigidbody.getVelocity().getY();
			double fullX = posX + actorA.getWidth();
			double fullY = posY+ actorA.getHeight();
			//Handle case for the wall on the left hand side
			if ((posX >= screenWidth || posX <= 0) && (posY >= 0) && (fullY <= screenHeight))
				//set velocity
				actorA.rigidbody.setVelocity(new Vector2D (actorA.rigidbody.getVelocity().getX() * -1, actorA.rigidbody.getVelocity().getY()));
			if ((posY >= screenHeight || posY <=0) && (posX >= 0) && (fullX <= screenWidth))
					actorA.rigidbody.setVelocity(new Vector2D (actorA.rigidbody.getVelocity().getX(), actorA.rigidbody.getVelocity().getY() * -1));
		}
		
	}
}
