package de.rabbitchat.confighandler;

public class ClientConfig extends Configuration {

	private String serverHost;
	private String serverUser;
	private String serverPass;
	private String defaultSendChannel;

	public ClientConfig(String srvHost, String srvUser, String srvPass, String defSndChannel) {
		this.confType = ConfigurationType.CLIENT;
		this.serverHost = srvHost;
		this.serverPass = srvPass;
		this.serverUser = srvUser;
		this.defaultSendChannel = defSndChannel;

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

}
