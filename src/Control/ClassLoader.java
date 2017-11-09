package Control;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import Shapes.IShape;
import Shapes.Rectangle;
import ShapesFactory.ReusablePool;

public class ClassLoader {

	private JFileChooser chooser;
	private DynamicLoading classLoader;
	private String url;
	private static ClassLoader uniqueInstance;

	public static ClassLoader getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new ClassLoader();
		return uniqueInstance;
	}

	private ClassLoader() {
		url = null;
		chooser = new JFileChooser();
		classLoader = new DynamicLoading();
	}

	public void getFile() {
		File file = chooseFn();
		if (file == null)
			url = null;
		else
			url = file.getAbsolutePath();
	}

	private File chooseFn() {
		FileNameExtensionFilter txtFilter = new FileNameExtensionFilter(
				"Jar files (*.jar)", "jar");
		chooser.addChoosableFileFilter(txtFilter);
		chooser.setFileFilter(txtFilter);
		chooser.showOpenDialog(null);
		File file = chooser.getSelectedFile();
		return file;
	}

	public Class loadingFn() {
		if (url == null)
			return null;
		classLoader.SetClassPath(url);
		try {
			return classLoader.loadClass();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
