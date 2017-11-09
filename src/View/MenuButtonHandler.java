package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

import Control.Load;
import Control.Save;
import Player.Player;
import ShapesFactory.ReusablePool;

public class MenuButtonHandler implements ActionListener {
	private PauseMenu caller;
	private Save saveObj;
	private Load loadObj;
	private ReusablePool reusablePool;
	private Player player1 = Player.getPlayer1();
	private Player player2 = Player.getPlayer2();

	private MenuButtonHandler() {
	}

	public MenuButtonHandler(PauseMenu caller) {
		this.caller = caller;
		saveObj = new Save();
		loadObj = new Load();
		reusablePool = ReusablePool.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton buttonPressed = (JButton) e.getSource();
		if (buttonPressed.getActionCommand().equals("0")) {
			caller.setVisible(false);
			caller.setStrategy();
			caller.negatedPaused();
		} else if (buttonPressed.getActionCommand().equals("1")) {
			try {
				caller.setStrategy();
				saveObj.saveGame(player1.getScore(), player2.getScore(),
						player1.getLStackString(), player1.getRStackString(),
						player2.getLStackString(), player2.getRStackString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("Error Saving Game!");
				e1.printStackTrace();
			}
		} else if (buttonPressed.getActionCommand().equals("2")) {
			try {
				caller.setStrategy();
				loadObj.loadGame();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("Error Loading Game!");
				e1.printStackTrace();
			}
		} else if (buttonPressed.getActionCommand().equals("3")) {
			caller.setStrategy();
			reusablePool.setLoaded(true);
		} else if (buttonPressed.getActionCommand().equals("4")) {
			System.exit(0);
		} else
			System.out.println("Error in menu buttons listener");
	}
}