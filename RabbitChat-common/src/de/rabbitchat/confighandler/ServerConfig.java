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
	// Channel for unrouteable messages (i.e. recipient unknown)
	private String deadLetterChannel;
	// Channel for messages that don't meet valid message criteria
	private String invalidMessageChannel;

	// Recipient List (assigns recipient names to queue names)
	private Map<String, String> queueRecipientMap;

	// Constructor.
	public ServerConfig(String srvHost, String srvUser, String srvPass, String defRcvChannel, String deadLetterChnl, String invalMsgChnl,
			Map<String, String> qMap) {
		this.confType = ConfigurationType.SERVER;

		this.serverHost = srvHost;
		this.serverUser = srvUser;
		this.serverPass = srvPass;
		this.defaultReceiveChannel = defRcvChannel;
		this.deadLetterChannel = deadLetterChnl;
		this.invalidMessageChannel = invalMsgChnl;
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

	public String getInvalidMessageChannel() {
		return invalidMessageChannel;
	}

	public Map<String, String> getQueueRecipientMap() {
		return queueRecipientMap;
	}

}
