package ShapesFactory;

import Shapes.Rectangle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import Control.DynamicLoading;
import Shapes.Circle;
import Shapes.IShape;
import Control.ClassLoader;

/**
 * a class to return new objects of shapes randomly
 * 
 * @author Dell
 *
 */
public class NewShapeFactory {

	private ClassLoader loader;
	private LinkedList<Class> shapesArr;
	private Class newClass;
	private boolean loaded;
	private IShape obj;

	public NewShapeFactory() {
		shapesArr = new LinkedList<Class>();
		shapesArr.add(Rectangle.class);
		shapesArr.add(Circle.class);
		loaded = false;
		loader = ClassLoader.getInstance();
	}

	public void setLoaded(boolean b) {
		loaded = b;
		loader.getFile();
	}

	// if loaded class
	private void loadClass() {
		newClass = loader.loadingFn();
		if (newClass != null) {
			shapesArr.add(newClass);
		}
	}

	// if no loaded class
	public IShape getShape() {

		if (loaded) {
			loadClass();
			loaded = false;
		}

		Random rand = new Random();
		int random = rand.nextInt(50) + 1;
		random = random % shapesArr.size();
		for (int i = 0; i < shapesArr.size(); i++) {
			if (random == i) {
				try {
					return (IShape) shapesArr.get(i).newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					System.out.println("Error in loading Class");
					e.printStackTrace();
				}
			}
		}

		return null;
	}
}
