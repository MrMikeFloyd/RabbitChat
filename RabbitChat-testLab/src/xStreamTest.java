import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.xstream.XStream;

import de.rabbitchat.confighandler.ServerConfig;

public class xStreamTest {

	public static void main(String[] args) {

		XStream xstream = new XStream();

		// RcptMessage cm1 = new RcptMessage(123, "Simon", "Hallo Simon, Deine
		// Mudder ist ein XML-File.");

		Map<String, String> m1 = new HashMap<String, String>();
		m1.put("Maik", "qNameMaik");
		m1.put("Simon", "qNameSimon");

		ServerConfig s1 = new ServerConfig("localhost", "rabbitChat-Router", "passwd123", "recvChannel001", "deadLetterChannel001", m1);

		String xml = xstream.toXML(s1);

		System.out.println(xml);

		/*
		 * System.out.println("---------------------------\n");
		 * 
		 * Message cm2 = (Message) xstream.fromXML(xml);
		 * 
		 * switch (cm2.getType()) { case CHAT:
		 * System.out.println("Dies ist eine Chatnachricht."); break; case RCPT:
		 * System.out.println("Dies ist eine Empfangsbestätigung."); break;
		 * default: throw new
		 * IllegalArgumentException("Message is of unknown type."); }
		 * System.out.println(cm2.getType());
		 * System.out.println(cm2.getPayload());
		 */
	}

}
