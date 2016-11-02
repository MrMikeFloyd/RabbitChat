package de.rabbitchat.router.handle;

import java.util.concurrent.LinkedBlockingQueue;

import de.rabbitchat.common.message.Message;

/**
 * Performs dequeuing of messages that reside in the receive queue. Stores
 * received messages in local queue for message routing. (Basically a mere
 * wrapper for {@link de.rabbitchat.common.recv})
 * 
 * @author maik
 *
 */
public class DequeueHandler implements Runnable {

	private String deqUser;
	private String deqPass;
	private String deqHost;
	private String deqRecvChannel;
	private LinkedBlockingQueue<Message> dequeuedMsgs;

	public DequeueHandler(String deqUsr, String deqPw, String deqHst, String deqRcvChnl, LinkedBlockingQueue<Message> deqMsgs) {
		this.deqUser = deqUsr;
		this.deqPass = deqPw;
		this.deqHost = deqHst;
		this.deqRecvChannel = deqRcvChnl;
		this.dequeuedMsgs = deqMsgs;
	}

	/**
	 * Dequeues messages currently in receive queue and writes them to local
	 * queue for further processing.
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
