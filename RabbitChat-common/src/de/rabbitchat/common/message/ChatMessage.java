package de.rabbitchat.common.message;

/**
 * Message Type for regular chat messages.
 * 
 * @author maik
 *
 */
public class ChatMessage extends Message {

	public ChatMessage(String messageId, String messageSender, String messageRecipient, String messagePayload) {
		super(messageId, MsgType.CHAT, messageSender, messageRecipient, messagePayload);
	}

}
