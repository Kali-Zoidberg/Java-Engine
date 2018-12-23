package jengine;
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
	private Actor player = (Actor) GameWorld.game_obj_table.get("Actor1");
	public void initializeControls() {
	
		InputMap im = Render.game_window.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		
		Action movedown = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
			
				//Render.setSpeed(yspeedup,1);
				player = (Actor) GameWorld.game_obj_table.get("Actor1");				
				//player.rigidbody.setY((int)player.rigidbody.getY() + 10);
				player.rigidbody.moveY(10);
			}
		};
		
		Action moveright = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				
				player = (Actor) GameWorld.game_obj_table.get("Actor1");
				//	Render.setSpeed(xspeedright, 3);
					player.rigidbody.setX((int)player.rigidbody.getX() + 10);

			
				
				}
		};
		
		Action moveleft = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				player = (Actor) GameWorld.game_obj_table.get("Actor1");
				player.rigidbody.setX((int)player.rigidbody.getX() - 10);
				player.rigidbody.setVelocity(new Vector2D(0.3,0));	
				//	Render.setSpeed(xspeedleft, 2);
				
				
			}
				
		};
		Action moveup = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				player = (Actor) GameWorld.game_obj_table.get("Actor1");
			//		player.rigidbody.setY((int)player.rigidbody.getY() - 10);
					player.rigidbody.moveY(-10);
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