package de.rabbitchat.router.handle;

import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;

import de.rabbitchat.common.message.Message;
import de.rabbitchat.common.send.Sender;
import de.rabbitchat.confighandler.ServerConfig;

/**
 * Message router. Reads messages from local queue, and enqueues into respective
 * target queue based on the routing table (to be set in the server's config
 * file).
 * 
 * @author maik
 *
 */
public class Router implements Callable<Integer> {

	// Sender instance to use for enqueuing messages
	private Sender messageSender;
	// Server configuration to use
	private ServerConfig currentConfig;
	// Internal message queue with all currently available dequeued messages
	private LinkedBlockingQueue<Message> messageQueue;

	public Router(ServerConfig serverCfg, LinkedBlockingQueue<Message> msgQueue) {
		this.currentConfig = serverCfg;
		this.messageQueue = msgQueue;

		// Initialize message sender
		messageSender = Sender.getInstance(serverCfg.getServerUser(), serverCfg.getServerPass(), serverCfg.getServerHost(),
				serverCfg.getDefaultReceiveChannel());
	}

	/**
	 * Looks up the recipient's target channel from the Queue Recipient Map.
	 * Returns null, if no route could be found.
	 * 
	 * @param recipientName
	 * @return The recipient's target queue.
	 */
	private String getTargetChannel(String recipientName) {
		return currentConfig.getQueueRecipientMap().get(recipientName);

	}

	/**
	 * Extracts the message header and performs message routing.
	 * 
	 * @param msg
	 */
	private void routeMessage(Message msg) {
		String recipient = msg.getRecipient();
		String targetChannel = getTargetChannel(recipient);
		messageSender.send(msg);

	}

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
