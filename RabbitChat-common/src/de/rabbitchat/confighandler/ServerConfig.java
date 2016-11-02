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
	// Channel on which to listen for incoming messages
	private String defaultReceiveChannel;
	// Channel for messages unrouteable messages (i.e. recipient unknown)
	private String deadLetterChannel;
	// Recipient List (assigns recipient names to queue names)
	private Map<String, String> queueRecipientMap;

	// Constructor.
	public ServerConfig(String srvHost, String srvUser, String srvPass, String defRcvChannel, String deadLetterChnl,
			Map<String, String> qMap) {
		this.confType = ConfigurationType.SERVER;

		this.serverHost = srvHost;
		this.serverUser = srvUser;
		this.serverPass = srvPass;
		this.defaultReceiveChannel = defRcvChannel;
		this.deadLetterChannel = deadLetterChnl;
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

	public String getDeadLetterChannel() {
		return deadLetterChannel;
	}

	public Map<String, String> getQueueRecipientMap() {
		return queueRecipientMap;
	}

}
