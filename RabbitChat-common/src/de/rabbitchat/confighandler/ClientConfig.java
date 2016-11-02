package de.rabbitchat.confighandler;

/**
 * Client configuration class. Holds relevant client-side configuration settings
 * that are required at the time of initialization.
 * 
 * @author maik
 *
 */
public class ClientConfig extends Configuration {

	private String serverHost;
	private String serverUser;
	private String serverPass;
	private String defaultSendChannel;
	private String receiveChannel;

	public ClientConfig(String srvHost, String srvUser, String srvPass, String defSndChannel, String recvChannel) {
		this.confType = ConfigurationType.CLIENT;
		this.serverHost = srvHost;
		this.serverPass = srvPass;
		this.serverUser = srvUser;
		this.defaultSendChannel = defSndChannel;
		this.receiveChannel = recvChannel;

	}

	// Getter
	public String getServerHost() {
		return serverHost;
	}

	public String getServerUser() {
		return serverUser;
	}

	public String getServerPass() {
		return serverPass;
	}

	public String getDefaultSendChannel() {
		return defaultSendChannel;
	}

	public String getReceiveChannel() {
		return receiveChannel;
	}

}
