package Control;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Player.Player;

public class ArrowsStrategy implements IMovePlayer {
	private static Player Player;
	private static Player Player1 = Player.getPlayer1();
	private JComponent PlayerComponent;

	private KeyboardAnimation animation;

	public JComponent getPlayerComponent() {
		return PlayerComponent;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

		animation = new KeyboardAnimation(PlayerComponent, 24);
		animation.addAction("LEFT", -10, 0);
		animation.addAction("RIGHT", 10, 0);

	}

	@Override
	public void recive_Component(JComponent Component) {
		// TODO Auto-generated method stub
		this.PlayerComponent = Component;
	}

	@Override
	public void modify(JComponent Component) {
		// TODO Auto-generated method stub
		Player1.notifyView(PlayerComponent.getLocation());
		Player1.setrsDetectionAreaCentre(
				new Point(Player1.getPosition().x + 120 - 36,
						Player1.getrsDetectionAreaCentre().y));
		Player1.setlsDetectionAreaCentre(new Point(Player1.getPosition().x,
				Player1.getlsDetectionAreaCentre().y));
		Player1.notifyStacksPlates();
	}

	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
