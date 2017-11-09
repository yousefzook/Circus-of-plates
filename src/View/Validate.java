package View;

import java.awt.Point;
import java.io.IOException;

import Shapes.IShape;

public class Validate extends GamePanel {

	public Validate(Frame caller) throws IOException {
		super(caller);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	public static boolean type1Draw(IShape currentShape, Point position,
			int type) {

		if (position.x == 1) {
			if (position.y == 10) {
				if (!type1) {
					type1 = true;
					return true;
				} else {
					Point positionn = new Point(1, 10);
					currentShape.setPoint(positionn);
					return false;
				}
			} else if (position.y == 70) {
				if (!type1) {
					type1 = true;
					return true;
				} else {
					Point positionn = new Point(1, 10);
					currentShape.setPoint(positionn);
					return false;
				}
			} else if (position.y == 150) {
				if (!type3) {
					type3 = true;
					return true;
				} else {
					Point positionn = new Point(1, 10);
					currentShape.setPoint(positionn);
					return false;
				}
			}
		} else if (position.y == 10 || position.y == 70 || position.y == 150) {
			if (position.x <= 70 && type == 1) {
				if (!type1) {
					type1 = true;
					return true;
				} else {
					Point positionn = new Point(1, 10);
					currentShape.setPoint(positionn);
					return false;
				}
			} else if (position.x <= 70 && type == 2) {
				if (!type2) {
					type2 = true;
					return true;
				} else {
					Point positionn = new Point(1, 70);
					currentShape.setPoint(positionn);
					return false;
				}
			} else if (position.x <= 70 && type == 3 && !type3) {
				if (!type3) {
					type3 = true;
					return true;
				} else {
					Point positionn = new Point(1, 150);
					currentShape.setPoint(positionn);
					return false;
				}
			} else if (position.x > 70) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	public static boolean type2Draw(IShape currentShape, Point position,
			int type) {
		if (position.x == 1325) {
			if (position.y == 10) {
				if (!type4) {
					type4 = true;
					return true;
				} else {
					Point positionn = new Point(1325, 10);
					currentShape.setPoint(positionn);
					return false;
				}
			} else if (position.y == 70) {
				if (!type5) {
					type5 = true;
					return true;
				} else {
					Point positionn = new Point(1325, 70);
					currentShape.setPoint(positionn);
					return false;
				}
			} else if (position.y == 150) {
				if (!type6) {
					type6 = true;
					return true;
				} else {
					Point positionn = new Point(1325, 150);
					currentShape.setPoint(positionn);
					return false;
				}
			}
		} else if (position.y == 10 || position.y == 70 || position.y == 150) {
			if (position.x >= 1255 && type == 4) {
				if (!type4) {
					type4 = true;
					return true;
				} else {
					Point positionn = new Point(1325, 10);
					currentShape.setPoint(positionn);
					return false;
				}
			} else if (position.x >= 1255 && type == 5) {
				if (!type5) {
					type5 = true;
					return true;
				} else {
					Point positionn = new Point(1325, 70);
					currentShape.setPoint(positionn);
					return false;
				}
			} else if (position.x >= 1255 && type == 6) {
				if (!type6) {
					type6 = true;
					return true;
				} else {
					Point positionn = new Point(1325, 150);
					currentShape.setPoint(positionn);
					return false;
				}
			} else if (position.x < 1255) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

}
///////////// 2840 line almost 