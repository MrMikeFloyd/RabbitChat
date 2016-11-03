package de.rabbitchat.common.send;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.thoughtworks.xstream.XStream;

import de.rabbitchat.common.message.Message;

/**
 * 
 * @author KleinfeldS
 *
 */
public class Sender {

	private static Sender senderSingleton;

	private ConnectionFactory factory;
	private Connection connection;
	private Channel channel;
	private String defaultSendChannel;

	/**
	 * 
	 * @param userName
	 * @param pw
	 * @param Host
	 * @param defSndChannel
	 */
	private Sender(String userName, String pw, String Host, String defSndChannel) {
		this.factory = new ConnectionFactory();
		this.factory.setUsername(userName);
		this.factory.setPassword(pw);
		this.factory.setHost(Host);
		this.defaultSendChannel = defSndChannel;

	}

	/**
	 * 
	 * @param userName
	 * @param pw
	 * @param Host
	 * @param sendChannel
	 * @return
	 */
	public static Sender getInstance(String userName, String pw, String Host, String sendChannel) {
		if (senderSingleton == null) {
			senderSingleton = new Sender(userName, pw, Host, sendChannel);
		}
		return senderSingleton;
	}

	/**
	 * 
	 * @param queueName
	 * @param messageString
	 * @throws IOException
	 * @throws TimeoutException
	 */
	private void enqueue(String queueName, String messageString) throws IOException, TimeoutException {
		connection = factory.newConnection();
		channel = connection.createChannel();

		channel.basicPublish("", queueName, null, messageString.getBytes("UTF-8"));
		// System.out.println("Sender Logging: [x] Sent '" + messageString +
		// "'");

		channel.close();
		connection.close();
	}

	/**
	 * Performs message enqueuing into specified destination channel. If no
	 * destination channel is supplied, the Sender's default send channel is
	 * being used.
	 * 
	 * @param msg
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public void send(Message msg, String destinationChannel) throws IOException, TimeoutException {

		String destChannel;
		if (destinationChannel == null) {
			destChannel = defaultSendChannel;
		} else {
			destChannel = destinationChannel;
		}

		String msgPayload = msgToXML(msg);
		enqueue(destChannel, msgPayload);
	}

	/**
	 * 
	 * @param msg
	 * @return
	 */
	private String msgToXML(Message msg) {
		XStream xstream = new XStream();
		return (xstream.toXML(msg));
	}

}
