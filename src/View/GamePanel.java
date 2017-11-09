package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Control.IMovePlayer;
import Control.Iterator;
import MovingShapes.EasyMoveStrategy;
import MovingShapes.HardMoveStrategy;
import MovingShapes.IMoveShape;
import MovingShapes.MediumMoveStrategy;
import Player.Player;
import Shapes.IShape;
import ShapesFactory.ReusablePool;

public class GamePanel extends JPanel implements MouseMotionListener {
	private final static Logger logger = LogManager.getLogger(Player.class);
	private Image background;
	private JLabel label1;
	private JLabel label2;
	private Stack player1LS, player2LS, player1RS, player2RS;
	private BufferedImage clownPicture1, clownPicture2;
	private JLabel clownLabel1, clownLabel2;
	private static Player Player;
	private static Player Player1;
	private static Player Player2;
	private IMovePlayer strategy1;
	private IMovePlayer strategy2;
	private LinkedList<IShape> currentShapes = new LinkedList<IShape>();
	protected static boolean type1 = false;
	protected static boolean type2 = false;
	protected static boolean type3 = false;
	protected static boolean type4 = false;
	protected static boolean type5 = false;
	protected static boolean type6 = false;
	private Iterator iter;
	private int maximumSize = 20;
	private int level = 1;
	private IMoveShape moveObject;
	private boolean paused;
	private Frame caller;

	public GamePanel(Frame caller) throws IOException {
		this.caller = caller;
		iter = new shapesAscendingIterator();
		paused = false;
		player1LS = new Stack<IShape>();
		player1RS = new Stack<IShape>();
		player2LS = new Stack<IShape>();
		player2RS = new Stack<IShape>();
		Player1 = Player.getPlayer1();
		Player2 = Player.getPlayer2();
		setLayout(null);
		addMouseMotionListener(this);
		setFocusable(true);
		this.background = ImageIO
				.read(new File("src/Materials/background.jpg"));
		this.clownPicture1 = ImageIO.read(new File("src/Materials/clown1.png"));
		this.clownPicture2 = ImageIO.read(new File("src/Materials/clown2.png"));

		this.clownLabel1 = new JLabel(new ImageIcon(clownPicture1));
		this.clownLabel2 = new JLabel(new ImageIcon(clownPicture2));
		add(clownLabel1);
		add(clownLabel2);

		Player1.setStrategy("Arrow Strategy");
		strategy1 = Player1.getStrategy();
		Player2.setStrategy("Mouse Strategy");
		strategy2 = Player2.getStrategy();

		clownLabel1.setBounds(300, 480, 120, 235);
		clownLabel2.setBounds(900, 480, 120, 235);

		strategy1.recive_Component(clownLabel1);
		strategy1.move();
		strategy2.recive_Component(clownLabel2);
		ReusablePool reusablePool = ReusablePool.getInstance();
		for (int i = 0; i < 200; i++) {
			IShape s = reusablePool.acquireShape();
			s.setIndex(i);
			currentShapes.add(s);
		}

		ImageIcon imgThisImg1 = new ImageIcon(
				new File("src/Materials/Dark.png").getAbsolutePath());
		label1 = new JLabel("Player1's Score = 0");
		label1.setBounds(20, 450, 256, 256);
		label1.setIcon(imgThisImg1);
		label1.setForeground(new Color(255, 255, 255));

		label1.setHorizontalTextPosition(JLabel.CENTER);
		label1.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.add(label1);

		ImageIcon imgThisImg2 = new ImageIcon(
				new File("src/Materials/Red.png").getAbsolutePath());
		label2 = new JLabel("Player2's Score = 0");
		label2.setBounds(1090, 450, 256, 256);
		label2.setIcon(imgThisImg2);
		label2.setForeground(new Color(255, 255, 255));

		label2.setFont(new Font("Tahoma", Font.BOLD, 18));
		label2.setHorizontalTextPosition(JLabel.CENTER);

		this.add(label2);

		setSize(1366, 790);

	}

	public void setPaused(boolean b) {
		paused = b;
	}

	public void moving(IShape currentShape) {

		if (level == 1) {
			moveObject = new EasyMoveStrategy(currentShape, 1);
			moveObject.move();
		} else if (level == 2) {
			moveObject = new MediumMoveStrategy(currentShape, "meduim");
			moveObject.move();
		} else if (level == 3) {
			moveObject = new HardMoveStrategy(currentShape, "Hard", 3);
			moveObject.move();
		}
	}

	public void setLevel(int level) {
		this.level = level;
		if (level == 2)
			maximumSize = 60;
		else if (level == 3)
			maximumSize = 80;
	}

	public boolean drawObjects(IShape currentShape, Point position, int type) {
		boolean draw = false;
		if (type <= 3)
			draw = Validate.type1Draw(currentShape, position, type);
		else
			draw = Validate.type2Draw(currentShape, position, type);

		return draw;
	}

	boolean b = true;

	public JLabel getClowns(int i) {
		if (i == 2)
			return clownLabel2;
		return clownLabel1;

	}

	public void detectShape(IShape currentShape) {

		if (Player1.Detect_Shape_LStack(currentShape)) {
			if (!Player1.Check_triConsecutive(currentShape, "Left")) {
				Player1.AddShape_toStack("Left", currentShape);
				currentShape.setAddedToStack(true);
				maximumSize++;
			} else {
				Player1.updateScore();
				update_score();

			}
		}

		else if (Player1.Detect_Shape_RStack(currentShape)) {
			if (!Player1.Check_triConsecutive(currentShape, "Right")) {
				Player1.AddShape_toStack("Right", currentShape);
				currentShape.setAddedToStack(true);

				maximumSize++;
			} else {
				Player1.updateScore();
				update_score();

			}

		} else if (Player2.Detect_Shape_LStack(currentShape)) {
			if (!Player2.Check_triConsecutive(currentShape, "Left")) {
				Player2.AddShape_toStack("Left", currentShape);
				currentShape.setAddedToStack(true);
				maximumSize++;
			} else {
				Player2.updateScore();
				update_score();

			}

		} else if (Player2.Detect_Shape_RStack(currentShape)) {
			if (!Player2.Check_triConsecutive(currentShape, "Right")) {
				Player2.AddShape_toStack("Right", currentShape);
				currentShape.setAddedToStack(true);
				maximumSize++;
			} else {
				Player2.updateScore();
				update_score();

			}
		}
	}

	public void update_score() {
		label1.setText("Player1's Score = " + Player1.getScore());
		logger.info("Player1's score changed to " + Player1.getScore());

		label2.setText("Player2's Score = " + Player2.getScore());
		logger.info("Player2's score changed to " + Player2.getScore());

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (Player1.getWinner()) {
			JOptionPane.showMessageDialog(caller,
					"Player1 Won !\nPlayer1 score: " + Player1.getScore()
							+ "\nPlayer2 score: " + Player2.getScore());
			System.exit(0);
		} else if (Player2.getWinner()) {
			JOptionPane.showMessageDialog(caller,
					"Player2 Won !\nPlayer1 score: " + Player1.getScore()
							+ "\nPlayer2 score: " + Player2.getScore());
			System.exit(0);
		} else if (Player1.getFinished() || Player2.getFinished()) {
			JOptionPane.showMessageDialog(caller,
					"Players are in draw\nPlayer1 score: " + Player1.getScore()
							+ "\nPlayer2 score: " + Player2.getScore());
			System.exit(0);
		}
		strategy2 = Player2.getStrategy();
		strategy2.recive_Component(clownLabel2);
		setLevel(Player1.getGameLevel());
		g.drawImage(background, 0, 0, this);
		for (int i = 0; i < maximumSize; i++) {
			IShape currentShape = currentShapes.get(i);
			ReusablePool reusablePool = ReusablePool.getInstance();
			if (reusablePool.getLoaded()) {
				for (int j = 0; j < maximumSize; j++) {
					currentShape = reusablePool.acquireShape();
					logger.info("A Shape is Acquired");
					currentShapes.set(j, currentShape);
				}
				currentShape = currentShapes.get(i);
			}
			boolean draw = drawObjects(currentShape, currentShape.getPoint(),
					currentShape.getType());
			if (draw) {
				currentShape.draw((Graphics2D) g);
				if (!currentShape.getAddedToStack()) {

					int p = currentShape.getPoint().y;

					detectShape(currentShape);

					moving(currentShape);
					if (p >= 740) {
						reusablePool.releaseShape(currentShape);
						logger.info("A Shape is Released");
						currentShape = reusablePool.acquireShape();
						logger.info("A Shape is Acquired");
					}

				}

			}
		}

		player1LS = Player1.getLStack();
		player1RS = Player1.getRStack();
		player2LS = Player2.getLStack();
		player2RS = Player2.getRStack();
		IShape s;

		iter = new shapesAscendingIterator();
		while (iter.hasNext(player1LS)) {
			s = iter.next(player1LS);
			if (s.getLoaded()) {
				update_score();
				currentShapes.add(0, s);
				maximumSize++;
				s.setLoaded(false);
			}
		}
		iter = new shapesAscendingIterator();
		while (iter.hasNext(player2LS)) {
			s = iter.next(player2LS);
			if (s.getLoaded()) {
				update_score();
				currentShapes.add(0, s);
				maximumSize++;
				s.setLoaded(false);
			}
		}
		iter = new shapesAscendingIterator();
		while (iter.hasNext(player1RS)) {
			s = iter.next(player1RS);
			if (s.getLoaded()) {
				update_score();
				currentShapes.add(0, s);
				maximumSize++;
				s.setLoaded(false);
			}
		}
		iter = new shapesAscendingIterator();
		while (iter.hasNext(player2RS)) {
			s = iter.next(player2RS);
			if (s.getLoaded()) {
				update_score();
				currentShapes.add(0, s);
				maximumSize++;
				s.setLoaded(false);
			}
		}
		iter = new shapesAscendingIterator();
		if (!paused)
			repaint();

		type1 = type2 = type3 = type4 = type5 = type6 = false;

	}

	public class shapesAscendingIterator implements Iterator {
		private int counter = -1;

		@Override
		public boolean hasNext(Stack<IShape> stack) {
			// TODO Auto-generated method stub
			try {
				if (stack.empty())
					return false;

				else if (stack.get(counter + 1) != null)
					return true;
			} catch (Exception e) {
				return false;

			}
			return false;
		}

		@Override
		public IShape next(Stack<IShape> stack) {
			// TODO Auto-generated method stub
			counter++;
			IShape shape = stack.get(counter);

			return shape;
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		String s = strategy2.toString().substring(8);
		if (!paused && s.charAt(0) != 'A') {
			strategy2.move(e.getX(), e.getY());
			clownLabel2.setLocation(Player2.getPosition());
			repaint();
		}
	}

}
