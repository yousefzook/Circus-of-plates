package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Control.Load;
import Control.Save;
import Player.Player;
import ShapesFactory.ReusablePool;

public class StartMenuButtonHandler implements ActionListener {

	private StartMenu caller;
	private Player player1;

	private StartMenuButtonHandler() {
	}

	public StartMenuButtonHandler(StartMenu caller) {
		player1 = Player.getPlayer1();
		this.caller = caller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		JButton buttonPressed = (JButton) e.getSource();
		 if (buttonPressed.getActionCommand().equals("10")) {
			player1.setGameLevel(1);
			caller.setStrategy();
			caller.setVisible(false);
			caller.negatedPaused();
		} else if (buttonPressed.getActionCommand().equals("11")) {
			player1.setGameLevel(2);
			caller.setStrategy();
			caller.setVisible(false);
			caller.negatedPaused();
		} else if (buttonPressed.getActionCommand().equals("12")) {
			player1.setGameLevel(3);
			caller.setStrategy();
			caller.setVisible(false);
			caller.negatedPaused();
		} else if (buttonPressed.getActionCommand().equals("4")) {
			System.exit(0);
			caller.setVisible(false);
			caller.negatedPaused();
		} else
			System.out.println("Error in start menu buttons listener");
	}

}
