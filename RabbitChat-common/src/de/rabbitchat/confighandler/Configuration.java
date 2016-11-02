package de.rabbitchat.confighandler;

/**
 * Configuration class. Used for conversion of permanently stored config
 * settings.
 * 
 * @author maik
 *
 */
public class Configuration {

	// Configuration Type ([Client|Server])
	ConfigurationType confType;

	// Getter
	public ConfigurationType getConfType() {
		return confType;
	}

	// Setter
	protected void setConfType(ConfigurationType cType) {
		this.confType = cType;
	}

}
