package LoaderPackage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import Shapes.IShape;

public class Plate implements IShape {
	private Color color;
	private Point point = new Point();
	private int wide = 30;
	private int high = 30;
	private int type = 0;
	private int[] xPoints = new int[4];
	private int[] yPoints = new int[4];
	private boolean addedToStack = false;
	private boolean loaded;

	@Override
	public void draw(Graphics2D g) {
		g.drawPolygon(xPoints, yPoints, 4);
		g.setColor(color);
		g.fillPolygon(xPoints, yPoints, 4);

	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void setPoint(Point point) {
		this.point = point;
		setPoints();
	}

	private void setPoints() {
		xPoints[0] = point.x;
		yPoints[0] = point.y;
		xPoints[1] = point.x + 10;
		yPoints[1] = point.y + 30;
		xPoints[2] = point.x + 20;
		yPoints[2] = point.y + 30;
		xPoints[3] = point.x + 30;
		yPoints[3] = point.y;
	}

	@Override

	public Point getPoint() {
		return point;

	}

	public Color getColor() {
		return color;
	}

	@Override
	public void setPause(boolean pause) {

	}

	public void setType() {
		if (point.x == 1 && point.y == 10) {
			this.type = 1;
		} else if (point.x == 1 && point.y == 70) {
			this.type = 2;
		} else if (point.x == 1 && point.y == 150) {
			this.type = 3;
		} else if (point.x == 1325 && point.y == 10) {
			this.type = 4;
		} else if (point.x == 1325 && point.y == 70) {
			this.type = 5;
		} else if (point.x == 1325 && point.y == 150) {
			this.type = 6;
		}
	}

	@Override
	public int getType() {
		return type;
	}

	@Override
	public int getHigh() {
		return high;
	}

	@Override
	public int getWide() {
		return wide;
	}

	@Override
	public void setAddedToStack(boolean addedToStack) {
		this.addedToStack = addedToStack;
	}

	@Override
	public boolean getAddedToStack() {
		return addedToStack;
	}

	@Override
	public void setIndex(int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLoaded(boolean b) {
		// TODO Auto-generated method stub
		loaded = b;
	}

	@Override
	public boolean getLoaded() {
		// TODO Auto-generated method stub
		return loaded;
	}

}
