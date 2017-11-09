package Control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Player.Player;

public class Load {

	private final static Logger logger = LogManager.getLogger(Player.class);
	private BufferedReader saveFile;
	private JFileChooser chooser;
	private Player player1 = Player.getPlayer1();
	private Player player2 = Player.getPlayer2();

	public Load() {
		chooser = new JFileChooser();
	}

	private File chooseFn() {
		FileNameExtensionFilter txtFilter = new FileNameExtensionFilter(
				"tesxt files (*.txt)", "txt");
		chooser.addChoosableFileFilter(txtFilter);
		chooser.setFileFilter(txtFilter);
		chooser.showOpenDialog(null);
		File file = chooser.getSelectedFile();
		return file;
	}

	private String[] loadStrings() throws IOException {
		String[] strings = new String[6];
		File file = chooseFn();
		String url = null;
		if (file != null) {
			url = file.getAbsolutePath();

			while (!url.substring(url.length() - 4).equals(".txt")) {
				System.out.println("Error");
				url = chooseFn().getAbsolutePath();
			}
			saveFile = new BufferedReader(new FileReader(url));
			for (int i = 0; i < 6; i++)
				strings[i] = saveFile.readLine();
			saveFile.close();

		} else {
			for (int i = 0; i < strings.length; i++) {
				strings[i] = " ";
			}
		}
		return strings;
	}

	// you have the strings here
	public void loadGame() throws IOException {
		String[] strings = loadStrings();
		logger.info("The game is loaded");
		player1.setScore(Integer.parseInt(strings[0]));
		player2.setScore(Integer.parseInt(strings[1]));
		player1.setLStackString(strings[2]);
		player1.setRStackString(strings[3]);
		player2.setLStackString(strings[4]);
		player2.setRStackString(strings[5]);
	}

}
