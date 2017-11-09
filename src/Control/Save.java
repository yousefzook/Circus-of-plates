package Control;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Player.Player;

public class Save {
	private final static Logger logger = LogManager.getLogger(Player.class);
	private FileWriter saveFile;
	private JFileChooser chooser;

	public Save() {
		chooser = new JFileChooser();
	}

	private File chooseFn() {
		FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("tesxt files (*.txt)", "txt");
		chooser.addChoosableFileFilter(txtFilter);
		chooser.setFileFilter(txtFilter);
		chooser.setSelectedFile(new File("Untitled.txt"));
		chooser.showSaveDialog(null);
		File file = chooser.getSelectedFile();
		return file;
	}

	// you saved completly
	public void saveGame(int score1, int score2, String stack1L, String stack1R, String stack2L, String stack2R)
			throws IOException {
		File file = chooseFn();
		String url;
		if (file != null) {
			logger.info("The game is Saved at" + file.getAbsolutePath() );
			url = file.getAbsolutePath();
			saveFile = new FileWriter(url);
			saveFile.write(score1 + "\r\n");
			saveFile.write(score2 + "\r\n");
			saveFile.write(stack1L + "\r\n");
			saveFile.write(stack1R + "\r\n");
			saveFile.write(stack2L + "\r\n");
			saveFile.write(stack2R + "\r\n");
			saveFile.close();
		}
	}

}
