package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Player.Player;

public class StartMenu extends JPanel {

	private JButton btn;

	private JPanel sPanel;
	private GridBagConstraints gridConstrian;
	private ImageIcon icon;
	private StartMenuButtonHandler handler;
	private JRadioButton mouseControl;
	private JRadioButton ADControl;
	private ButtonGroup controlGroup;
	private JTextField name1, name2;
	private Frame caller;
	private Player player2;
	private JComponent component;

	private StartMenu() {
	}

	public StartMenu(Frame caller) {
		this.caller = caller;
		player2 = Player.getPlayer2();
		gridConstrian = new GridBagConstraints();
		name1 = new JTextField("Player1 name");
		name2 = new JTextField("Player2 name");
		JLabel text = new JLabel("Player 2 control:");
		sPanel = new PausePanel();
		handler = new StartMenuButtonHandler(this);
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

		gridConstrian.fill = GridBagConstraints.HORIZONTAL;
		gridConstrian.weightx = 0;
		gridConstrian.ipady = 30;
		gridConstrian.gridx = 0;
		gridConstrian.gridy = 0;
		gridConstrian.fill = GridBagConstraints.HORIZONTAL;
		sPanel.add(cont, gridConstrian);
		sPanel.setLayout(new GridBagLayout());

		this.add(sPanel, BorderLayout.CENTER);

		addingButtons();
		setOperations();
		centerWidow();
		component = caller.getClown();

	}

	private void setOperations() {
		sPanel.setPreferredSize(new Dimension(300, 500));
		this.setSize(new Dimension(300, 500));
		sPanel.setVisible(true);
		this.setVisible(false);

	}

	public void setStrategy() {
		if (ADControl.isSelected()) {
			player2.setStrategy("AD Strategy");
			player2.getStrategy().recive_Component(component);
			player2.getStrategy().move();
		} else {
			player2.setStrategy("mouse Strategy");
		}
	}

	private void addingButtons() {
		gridConstrian.fill = GridBagConstraints.HORIZONTAL;
		gridConstrian.weightx = 0;
		gridConstrian.ipady = 30;
		gridConstrian.gridx = 0;
		gridConstrian.gridy = 1;
		JLabel text = new JLabel(" Choose game level");
		text.setFont(new Font("Verdana", 1, 25));
		sPanel.add(text, gridConstrian);

		for (int i = 10; i < 14; i++) {
			if (i == 13)
				i = 4;
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
			if (i > 9)
				gridConstrian.gridy = i - 7;
			else
				gridConstrian.gridy = i - 2;

			sPanel.add(btn, gridConstrian);

			if (i == 4)
				i = 13;
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
