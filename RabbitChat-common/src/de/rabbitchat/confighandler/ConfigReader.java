package de.rabbitchat.confighandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Reads configuration file and transforms it into respective config object.
 * 
 * @author maik
 *
 */
public class ConfigReader {

	public static final String CONFIG_FILE_NAME = "config.xml";

	/**
	 * Reads input config file and creates a Config object from it. Determines
	 * the type of config that will be used (Client|Server) depending on the
	 * input file.
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Configuration readConfigFile() throws FileNotFoundException {

		File configFile = new File(CONFIG_FILE_NAME);
		XStream xstream = new XStream(new DomDriver());
		InputStream in = new FileInputStream(configFile);
		Configuration conf = (Configuration) xstream.fromXML(in);

		// As XStream doesn't call our constructors, determine which Class we
		// are dealing with and set the type accordingly.
		if (conf instanceof ClientConfig) {
			conf.setConfType(ConfigurationType.CLIENT);
		} else if (conf instanceof ServerConfig) {
			conf.setConfType(ConfigurationType.SERVER);
		} else {
			throw new IllegalArgumentException("Something went terribly wrong. How the hell did you get here!?");
		}

		return conf;
	}

}
