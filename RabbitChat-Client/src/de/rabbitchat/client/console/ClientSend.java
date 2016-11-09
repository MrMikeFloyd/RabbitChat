package de.rabbitchat.client.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;

import de.rabbitchat.common.message.ChatMessage;
import de.rabbitchat.common.message.RcptMessage;
import de.rabbitchat.common.send.Sender;
import de.rabbitchat.confighandler.ClientConfig;

/**
 * Client Class for sending messages
 * @author KleinfeldS
 *
 */
public class ClientSend {

	/**
	 * Sends new Message with given config
	 * @param config
	 * @param msgId
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public static void sendChatMessage(ClientConfig config, int msgId) throws IOException, TimeoutException{
		String id = config.getDefaultSenderName() + "." + msgId; //concat id with sendername
		String recipient = getRecipient();
		String messagePayload = getMessagePayload();
		Sender.getInstance(config.getServerUser(), config.getServerPass(), config.getServerHost(), config.getDefaultSendChannel()).send(new ChatMessage(id, config.getDefaultSenderName(), recipient, messagePayload), null); //TODO richtigen Aufruf zum Benutzernamen reinmachen

	}
	
	/**
	 * Sends new rcpt Message with given config
	 * @param config
	 * @param msgId
	 * @param receiver
	 * @param msgPayload
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public static void sendRcptMessage(ClientConfig config, String msgId, String receiver, String msgPayload) throws IOException, TimeoutException{
		Sender.getInstance(config.getServerUser(), config.getServerPass(), config.getServerHost(), config.getDefaultSendChannel()).send(new RcptMessage(msgId, config.getDefaultSenderName(), receiver, msgPayload ), null); // TODO siehe 
	}
	
	/**
	 * User has to specify the recipient
	 * @return
	 * @throws IOException
	 */
	private static String getRecipient() throws IOException{
		System.out.println("\n---------------------------------------------------");
		System.out.println("Enter Recipient:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String receiver = br.readLine();
		return receiver;
	}
	
	/**
	 * User has to give in the payload of his message
	 * @return
	 * @throws IOException
	 */
	private static String getMessagePayload() throws IOException{
		System.out.println("\n---------------------------------------------------");
		System.out.println("Enter Message");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String messagePayload = br.readLine();
		return messagePayload;
	}
	
	
}
