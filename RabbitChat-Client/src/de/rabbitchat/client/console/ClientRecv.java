package de.rabbitchat.client.console;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeoutException;

import de.rabbitchat.common.message.Message;
import de.rabbitchat.common.recv.Receiver;
import de.rabbitchat.confighandler.ClientConfig;

/**
 * 
 * @author KleinfeldS
 *
 */
public class ClientRecv implements Callable<Integer> {

	ClientConfig config;
	LinkedBlockingQueue<Message> inputMsgQueue;
	
	Receiver receiver;
	
	ClientRecv(ClientConfig config,LinkedBlockingQueue<Message> inputMsgQueue){
		this.config = config;
		this.inputMsgQueue = inputMsgQueue;
		this.receiver =  Receiver.getInstance(config.getServerUser(), config.getServerPass(), config.getServerHost(), config.getReceiveChannel(), inputMsgQueue);
	}
	/**
	 * Starts receiving and dequeuing
	 * @param config
	 * @throws Exception
	 */
	private  void startRecv() throws Exception{		
		receiver.call();
		dequeue();
	}
	
	
	/**
	 * Dequeues messages from Queue and prints them 
	 * @throws InterruptedException 
	 * @throws TimeoutException 
	 * @throws IOException 
	 * 
	 */
	private  void dequeue() throws InterruptedException, IOException, TimeoutException{
		
		while(true){
			Message m1 = inputMsgQueue.take();
			
			switch (m1.getType()) {
			case CHAT:
				System.out.println("Incoming Message from " + m1.getRecipient());
				System.out.println("Created at: " + m1.getCrtDate().getTime());
				System.out.println("---------\n" + m1.getPayload()+"\n---------\nReading confirmation is sent.");
				String msg = "Message: " + m1.getId() + " was readed";
				ClientSend.sendRcptMessage(config, m1.getId(), m1.getSender(), msg);
				break;
				
			case RCPT:
				System.out.println(m1.getPayload());
				break;
			case FAIL:
				System.out.println("Message: " + m1.getId() + " failed!!!");
				break;
			}
		}
		
	}

	@Override
	public Integer call() throws Exception {
		startRecv();
		return null;
	}
	
	
	
}
