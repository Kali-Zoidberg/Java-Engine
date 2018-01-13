

import java.awt.Color;
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
		
		
		render_obj.test_start(); //rename
		render_obj.start();
		BufferedImage car_bg = ImageIO.read(car_file);
		render_obj.setBackground(car_bg);
		render_obj.setBackground(Color.black);
		controller.initializeControls();

		
	}
}
