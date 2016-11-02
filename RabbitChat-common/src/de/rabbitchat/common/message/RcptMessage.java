package de.rabbitchat.common.message;

/**
 * Message Type for delivery receipts.
 * 
 * @author maik
 *
 */
public class RcptMessage extends Message {

	public RcptMessage(String messageId, String messageSender, String messageRecipient, String messagePayload) {
		super(messageId, MsgType.RCPT, messageSender, messageRecipient, messagePayload);
	}

}
