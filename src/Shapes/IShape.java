/**
 * 
 */
package Shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * @author Dell
 *
 */
public interface IShape {

	/**
	 * Draws the shape
	 * 
	 * @param g
	 *            to draw
	 */
	public void draw(Graphics2D g);

	/**
	 * to set the color of the object
	 * 
	 * @param color
	 *            to set
	 */
	public void setColor(Color color);

	/**
	 * to set the position of the object
	 * 
	 * @param position
	 *            to set
	 */


	public void setType();

	public int getType();

	public Point getPoint();

	public Color getColor();

	public int getHigh();

	public int getWide();

	public void setIndex(int index);

	public int getIndex();


	public void setAddedToStack(boolean addedToStack);

	public boolean getAddedToStack();
	public void setLoaded(boolean b);
	public boolean getLoaded();
	/**
	 * get the position of the shape
	 * 
	 * /
	 * 
	 * 
	 * /** to set the pause boolean
	 * 
	 * @param pause
	 *            to set
	 */
	public void setPause(boolean pause);

	void setPoint(Point point);

}
