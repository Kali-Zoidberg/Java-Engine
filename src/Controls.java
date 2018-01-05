import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


public class Controls {

	private int yspeedup = -10;
	private int yspeeddown = 10;
	private int xspeedleft = -10;
	private int xspeedright = 10;
	public int countt = 0;
	private int dir = 0;
	private Render render_obj;
	private boolean isMoving = false;
	private boolean hasMoved = true;
	public boolean movingup = false;

	public void initializeControls() {
	
		InputMap im = Main.focusWindow.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		
		Action movedown = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
			if (dir != 0) {
				dir = 1;
				//Render.setSpeed(yspeedup,1);
				Actor player = Render.actor_list.get(0);
			
				
					//	hasMoved = player.transform.moveTo(new Cartesian2D(player.transform.getX(),player.transform.getY() + 100), 10000, 2f);
						
				//player.transform.setY((int)player.transform.getY() + 10);
			}
			}
		};
		
		Action moveright = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				
				if(dir != 2) {
				//	Render.setSpeed(xspeedright, 3);
					Actor player = Render.actor_list.get(0);
					player.transform.setY((int)player.transform.getY() - 10);

					dir= 3;
				}
				}
		};
		
		Action moveleft = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if (dir !=3) {
					Actor player = Render.actor_list.get(0);

					player.transform.setX((int)player.transform.getX() + 10);

				//	Render.setSpeed(xspeedleft, 2);
					dir = 2;
				}
			}
				
		};
		Action moveup = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if (dir != 1) {
					Actor player = Render.actor_list.get(0);

					player.transform.setX((int)player.transform.getX() - 10);

				//	Render.setSpeed(yspeeddown,0);
					dir = 0;
				}
			}
		};
		
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D,0), "moveright");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S,0), "movedown");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A,0),"moveleft");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W,0),"moveup");
		Main.focusWindow.getActionMap().put("moveright", moveright);
		Main.focusWindow.getActionMap().put("moveleft",  moveleft);
		Main.focusWindow.getActionMap().put("movedown", moveup);
		Main.focusWindow.getActionMap().put("moveup", movedown);
	}
	
	public int movedown(int speed) {
		
		return speed;
	}
	
}
