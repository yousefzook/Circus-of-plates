package Control;

import java.awt.Point;

import javax.swing.JComponent;

import Player.Player;

public class MouseStrategy implements IMovePlayer {
	private static Player Player;

	private static Player Player2 = Player.getPlayer2();
	public void move(int x , int y) {
		// TODO Auto-generated method stub
		Player2.notifyView(new Point(x-110,(int)Player2.getPosition().getY()));
		Player2.setrsDetectionAreaCentre(new Point(Player2.getPosition().x + 120 - 36,Player2.getrsDetectionAreaCentre().y));
		Player2.setlsDetectionAreaCentre(new Point(Player2.getPosition().x,Player2.getlsDetectionAreaCentre().y));
		Player2.notifyStacksPlates();

	}
	@Override
	public void recive_Component(JComponent Component) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void modify(JComponent Component) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public JComponent getPlayerComponent() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
	

}
