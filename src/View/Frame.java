package View;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Frame extends JFrame {

	private JLayeredPane container;
	private PauseMenu pauseMenu;
	private GamePanel gamePanel;
	private StartMenu startMenu;

	public Frame() throws IOException {
		super("Circus of plates");
		playMusic();
		this.setUndecorated(true);
		container = new JLayeredPane();
		pauseMenu = new PauseMenu(this);
		gamePanel = new GamePanel(this);
		startMenu = new StartMenu(this);
		gamePanel.setPaused(true);
		startMenu.setVisible(true);
		container.add(pauseMenu);
		container.add(startMenu);
		container.add(gamePanel);

		add(container);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void showPausePanel(boolean b) {
		pauseMenu.setVisible(b);
		gamePanel.setPaused(b);
	}
	public void showStartPanel(boolean b) {
		startMenu.setVisible(b);
		gamePanel.setPaused(b);
	}

	public boolean isPanelVisible() {
		return pauseMenu.isVisible();
	}

	private void playMusic() {
		Clip clip;
		try {
			clip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(
					new File("src/Materials/music.wav").toURL());
			clip.open(ais);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (LineUnavailableException | UnsupportedAudioFileException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JComponent getClown() {
		// TODO Auto-generated method stub

		return gamePanel.getClowns(2);

	}

}
