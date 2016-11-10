package runner;

import java.io.FileNotFoundException;
import java.util.concurrent.LinkedBlockingQueue;

import de.rabbitchat.common.message.Message;
import de.rabbitchat.common.recv.Receiver;
import de.rabbitchat.confighandler.ConfigReader;
import de.rabbitchat.confighandler.ServerConfig;
import de.rabbitchat.router.handler.Router;

public class ServerConsole {

	public static void main(String[] args) {

		System.out.println("RabbitChat Server");
		System.out.println("-------------------------------");

		// Start with an empty config
		ServerConfig serverConfig = new ServerConfig("", "", "", "", "", "", null);
		LinkedBlockingQueue<Message> receivedMsgs = new LinkedBlockingQueue<Message>();

		// Populate the Server config
		try {
			System.out.println("[MAIN]: Loading configuration file.");
			serverConfig = ConfigReader.readServerConfigFile();
		} catch (FileNotFoundException e) {
			System.out.println("[MAIN]: Configuration file not found - bailing out.");
			e.printStackTrace();
			System.exit(1);
		}

		// Start threads for dequeuing and message routing
		Receiver receiver = Receiver.getInstance(serverConfig.getServerUser(), serverConfig.getServerPass(), serverConfig.getServerHost(),
				serverConfig.getDefaultReceiveChannel(), receivedMsgs);

		try {
			System.out.println("[MAIN]: Beginning message receiving.");
			receiver.call();
		} catch (Exception e) {
			System.out.println("[MAIN]: Ran into an error while receiving messages:");
			e.printStackTrace();
		}

		Router messageRouter = Router.getInstance(serverConfig, receivedMsgs);
		try {
			System.out.println("[MAIN]: Beginning message routing.");
			messageRouter.call();
		} catch (Exception e) {
			System.out.println("[MAIN]: Ran into an error during message routing:");
			e.printStackTrace();
		}

	}

}
