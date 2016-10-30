import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitTest1 {

  private final static String QUEUE_NAME = "default-chat";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setUsername("rabbitclient-A");
    factory.setPassword("123456");
    factory.setHost("192.168.2.108");
    
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    String message = "Hallo Maik, hab ich nicht gesagt, dass das toll wird?????????!!1!!!!!!!!";
    channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
    System.out.println(" [x] Sent '" + message + "'");

    channel.close();
    connection.close();
  }
}