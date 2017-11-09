package ShapesFactory;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

//import Shapes.Circle;
import Shapes.IShape;

/**
 * a class to reuse the objects of shapes
 * 
 * @author Dell
 *
 */
public class ReusablePool {

	private ArrayList<IShape> freeShapes;
	private ArrayList<IShape> usedShapes;
	private static ReusablePool uniqueInstance;
	private NewShapeFactory newShapeFactory;
	private boolean loaded;

	private ReusablePool() {
		loaded = false;
		freeShapes = new ArrayList<IShape>();
		usedShapes = new ArrayList<IShape>();
		newShapeFactory = new NewShapeFactory();
	}

	public void setLoaded(boolean b) {
		loaded = b;
		newShapeFactory.setLoaded(b);
	}

	public boolean getLoaded() {
		return loaded;
	}

	// to get the unique instance of the class (Singleton)
	public static ReusablePool getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new ReusablePool();
		return uniqueInstance;
	}

	private Point randomPosition() {
		int x = 900, y = 10;
		Random rand = new Random();
		int random = rand.nextInt(50) + 1;
		if (random % 6 == 0) {
			x = 1;
			y = 10;
		} else if (random % 6 == 1) {
			x = 1;
			y = 70;
		} else if (random % 6 == 2) {
			x = 1;
			y = 150;
		} else if (random % 6 == 3) {
			x = 1325;
			y = 10;
		} else if (random % 6 == 4) {
			x = 1325;
			y = 70;
		} else {
			x = 1325;
			y = 150;
		}
		return new Point(x, y);
	}

	private Color randomColor() {

		Color randomColor = null;
		Random rand = new Random();
		int random = rand.nextInt(50) + 1;
		if (random % 3 == 0)
			randomColor = new Color(255, 0, 0);
		else if (random % 3 == 1)
			randomColor = new Color(0, 255, 0);
		else
			randomColor = new Color(0, 0, 255);
		return randomColor;
	}

	// to get a shape to use
	public IShape acquireShape() {
		IShape shape;
		if (loaded) {
			for (int i = 0; i < freeShapes.size(); i++) {
				shape = newShapeFactory.getShape();
				freeShapes.set(i, shape);
			}
		}
		if (freeShapes.isEmpty())
			shape = newShapeFactory.getShape();
		else
			shape = freeShapes.remove(freeShapes.size() - 1);
		usedShapes.add(shape);
		loaded = false;
		shape.setPoint(randomPosition());
		shape.setType();
		shape.setAddedToStack(false);
		shape.setColor(randomColor());
		return shape;
	}

	// to release a shape which is not used now
	public void releaseShape(IShape shape) {
		if (usedShapes.remove(shape)) {
			freeShapes.add(shape);
			shape.setAddedToStack(false);
		} else
			System.out.println("Error there is no used shapes");
	}
}
