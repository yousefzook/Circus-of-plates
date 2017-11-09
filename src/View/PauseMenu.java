package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardUpLeftHandler;

import Player.Player;

/**
 * the menu which appears while pause it is contained from a panel with some
 * buttons
 * 
 * @author Dell
 *
 */
public class PauseMenu extends JPanel {

	private JButton btn;

	private JPanel sPanel;
	private GridBagConstraints gridConstrian;
	private ImageIcon icon;
	private MenuButtonHandler handler;
	private JRadioButton mouseControl;
	private JRadioButton ADControl;
	private ButtonGroup controlGroup;
	private JTextField name1, name2;
	private Frame caller;
	private Player player2;
	private JComponent component;

	private PauseMenu() {
	}

	public PauseMenu(Frame caller) {
		this.caller = caller;
		player2 = Player.getPlayer2();
		component = new JComponent() {
		};
		gridConstrian = new GridBagConstraints();
		name1 = new JTextField("Player1 name");
		name2 = new JTextField("Player2 name");
		JLabel text = new JLabel("Player 2 control:");
		sPanel = new PausePanel();
		handler = new MenuButtonHandler(this);
		ADControl = new JRadioButton("A-D", true);
		mouseControl = new JRadioButton("Mouse", false);
		controlGroup = new ButtonGroup();

		controlGroup.add(ADControl);
		controlGroup.add(mouseControl);
		JPanel cont = new PausePanel();
		cont.add(text);
		cont.add(ADControl);
		cont.add(mouseControl);
		cont.add(name1);
		cont.add(name2);
		text.setFont(new Font("Verdana", 1, 15));

		gridConstrian.gridx = 0;
		gridConstrian.gridy = 0;
		gridConstrian.fill = GridBagConstraints.HORIZONTAL;
		sPanel.add(cont, gridConstrian);
		sPanel.setLayout(new GridBagLayout());

		this.add(sPanel, BorderLayout.CENTER);

		addingButtons();
		setOperations();
		centerWidow();

	}

	public void setStrategy() {
		if (ADControl.isSelected()) {
			player2.setStrategy("AD Strategy");
			player2.getStrategy().recive_Component(component);
			player2.getStrategy().move();
		} else {
			player2.setStrategy("mouse Strategy");
			player2.getStrategy().recive_Component(new JComponent() {
			});
		}
	}

	private void setOperations() {
		sPanel.setPreferredSize(new Dimension(300, 500));
		this.setSize(new Dimension(300, 500));
		sPanel.setVisible(true);
		this.setVisible(false);

	}

	private void addingButtons() {
		for (int i = 0; i < 5; i++) {
			icon = new ImageIcon("src/Materials/" + i + ".png");
			btn = new JButton("", icon);
			btn.setRolloverIcon(
					new ImageIcon("src/Materials/" + (i + 5) + ".png"));
			btn.setBorder(BorderFactory.createEmptyBorder());
			btn.setContentAreaFilled(false);
			btn.addActionListener(handler);
			btn.setActionCommand("" + i);
			gridConstrian.fill = GridBagConstraints.HORIZONTAL;
			gridConstrian.weightx = 0;
			gridConstrian.ipady = 30;
			gridConstrian.gridx = 0;
			gridConstrian.gridy = i + 1;

			sPanel.add(btn, gridConstrian);
		}
	}

	private void centerWidow() {

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);
	}

	public void negatedPaused() {
		caller.showPausePanel(false);
	}

}
