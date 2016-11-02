package de.rabbitchat.common.message;

/**
 * <p>
 * Enumerator data type to indicate the message type.
 * </p>
 * <p>
 * CHAT = Regular Chat Message<br>
 * RCPT = Message Receipt<br>
 * FAIL = Transmission with errors<br>
 * </p>
 * 
 * @author maik
 */
public enum MsgType {
	CHAT, RCPT, FAIL
}
