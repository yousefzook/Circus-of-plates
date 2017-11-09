package Shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Circle implements IShape {

	private Color color;
	private Point point;
	private int raduios = 30;
	private int type = 0;
	private int index=0;
	private boolean loaded = false;
	private boolean addedToStack=false;

	@Override
	public void draw(Graphics2D g) {
		g.drawOval(point.x, point.y, raduios, raduios);
		g.setColor(color);
//		g.setStroke(new BasicStroke(5));
		g.fillOval(point.x, point.y, raduios, raduios);

	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override

	public Point getPoint() {
		return point;
	}

	@Override

	public void setPoint(Point point) {
		this.point = point;
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
		return raduios;
	}

	@Override
	public int getWide() {
		return raduios;
	}
	@Override
	public void setAddedToStack(boolean addedToStack) {
		this.addedToStack=addedToStack;
	}

	@Override
	public boolean getAddedToStack() {
		return addedToStack;
	}

	@Override
	public void setIndex(int index) {
		this.index=index;
	}

	@Override
	public int getIndex() {
		return index;
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
