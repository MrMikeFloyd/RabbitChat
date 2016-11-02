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
	public static ClientConfig readClientConfigFile() throws FileNotFoundException {

		File configFile = new File(CONFIG_FILE_NAME);
		XStream xstream = new XStream(new DomDriver());
		InputStream in = new FileInputStream(configFile);
		ClientConfig conf = (ClientConfig) xstream.fromXML(in);
		conf.setConfType(ConfigurationType.CLIENT);

		return conf;
	}

	/**
	 * Reads input config file and creates a Config object from it. Determines
	 * the type of config that will be used (Client|Server) depending on the
	 * input file.
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	public static ServerConfig readServerConfigFile() throws FileNotFoundException {

		File configFile = new File(CONFIG_FILE_NAME);
		XStream xstream = new XStream(new DomDriver());
		InputStream in = new FileInputStream(configFile);
		ServerConfig conf = (ServerConfig) xstream.fromXML(in);
		conf.setConfType(ConfigurationType.SERVER);

		return conf;
	}

}
