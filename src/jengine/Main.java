package jengine;


import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import TestPackages.TestVectors;

public class Main {
	
	public static JLabel focusWindow = new JLabel(""); 
	public static Render render_obj = new Render("Testing title",60,1000,1000);
	public static Controls controller = new Controls();

	public static void main (String[] args) throws InterruptedException, IOException {
		testMethods();
		File car_file = new File("car_background.jpg");
		//GameWorld.initializeWorldCoordinates();
		
		
		Start(); //rename

		render_obj.start();
		
		BufferedImage car_bg = ImageIO.read(car_file);
		render_obj.setBackground(car_bg);
		render_obj.setBackground(Color.black);
		controller.initializeControls();
		
		
	}
	public static void testMethods()
	{
		ChowFunctions.testNormals();
		TestVectors.testNormalize();
		TestVectors.testUnitVector();
		TestVectors.testAddSubVector();
	}
	public static void Start() throws IOException {
//		Transform transform_test = new Transform("Transform", 750,500,0f);
			File sprite_img = new File("sprite_test.jpg");
			BufferedImage car_bg = ImageIO.read(sprite_img);

			//UserInterface ui_test = new UserInterface(60.0,60.0,0.0,"Hello world!",Color.WHITE,	new Font("Courier New", Font.PLAIN, 12), "ui_test");
			
			Actor player = new Actor(50, 700, 50,50, "Actor1");
			Actor box = new Actor(300, 300,50,50, "box1");
			Actor box2 = new Actor(450, 450,50,100, "box2");
			box.rigidbody.setMass(10);
			box2.rigidbody.setMass(1000000);
			player.rigidbody.setMass(1);
			box.setRect(50, 50, Color.blue);
			System.out.println("box center: " + (box.rigidbody.collisionShape.getCenter().getX() - player.rigidbody.collisionShape.getCenter().getX()));
			box.setVisible(true);

			box.rigidbody.setCollision(true);
			
			box2.setRect(100, 100, Color.pink);
			box2.setVisible(true);
			box2.rigidbody.setCollision(true);
			
		//	player.rigidbody.setX(player.rigidbody.getX());
		//	player.rigidbody.setY(player.rigidbody.getY());
			player.setVisible(true);
			
			Sprite sprite_test = new Sprite(car_bg,10,10);
			player.setSprite(sprite_test, "sprite_test");
			player.rigidbody.setCollision(true);
			
			System.out.println("box1: " + box.rigidbody.getX() + " y:" + box.rigidbody.getY());

			//player.transform.setDirection(0.86f);

			//	ui_list.add(ui_test);
			//GameWorld.actor_list.add(player);
			AudioLine ambientAudioLine = new AudioLine("Ambience");
			AudioLine backgroundLine = new AudioLine("Background");
			//SoundEmittor rain_emittor = new SoundEmittor(box, "Rain", "rain.wav", "Ambience");
			backgroundLine.scaleLineVol(0.5f);
			SoundEmittor coffee_shop = new SoundEmittor(box2, "Coffee", "coffee_shop.wav", "Background");

			//rain_emittor.play();
			//rain_emittor.sound.setVolLinear(0.5f);
			coffee_shop.play();
			AudioMaster.scaleVolume(0.1f);
			//rain_emittor.sound.printClipInfo();
			ambientAudioLine.scaleLineVol(0.5f);
			//ChowFunctions.test_functions();

			
	}
	
	public static void Update() {
		
	}
}
