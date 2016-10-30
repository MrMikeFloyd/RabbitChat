package de.rabbitchat.common.test;

import de.rabbitchat.confighandler.ConfigReader;
import de.rabbitchat.confighandler.Configuration;

/**
 * Reads simple config file and checks if reading it works.
 * 
 * @author maik
 *
 */
public class TestConfigReader {

	public static void main(String[] args) {

		try {
			Configuration c1 = ConfigReader.readConfigFile();
			System.out.println(c1.getClass() + " " + c1.getConfType());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
