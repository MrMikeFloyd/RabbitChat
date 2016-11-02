import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.xstream.XStream;

import de.rabbitchat.common.message.Message;
import de.rabbitchat.common.message.RcptMessage;

public class xStreamTest {

	public static void main(String[] args) {

		XStream xstream = new XStream();

		//RcptMessage cm1 = new RcptMessage(123, "Simon", "Hallo Simon, Deine Mudder ist ein XML-File.");
		
		Map<String, String> m1 = new HashMap<String, String>();
		
		
		m1.put("a","b");
		m1.put("b","a");

		String xml = xstream.toXML(m1);

		System.out.println(xml);

		/*System.out.println("---------------------------\n");

		Message cm2 = (Message) xstream.fromXML(xml);

		switch (cm2.getType()) {
		case CHAT:
			System.out.println("Dies ist eine Chatnachricht.");
			break;
		case RCPT:
			System.out.println("Dies ist eine Empfangsbestätigung.");
			break;
		default:
			throw new IllegalArgumentException("Message is of unknown type.");
		}
		System.out.println(cm2.getType());
		System.out.println(cm2.getPayload());
*/
	}

}
