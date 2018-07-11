

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class Main {
	
	public static JLabel focusWindow = new JLabel(""); 
	public static Render render_obj = new Render("Testing title",60,1000,1000);
	public static Controls controller = new Controls();

	public static void main (String[] args) throws InterruptedException, IOException {
		File car_file = new File("car_background.jpg");
		GameWorld.initializeWorldCoordinates();

		
		Start(); //rename

		render_obj.start();
		
		BufferedImage car_bg = ImageIO.read(car_file);
		render_obj.setBackground(car_bg);
		render_obj.setBackground(Color.black);
		controller.initializeControls();

		
	}
	
	public static void Start() throws IOException {
//		Transform transform_test = new Transform("Transform", 750,500,0f);
			File sprite_img = new File("sprite_test.jpg");
			BufferedImage car_bg = ImageIO.read(sprite_img);

			UserInterface ui_test = new UserInterface(60.0,60.0,0.0,"Hello world!",Color.WHITE,	new Font("Courier New", Font.PLAIN, 12), "ui_test");
			
			Actor player = new Actor(50, 300, "Actor1");
			Actor box = new Actor(50, 600, "box1");
			Actor box2 = new Actor(50, 50, "box2");
			
			box.setRect(50, 50, Color.blue);
	
			box.setVisible(true);

			box.rigidbody.setCollision(true);
			
			box2.setRect(50, 50, Color.pink);
			box2.setVisible(true);
			box2.rigidbody.setCollision(true);
			
		//	player.rigidbody.setX(player.rigidbody.getX());
		//	player.rigidbody.setY(player.rigidbody.getY());
			player.setVisible(true);
			
			Sprite sprite_test = new Sprite(car_bg,50,50);
			player.setSprite(sprite_test);
			player.rigidbody.setCollision(true);
			
			System.out.println("box1: " + box.rigidbody.getX() + " y:" + box.rigidbody.getY());

			//player.transform.setDirection(0.86f);

			//	ui_list.add(ui_test);
			//GameWorld.actor_list.add(player);
			System.out.println("Actor index: " + GameWorld.getActorIndex("test actor"));
			AudioLine musicLine = new AudioLine("Music");

			SoundEmittor sound_test = new SoundEmittor(0,0, "Toto-Africa", "Test_music.wav", "Music");
			sound_test.play();
			sound_test.sound.setVolLinear(0.6f);

			
	}
	
	public static void Update() {
		
	}
}
