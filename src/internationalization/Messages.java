package internationalization;

import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

public class Messages {
	
	private static Properties PROP = new Properties();
	
	public static void init() {
		loadProperties();
	}
	
	private static void loadProperties() {
		try {
			PROP.load(new FileInputStream("src/messages.properties"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Properties not loadead.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void showMessageError(String key) {
		JOptionPane.showMessageDialog(null, getMessage(key), "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public static String getMessage(String key) {
		return PROP.getProperty(key);
	}
}
