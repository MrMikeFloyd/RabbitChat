import com.rabbitmq.client.*;

import java.io.IOException;

public class RabbitTest2 {

  private final static String QUEUE_NAME = "hello";

  public static void main(String[] argv) throws Exception {
   
    Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
          throws IOException {
        String message = new String(body, "UTF-8");
        System.out.println(" [x] Received '" + message + "'");
      }
    };
    channel.basicConsume(QUEUE_NAME, true, consumer);
  }
}