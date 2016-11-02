package de.rabbitchat.client.console;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.rabbitchat.common.message.Message;
import de.rabbitchat.confighandler.ClientConfig;
import de.rabbitchat.confighandler.ConfigReader;

public class ClientMain {
	
	 
	
	public static void main(String[] args) {
		System.out.println("Welcome to RabbitChat!");
		int id = 0;
		LinkedBlockingQueue<Message> inputMsgQueue = new LinkedBlockingQueue<Message>();
		/**
		 * Load Client Configuration
		 * config.xml must be in the program root folder
		 */
		ClientConfig config = new ClientConfig("", "", "", "", "", "");
		try {
			config = ConfigReader.readClientConfigFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} 
		
		Boolean run = true;
		while(run){
			/**
			 * Determine the Client modus
			 * Send or Receive modus
			 */
			String modus = "";
			try {
				modus = determineModus();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
			
			
			
			/**
			 * Start specified Service
			 */
			switch(modus){
				case "s":
				System.out.println("Entering Send Mode");
				try {
					ClientSend.sendChatMessage(config, id);
					System.out.println("Message " + config.getDefaultSenderName() + "." + id + " sent"); // TODO aufruf zum Benutzername reinmachen
					id++;
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
					break;
					
				case "r":
					System.out.println("Entering Receive Mode");
					ClientRecv recv = new ClientRecv(config, inputMsgQueue);
				try {
					recv.startRecv();
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
					break;
					
				default:
					System.out.println("Exit Program");
					run = false;
					break;	
			}
		}
	}
		
	
		/**
		 * 
		 * @return
		 * @throws IOException
		 */
	private static String determineModus() throws IOException{
		
		System.out.println("Sent Message [s], Receive Messages [r] or exit program [e]?");
		System.out.println("Input Parameter: ");
		String modus = "";
		
		
		while(true){
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			modus = br.readLine();
			
			System.out.println(modus);
			
			Pattern p = Pattern.compile("[s|r|e]");
			Matcher m = p.matcher(modus);
			if (m.find()){
				//System.out.println("Eingabe " + modus);
				break;
			}else{
				System.out.println("Invalid input: ");
				System.out.println("Sent Message [s], Receive Messages [r] or exit program [e]?");
			}
		}
		
		return modus;
	}

}
