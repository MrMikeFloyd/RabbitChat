package de.rabbitchat.common.message;

/**
 * Message Type for delivery receipts.
 * 
 * @author maik
 *
 */
public class RcptMessage extends Message {

	public RcptMessage(int messageId, String messageRecipient, String messagePayload) {
		super(messageId, MsgType.RCPT, messageRecipient, messagePayload);
	}

}
