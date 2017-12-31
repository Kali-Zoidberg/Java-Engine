
import javax.swing.JLabel;

public class Main {
	public static int frameratecap = 10;
	public static JLabel focusWindow = new JLabel(""); 
	public static Render rd;
	public static Controls controller = new Controls();
	
	public static void main (String[] args) {

		Render.Renderframes(frameratecap);


	}

}
