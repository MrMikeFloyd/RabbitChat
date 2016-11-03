package de.rabbitchat.common.recv;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.thoughtworks.xstream.XStream;

import de.rabbitchat.common.message.Message;

/**
 * Receiver base class. Callable, can thus be executed in a separate subthread.
 * 
 * @author KleinfeldS
 *
 */
public class Receiver implements Callable<Integer> {

	private static Receiver receiverSingleton;

	private ConnectionFactory factory;
	private Connection connection;
	private Channel channel;
	private String receiveChannel;
	private LinkedBlockingQueue<Message> receivedMsgs;

	/**
	 * 
	 * @param userName
	 * @param pw
	 * @param Host
	 * @param rcvChannel
	 * @param receivedMsgs
	 */
	private Receiver(String userName, String pw, String Host, String rcvChannel, LinkedBlockingQueue<Message> receivedMsgs) {
		this.factory = new ConnectionFactory();
		this.factory.setUsername(userName);
		this.factory.setPassword(pw);
		this.factory.setHost(Host);
		this.receiveChannel = rcvChannel;
		this.receivedMsgs = receivedMsgs;

	}

	/**
	 * 
	 * @param userName
	 * @param pw
	 * @param Host
	 * @param sendChannel
	 * @param receivedMsgs
	 * @return
	 */
	public static Receiver getInstance(String userName, String pw, String Host, String sendChannel,
			LinkedBlockingQueue<Message> receivedMsgs) {
		if (receiverSingleton == null) {
			receiverSingleton = new Receiver(userName, pw, Host, sendChannel, receivedMsgs);
		}
		return receiverSingleton;
	}

	/**
	 * 
	 * @throws IOException
	 * @throws TimeoutException
	 */
	private void startReceiving() throws IOException, TimeoutException {
		connection = factory.newConnection();
		channel = connection.createChannel();
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
					throws IOException {

				String message = new String(body, "UTF-8");
				Message msg = xmlToMsg(message);

				receivedMsgs.add(msg);
				// System.out.println(" [x] Received '" + message + "'");
			}
		};
		channel.basicConsume(receiveChannel, true, consumer);
	}

	/**
	 * 
	 * @param xml
	 * @return
	 */
	private Message xmlToMsg(String xml) {
		XStream xstream = new XStream();
		return ((Message) xstream.fromXML(xml));
	}

	/**
	 * Run method. Will start the receiving process against the assigned queue.
	 */
	@Override
	public Integer call() throws Exception {
		startReceiving();
		return 0;
	}

}
