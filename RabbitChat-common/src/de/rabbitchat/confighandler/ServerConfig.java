package de.rabbitchat.confighandler;

import java.util.Map;

public class ServerConfig extends Configuration {

	private Map<String, String> queueRecipientMap;

	public ServerConfig(Map<String, String> qMap) {
		this.confType = ConfigurationType.SERVER;
		this.queueRecipientMap = qMap;

	}

	// Getter
	public Map<String, String> getQueueRecipientMap() {
		return queueRecipientMap;
	}

}
