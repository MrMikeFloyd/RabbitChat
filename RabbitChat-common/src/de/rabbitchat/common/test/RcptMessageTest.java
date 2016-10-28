package de.rabbitchat.common.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import de.rabbitchat.common.message.MsgType;
import de.rabbitchat.common.message.RcptMessage;

/**
 * Unit tests for message receipts.
 * 
 * @author maik
 *
 */
public class RcptMessageTest {

	@Test
	public final void testMessage() {
		RcptMessage rm1 = new RcptMessage(1234, "Irgendwer", "Irgendwas.");
		assertNotNull(rm1);
	}

	@Test
	public final void testGetId() {
		RcptMessage rm2 = new RcptMessage(1234, "Irgendwer", "Irgendwas.");
		assertEquals(1234, rm2.getId());
	}

	@Test
	public final void testGetType() {
		RcptMessage rm3 = new RcptMessage(1234, "Irgendwer", "Irgendwas.");
		assertEquals(MsgType.RCPT, rm3.getType());
	}

	@Test
	public final void testGetRecipient() {
		RcptMessage rm4 = new RcptMessage(1234, "Irgendwer", "Irgendwas.");
		assertEquals("Irgendwer", rm4.getRecipient());
	}

	@Test
	public final void testGetPayload() {
		RcptMessage rm5 = new RcptMessage(1234, "Irgendwer", "Irgendwas.");
		assertEquals("Irgendwas.", rm5.getPayload());
	}

}
