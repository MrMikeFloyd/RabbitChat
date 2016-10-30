package de.rabbitchat.common.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import de.rabbitchat.common.message.ChatMessage;
import de.rabbitchat.common.message.MsgType;

/**
 * Unit tests for chat message class.
 * 
 * @author maik
 *
 */
public class ChatMessageTest {

	@Test
	public final void testMessage() {
		ChatMessage cm1 = new ChatMessage(1234, "Maik", "Simon" ,"Moin Moin!");
		assertNotNull(cm1);
	}

	@Test
	public final void testGetId() {
		ChatMessage cm2 = new ChatMessage(1234, "Maik","Simon" , "Moin Moin!");
		assertEquals(1234, cm2.getId());
	}

	@Test
	public final void testGetType() {
		ChatMessage cm3 = new ChatMessage(1234, "Maik","Simon" , "Moin Moin!");
		assertEquals(MsgType.CHAT, cm3.getType());
	}

	@Test
	public final void testGetRecipient() {
		ChatMessage cm4 = new ChatMessage(1234, "Maik","Simon" , "Moin Moin!");
		assertEquals("Simon", cm4.getRecipient());
	}

	@Test
	public final void testGetPayload() {
		ChatMessage cm5 = new ChatMessage(1234, "Maik","Simon" , "Moin Moin!");
		assertEquals("Moin Moin!", cm5.getPayload());
	}

}
