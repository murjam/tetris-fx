package ee.itcollege.tetris.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationService {
	
	private Properties properties = new Properties();	
	
	public void loadFile(String fileName) throws FileNotFoundException, IOException {
		properties.load(new FileInputStream(new File(fileName)));
	}
	
	public String getStringValue(String key) {
		String value = properties.getProperty(key);
		if (null == value) {
			return null;
		}
		return value.trim();
	}
	
	public int getIntValue(String key) {
		return Integer.parseInt(getStringValue(key));
	}
	
}
