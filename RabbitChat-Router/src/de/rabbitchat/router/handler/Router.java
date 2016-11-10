package de.rabbitchat.router.handler;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeoutException;

import de.rabbitchat.common.message.Message;
import de.rabbitchat.common.message.MsgType;
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

	private static Router routerSingleton;

	// Sender instance to use for enqueuing messages
	private Sender messageSender;
	// Server configuration to use
	private ServerConfig currentConfig;
	// Internal message queue with all currently available dequeued messages
	private LinkedBlockingQueue<Message> messageQueue;

	private Router(ServerConfig serverCfg, LinkedBlockingQueue<Message> msgQueue) {
		this.currentConfig = serverCfg;
		this.messageQueue = msgQueue;

		// Initialize message sender
		messageSender = Sender.getInstance(serverCfg.getServerUser(), serverCfg.getServerPass(), serverCfg.getServerHost(),
				serverCfg.getDefaultReceiveChannel());
	}

	public static Router getInstance(ServerConfig serverCfg, LinkedBlockingQueue<Message> msgQueue) {
		if (routerSingleton == null) {
			routerSingleton = new Router(serverCfg, msgQueue);
		}
		return routerSingleton;
	}

	/**
	 * Looks up the recipient's target channel from the Queue Recipient Map.
	 * Defaults to dead letter channel, if no entry in Queue Recipient Map is
	 * found.
	 * 
	 * @param recipientName
	 * @return The recipient's target queue.
	 */
	private String getTargetChannel(String recipientName) {
		String targetChannel = currentConfig.getQueueRecipientMap().get(recipientName);
		if (targetChannel == null) {
			targetChannel = currentConfig.getDeadLetterChannel();
		}
		System.out.println("[ROUTER-ROUTE]: Route lookup yields " + recipientName + " --> " + targetChannel);
		return targetChannel;
	}

	/**
	 * Extracts the message recipient from the message header and enqueues the
	 * message in the respective target channel.
	 * 
	 * @param msg
	 * @throws TimeoutException
	 * @throws IOException
	 */
	private void routeMessage(Message msg) throws IOException, TimeoutException {

		// Only process message if it is a chat, rcpt or fail message, throw to
		// invalid
		// message channel otherwise
		if (msg.getType() == MsgType.CHAT || msg.getType() == MsgType.RCPT || msg.getType() == MsgType.FAIL) {
			String recipient = msg.getRecipient();
			String targetChannel = getTargetChannel(recipient);
			System.out.println("[ROUTER-POSTROUTE]: Message id " + msg.getId() + " is being forwarded to channel " + targetChannel + ".");

			messageSender.send(msg, targetChannel);
		} else {

			System.out.println(
					"[ROUTER-POSTROUTE]: Message with id " + msg.getId() + " is of unknown type - forwarding to invalid message channel. ");
			messageSender.send(msg, currentConfig.getInvalidMessageChannel());
		}

	}

	/**
	 * Dequeues messages and triggers message routing.
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws TimeoutException
	 */
	private void handleMessages() throws InterruptedException, IOException, TimeoutException {
		while (true) {

			Message msg = messageQueue.take();
			System.out.println("[ROUTER-PREPROC]: Processing message of type " + msg.getType() + ", id: " + msg.getId() + " from: "
					+ msg.getSender() + " to: " + msg.getRecipient());
			routeMessage(msg);
		}

	}

	/**
	 * Starts dequeueing/routing according to the Server's configuration.
	 */
	@Override
	public Integer call() throws Exception {
		handleMessages();

		return 0;
	}

}
