package ui;
import java.io.File;

import javax.swing.filechooser.FileFilter;

public class JSFilter extends FileFilter {

	@Override
	public boolean accept(File pathname) {
		String extension = getExtension(pathname);
		if (pathname.isDirectory())
			return true;
		if (pathname.isFile() && extension.equals("js"))
			return true;
		return false;
	}
	
	@Override
	public String getDescription() {
		return "*.js";
	}

	private String getExtension(File f) {
		String extension = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			extension = s.substring(i + 1).toLowerCase();
		}
		return extension;
	}
}
