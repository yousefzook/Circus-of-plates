package MovingShapes;

import java.awt.Point;

import Shapes.IShape;

public class HardMoveStrategy implements IMoveShape {

	private IShape currentShape;
	private Point point;
	private int t;
	private String string;

	public HardMoveStrategy(IShape currentShape, String string, int typpe) {
		super();
		this.currentShape = currentShape;
		this.t = typpe;
		this.string = string;
	}

	public Point type1Moving(Point position, int type) {

		if ((type == 1 && position.x < 500) || (type == 2 && position.x < 300) || (type == 3 && position.x < 100)) {
			position.x+=8;
			return position;
		} else if (position.y > 500) {
			position.y+=8;
			return position;
		}
		position.x+=8;
		position.y+=8;
		return position;
	}

	public Point type2Moving(Point position, int type) {

		if ((type == 4 && position.x > 825) || (type == 5 && position.x > 1025) || (type == 6 && position.x > 1225)) {
			position.x-=8;
			return position;
		} else if (position.y > 500) {
			position.y+=8;
			return position;
		}
		position.x-=8;
		position.y+=8;
		return position;
	}

	@Override
	public void move() {
		this.point = currentShape.getPoint();
		int type = currentShape.getType();
		if (type <= 3)
			point = type1Moving(point, type);
		else
			point = type2Moving(point, type);
		currentShape.setPoint(point);

	}

}
