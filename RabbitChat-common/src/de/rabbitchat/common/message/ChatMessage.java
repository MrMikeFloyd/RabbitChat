package de.rabbitchat.common.message;

/**
 * Message Type for regular chat messages.
 * 
 * @author maik
 *
 */
public class ChatMessage extends Message {

	public ChatMessage(int messageId, String messageRecipient, String messagePayload) {
		super(messageId, MsgType.CHAT, messageRecipient, messagePayload);
	}

}
