package Control;

import java.util.Stack;

import Shapes.IShape;

public interface Iterator {
	
	boolean hasNext(Stack<IShape> stack);
	IShape next(Stack<IShape> stack);

}
