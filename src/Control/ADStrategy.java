package Control;

import java.awt.Point;

import javax.swing.JComponent;

import Player.Player;

public class ADStrategy implements IMovePlayer {
	private static Player Player;

	private static Player Player2 = Player.getPlayer2();
	private KeyboardAnimation animation2;
	private JComponent PlayerComponent;

	public ADStrategy() {
		// TODO Auto-generated constructor stub
	}

	public void move() {
		// TODO Auto-generated method stub
		animation2 = new KeyboardAnimation(PlayerComponent, 24);
		animation2.addAction("A", -10, 0);
		animation2.addAction("D", 10, 0);
	}

	public void modify(JComponent Component) {

		Player2.notifyView(PlayerComponent.getLocation());
		Player2.setrsDetectionAreaCentre(
				new Point(Player2.getPosition().x + 120 - 36,
						Player2.getrsDetectionAreaCentre().y));
		Player2.setlsDetectionAreaCentre(new Point(Player2.getPosition().x,
				Player2.getlsDetectionAreaCentre().y));
		Player2.notifyStacksPlates();

	}

	@Override
	public void recive_Component(JComponent Component) {
		// TODO Auto-generated method stub
		PlayerComponent = Component;
	}

	public JComponent getPlayerComponent() {
		return PlayerComponent;
	}

	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub

	}

}
