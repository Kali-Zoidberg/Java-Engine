
import javax.swing.JLabel;

public class Main {
	public static int frameratecap = 10;
	public static JLabel focusWindow = new JLabel(""); 
	public static Render rd;
	public static Controls controller = new Controls(rd);
	
	public static void main (String[] args) {

		rd = new Render();
		rd.Renderframes(frameratecap);


	}

}
