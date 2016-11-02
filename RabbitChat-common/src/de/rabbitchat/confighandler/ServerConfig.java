package de.rabbitchat.confighandler;

import java.util.Map;

/**
 * Server configuration class. Holds relevant server-side configuration settings
 * that are required at the time of initialization.
 * 
 * @author maik
 *
 */
public class ServerConfig extends Configuration {

	private String serverHost;
	private String serverUser;
	private String serverPass;
	private String defaultReceiveChannel;

	// Recipient List (assigns recipient names to queue names)
	private Map<String, String> queueRecipientMap;

	public ServerConfig(String srvHost, String srvUser, String srvPass, String defRcvChannel, Map<String, String> qMap) {
		this.confType = ConfigurationType.SERVER;

		this.serverHost = srvHost;
		this.serverUser = srvUser;
		this.serverPass = srvPass;
		this.defaultReceiveChannel = defRcvChannel;
		this.queueRecipientMap = qMap;
	}

	// Getters

	public String getServerHost() {
		return serverHost;
	}

	public String getServerUser() {
		return serverUser;
	}

	public String getServerPass() {
		return serverPass;
	}

	public String getDefaultReceiveChannel() {
		return defaultReceiveChannel;
	}

	public Map<String, String> getQueueRecipientMap() {
		return queueRecipientMap;
	}

}
