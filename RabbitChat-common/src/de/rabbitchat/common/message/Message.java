package de.rabbitchat.common.message;

import java.util.Date;

/**
 * Generic message base class with message type used both in client and server
 * applications.<br>
 * Protected access as class may only be instantiated by the respective subclass
 * constructor.
 * 
 * @author maik
 *
 */
public class Message {

	private int id;
	private MsgType type;
	private String recipient;
	private String sender;
	private String payload;
	private Date crtDate;
	

	public Message(int messageId, MsgType messageType, String messageSender, String messageRecipient, String messagePayload) {
		this.id = messageId;
		this.type = messageType;
		this.recipient = messageRecipient;
		this.sender = messageSender;
		this.payload = messagePayload;
		this.crtDate = new Date();
	}

	/*
	 * Getters. Messages must not be changed after initial creation.
	 */

	public int getId() {
		return id;
	}

	public MsgType getType() {
		return type;
	}

	public String getRecipient() {
		return recipient;
	}

	public String getPayload() {
		return payload;
	}
	
	public String getSender() {
		return sender;
	}

	public Date getCrtDate() {
		return crtDate;
	}

}