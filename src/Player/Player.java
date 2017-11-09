package Player;

import java.awt.Color;
import java.awt.Point;
import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sun.audio.*;
import Control.ADStrategy;
import Control.ArrowsStrategy;
import Control.IMovePlayer;
import Control.MouseStrategy;
import Shapes.IShape;
import ShapesFactory.ReusablePool;

public class Player {
	private static Player Player1 = new Player(300, 480, "Player 1");
	private static Player Player2 = new Player(900, 480, "Player 2");
	private final static Logger logger = LogManager.getLogger(Player.class);

	public static Player getPlayer1() {
		return Player1;
	}

	public static Player getPlayer2() {
		return Player2;
	}

	private boolean Winner = false, finished = false;
	private Point position; // postion of player
	private Stack<IShape> LStack; // left hand stack of plates
	private Stack<IShape> RStack; // right hand stack of plates

	private Point lsDetectionAreaCentre;
	private Point rsDetectionAreaCentre;
	private String playerName;
	private int score; // score of player
	private int maxPlatesLength;
	private IMovePlayer Move_Strategy;
	private int gameLevel = 1;
	ReusablePool reusablePool = ReusablePool.getInstance();

	// initializing in constructor
	public Player(int x, int y, String playerName) {
		position = new Point(x, y);
		LStack = new Stack<IShape>();
		RStack = new Stack<IShape>();
		this.playerName = playerName;
		lsDetectionAreaCentre = new Point(this.getPosition().x,
				this.getPosition().y + 15);
		rsDetectionAreaCentre = new Point(this.getPosition().x + 120 - 36,
				this.getPosition().y + 13);
		score = 0;
		maxPlatesLength = 17;
	}

	public void updateScore() {
		this.score++;
	}

	public void setGameLevel(int level) {
		gameLevel = level;
	}

	public int getGameLevel() {
		return gameLevel;
	}

	public Stack<IShape> getLStack() {
		return this.LStack;
	}

	public Stack<IShape> getRStack() {
		return this.RStack;
	}

	public void setLStackString(String s) {
		this.LStack.removeAllElements();
		s = s.trim();
		String[] sArr = s.split(" ");
		if (sArr.length != 0) {
			int len = (s.length() / 2 + 1) / 3;
			lsDetectionAreaCentre = new Point(this.getPosition().x,
					this.getPosition().y + 15);
			for (int i = 0; i < len; i++) {
				IShape shape = reusablePool.acquireShape();
				if (sArr[i * 3].charAt(0) == '2')
					shape.setColor(new Color(255, 0, 0));
				else if (sArr[i * 3 + 1].charAt(0) == '2')
					shape.setColor(new Color(0, 255, 0));
				else
					shape.setColor(new Color(0, 0, 255));
				shape.setLoaded(true);
				AddShape_toStack("Left", shape);
				shape.setAddedToStack(true);
			}
		}
	}

	public void setRStackString(String s) {
		this.RStack.removeAllElements();
		s = s.trim();
		String[] sArr = s.split(" ");
		if (sArr.length != 0) {
			int len = (s.length() / 2 + 1) / 3;
			rsDetectionAreaCentre = new Point(this.getPosition().x + 120 - 36,
					this.getPosition().y + 13);
			for (int i = 0; i < len; i++) {
				IShape shape = reusablePool.acquireShape();
				if (sArr[i * 3].charAt(0) == '2')
					shape.setColor(new Color(255, 0, 0));
				else if (sArr[i * 3 + 1].charAt(0) == '2')
					shape.setColor(new Color(0, 255, 0));
				else
					shape.setColor(new Color(0, 0, 255));
				shape.setLoaded(true);
				AddShape_toStack("Right", shape);
				shape.setAddedToStack(true);
			}
		}
	}

	public String getLStackString() {
		String finalS = "";
		for (int i = 0; i < this.LStack.size(); i++) {
			String s = this.LStack.get(i).getColor().toString();
			int r = s.indexOf('r') + 2;
			int g = s.indexOf('g');
			int b = s.indexOf('b');
			finalS += (" " + s.charAt(r + 2));
			finalS += (" " + s.charAt(g + 2));
			finalS += (" " + s.charAt(b + 2));
		}
		return finalS;
	}

	public String getRStackString() {
		String finalS = "";
		for (int i = 0; i < this.RStack.size(); i++) {
			String s = this.RStack.get(i).getColor().toString();
			int r = s.indexOf('r') + 2;
			int g = s.indexOf('g');
			int b = s.indexOf('b');
			finalS += (" " + s.charAt(r + 2));
			finalS += (" " + s.charAt(g + 2));
			finalS += (" " + s.charAt(b + 2));
		}
		return finalS;
	}

	public void setStrategy(String s) {
		if (s.equals("AD Strategy")) {
			this.Move_Strategy = new ADStrategy();
			logger.info(this.playerName + "'s Control Strategy is set to A-D");

		} else if (s.equals("Arrow Strategy")) {
			this.Move_Strategy = new ArrowsStrategy();
			logger.info(
					this.playerName + "'s Control Strategy is set to arrows");

		} else {
			this.Move_Strategy = new MouseStrategy();

			logger.info(
					this.playerName + "'s Control Strategy is set to mouse");
		}

	}

	public IMovePlayer getStrategy() {
		return this.Move_Strategy;

	}

	public void setlsDetectionAreaCentre(Point lsDetectionAreaCentre) {
		this.lsDetectionAreaCentre = lsDetectionAreaCentre;

	}

	public void setrsDetectionAreaCentre(Point rsDetectionAreaCentre) {
		this.rsDetectionAreaCentre = rsDetectionAreaCentre;

	}

	public Point getlsDetectionAreaCentre() {
		return this.lsDetectionAreaCentre;
	}

	public Point getrsDetectionAreaCentre() {
		return this.rsDetectionAreaCentre;
	}

	// setter for max plates length
	public void setMaxLength(int maxPlatesLength) {
		this.maxPlatesLength = maxPlatesLength;
	}

	// getter and setter for position
	public Point getPosition() {
		return this.position;
	}

	public void setPosition(Point position) {// updated in control
		this.position = position;
	}

	// getter and setter for score
	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean Detect_Shape_LStack(IShape currentShape) {
		int px = currentShape.getPoint().x;
		int py = currentShape.getPoint().y;
		int wide = currentShape.getWide();
		int high = currentShape.getHigh();
		if ((lsDetectionAreaCentre.x - px < wide
				&& lsDetectionAreaCentre.y - py < high
				&& px <= lsDetectionAreaCentre.x
				&& py <= lsDetectionAreaCentre.y)
				|| (px <= lsDetectionAreaCentre.x + wide
						&& lsDetectionAreaCentre.y - py < high
						&& px >= lsDetectionAreaCentre.x
						&& py <= lsDetectionAreaCentre.y)) {
			return true;
		}
		return false;

	}

	public boolean Detect_Shape_RStack(IShape currentShape) {
		int px = currentShape.getPoint().x;
		int py = currentShape.getPoint().y;
		int wide = currentShape.getWide();
		int high = currentShape.getHigh();
		if ((rsDetectionAreaCentre.x - px < wide
				&& rsDetectionAreaCentre.y - py < high
				&& px <= rsDetectionAreaCentre.x
				&& py <= rsDetectionAreaCentre.y)
				|| (px <= rsDetectionAreaCentre.x + wide
						&& rsDetectionAreaCentre.y - py < high
						&& px >= rsDetectionAreaCentre.x
						&& py <= rsDetectionAreaCentre.y)) {
			return true;
		}
		return false;
	}

	public void AddShape_toStack(String stack, IShape currentShape) {

		if (stack.equals("Left")) {
			LStack.add(currentShape);
			currentShape.setPoint(lsDetectionAreaCentre);

			this.setlsDetectionAreaCentre(new Point(lsDetectionAreaCentre.x,
					lsDetectionAreaCentre.y - currentShape.getHigh()));
			logger.info("A Shape is being added to " + this.playerName
					+ "'s Left Stack");
			logger.info("Left Stack size equals " + LStack.size());

		} else if (stack.equals("Right")) {
			RStack.add(currentShape);
			currentShape.setPoint(rsDetectionAreaCentre);

			this.setrsDetectionAreaCentre(new Point(rsDetectionAreaCentre.x,
					rsDetectionAreaCentre.y - currentShape.getHigh()));
			logger.info("A Shape is being added to " + this.playerName
					+ "'s Right Stack");
			logger.info("Left Stack size equals " + RStack.size());

		}
		if (isMax()) {
			notifyFinish();
		}

	}

	// return true if one of stacks reaches its max
	public boolean isMax() {
		if (LStack.size() >= maxPlatesLength
				|| RStack.size() >= maxPlatesLength)
			return true;
		return false;
	}

	public boolean Check_triConsecutive(IShape currentshape, String stack) {

		if (stack.equals("Left")) {
			if (LStack.size() >= 2) {
				if (currentshape.getColor().equals(LStack.peek().getColor())) {
					if (currentshape.getColor().equals(
							(LStack.get(LStack.size() - 2)).getColor())) {

						reusablePool.releaseShape(currentshape);
						reusablePool.acquireShape();

						reusablePool.releaseShape(LStack.pop());
						reusablePool.acquireShape();

						reusablePool.releaseShape(LStack.pop());
						reusablePool.acquireShape();
						logger.info("2 Shapes has been removed from "
								+ this.playerName + "'s Left Stack");
						logger.info("Left Stack size equals " + LStack.size());
						logger.info("A Point is added to " + this.playerName
								+ "'s score");

						setlsDetectionAreaCentre(
								new Point(getlsDetectionAreaCentre().x,
										getlsDetectionAreaCentre().y
												+ 2 * currentshape.getHigh()));
						return true;
					}
				}
			}
		} else if (stack.equals("Right")) {
			if (RStack.size() >= 2) {
				if (currentshape.getColor().equals(RStack.peek().getColor())) {
					if (currentshape.getColor().equals(
							(RStack.get(RStack.size() - 2)).getColor())) {

						reusablePool.releaseShape(currentshape);
						reusablePool.acquireShape();

						reusablePool.releaseShape(RStack.pop());
						reusablePool.acquireShape();

						reusablePool.releaseShape(RStack.pop());
						reusablePool.acquireShape();
						logger.info("2 Shapes has been removed from "
								+ this.playerName + "'s Right Stack");
						logger.info(this.playerName
								+ "'s Right Stack size equals" + RStack.size());

						logger.info("A Point is added to " + this.playerName
								+ "'s score");
						setrsDetectionAreaCentre(
								new Point(getrsDetectionAreaCentre().x,
										getrsDetectionAreaCentre().y
												+ 2 * currentshape.getHigh()));

						return true;
					}
				}
			}
		}

		return false;
	}

	// notify view and plates when the position change
	public void notifyView(Point position) {
		// guiObj.update;
		this.setPosition(position);

	}

	public void notifyStacksPlates() {// to update each plate position
		logger.info(this.playerName + " Moved");
		for (IShape currentshape : LStack) {
			currentshape.setPoint(new Point(lsDetectionAreaCentre.x,
					currentshape.getPoint().y));

		}
		for (IShape currentshape : RStack) {
			currentshape.setPoint(new Point(rsDetectionAreaCentre.x,
					currentshape.getPoint().y));

		}
	}

	// notify finish class that the stack reaches max
	private void notifyFinish() {// when reach max height
		this.finished = true;
		logger.info("The game is Finished");
		if (Player1.getScore() > Player2.getScore()) {
			Player1.setWinner(true);
			logger.info("Player1 Wins");

		} else if (Player1.getScore() < Player2.getScore()) {
			Player2.setWinner(true);
			logger.info("Player2 Wins");

		} else
			logger.info("It's a DRAW");

	}

	public void setWinner(boolean b) {
		// TODO Auto-generated method stub
		this.Winner = b;
	}

	public boolean getWinner() {
		// TODO Auto-generated method stub
		return Winner;
	}

	public boolean getFinished() {
		return finished;
	}
}
