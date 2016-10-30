package de.rabbitchat.confighandler;

/**
 * Configuration class. Used for conversion of permanently stored config
 * settings.
 * 
 * @author maik
 *
 */
public class Configuration {

	ConfigurationType confType;

	// Setter
	protected void setConfType(ConfigurationType cType) {
		this.confType = cType;
	}

	// Getter
	public ConfigurationType getConfType() {
		return confType;
	}

}
