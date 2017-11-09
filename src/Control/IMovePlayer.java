/**
 * 
 */
package Control;

import javax.swing.JComponent;

/**
 * an interface common between the all move strategy classes
 * @author Dell
 *
 */
public interface IMovePlayer  {
	
	
	public void move();
	public void move(int x , int y);
	public void recive_Component(JComponent Component);
	public JComponent getPlayerComponent();
	public void modify(JComponent Component);
}
