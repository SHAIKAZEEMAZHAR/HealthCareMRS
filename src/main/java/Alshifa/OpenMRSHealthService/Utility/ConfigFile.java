package Alshifa.OpenMRSHealthService.Utility;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigFile {
	
	private static Properties properties = new Properties();
	
	public ConfigFile() {}
	
	static {
		
		try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties")) {
			properties.load(fileInputStream);
		} catch (Exception e) {
			throw new RuntimeException("config.properties file load error : " + e.getMessage());
		}
	}
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}
