package View;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class AppStarter {

	private static Frame startFrame;
	private static boolean show;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		show = false;
		startFrame = new Frame();
		startFrame.setFocusable(true);
		startFrame.addKeyListener(getKeyListener());
	}

	private static KeyListener getKeyListener() {
		return new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					show = !startFrame.isPanelVisible();
					startFrame.showPausePanel(show);
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		};
	}

}
