import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


public class Controls {

	public int countt = 0;

	public boolean movingup = false;
	
	public void initializeControls() {
	
		InputMap im = Render.game_window.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		
		Action movedown = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
			
				System.out.println("Action movedown");
				//Render.setSpeed(yspeedup,1);
				Actor player = GameWorld.actor_list.get(0);
			
				
				//	try {
					 try {
						player.moveTo(new Cartesian2D(player.getX() - 90,player.getY() + 300), 100, 1.4f);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
				//		e1.printStackTrace();
				//	}
						
				player.setY((int)player.getY() + 10);
			
			}
		};
		
		Action moveright = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				
				
				//	Render.setSpeed(xspeedright, 3);
					Actor player = GameWorld.actor_list.get(0);
					player.setY((int)player.getX() + 10);

			
				
				}
		};
		
		Action moveleft = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				
					Actor player = GameWorld.actor_list.get(0);

					player.setX((int)player.getX() - 10);

				//	Render.setSpeed(xspeedleft, 2);
				
				
			}
				
		};
		Action moveup = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("MOVEUP");
					Actor player = GameWorld.actor_list.get(0);

					player.setX((int)player.getY() - 10);

				//	Render.setSpeed(yspeeddown,0);
				
			}
		};
		
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D,0), "moveright");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S,0), "movedown");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A,0),"moveleft");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W,0),"moveup");
		Render.game_window.getActionMap().put("moveright", moveright);
		Render.game_window.getActionMap().put("moveleft",  moveleft);
		Render.game_window.getActionMap().put("movedown", movedown);
		Render.game_window.getActionMap().put("moveup", moveup);
	}
	
	public int movedown(int speed) {
		
		return speed;
	}
	
}
