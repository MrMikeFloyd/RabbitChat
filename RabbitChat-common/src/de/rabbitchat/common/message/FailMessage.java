package de.rabbitchat.common.message;

/**
 * Message container for failed transactions.
 * 
 * @author maik
 *
 */
public class FailMessage extends Message {

	public FailMessage(String messageId, String messageSender, String messageRecipient, String messagePayload) {
		super(messageId, MsgType.FAIL, messageSender, messageRecipient, messagePayload);
	}

}
