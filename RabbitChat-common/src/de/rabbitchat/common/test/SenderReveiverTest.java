package de.rabbitchat.common.test;

import java.util.concurrent.LinkedBlockingQueue;

import de.rabbitchat.common.message.ChatMessage;
import de.rabbitchat.common.message.Message;
import de.rabbitchat.common.recv.Receiver;
import de.rabbitchat.common.send.Sender;

public class SenderReveiverTest {

	public static void main1(String[] args) {
		LinkedBlockingQueue<Message> q1 = new LinkedBlockingQueue<Message>();

		Sender s1 = Sender.getInstance("rabbitclient-A", "123456", "192.168.2.108", "rabbitchat-global-input1");
		ChatMessage cm1 = new ChatMessage("Simon1234", "Simon", "Maik", "Sender base class messaging test.");

		try {
			s1.send(cm1, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// class FetchMsgs implements Runnable {
		//
		// public void run() {
		// System.out.println("Starting Fetch Thread.");
		// Receiver r1 = Receiver.getInstance("rabbitclient-B", "123456",
		// "192.168.2.108", "rabbitchat-global-input1", q1);
		//
		// try {
		// r1.startReceiving();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		//
		// }
		//
		// }
		// new Thread(new FetchMsgs()).start();

		Receiver r1 = Receiver.getInstance("rabbitclient-B", "123456", "192.168.2.108", "rabbitchat-global-input1", q1);
		try {
			r1.call();
		} catch (Exception e) {
			e.printStackTrace();
		}

		class DequeueReceivedMsgs implements Runnable {

			public void run() {
				System.out.println("Starting Dequeue Thread.");
				try {
					Message m1 = q1.take();
					System.out.println(m1.getId() + " " + m1.getRecipient() + " " + m1.getCrtDate() + " " + m1.getPayload());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
		new Thread(new DequeueReceivedMsgs()).start();

	}

}
