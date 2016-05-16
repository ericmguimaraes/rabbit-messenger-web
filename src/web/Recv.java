package web;


import java.io.IOException;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer;

public class Recv {

  private final static String QUEUE_NAME = "mainha";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("ajalvesneto.koding.io");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME, true, false, false, null);
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
    QueueingConsumer consumer = new QueueingConsumer(channel);

    channel.basicConsume(QUEUE_NAME, true, consumer);

    while(true){
    	QueueingConsumer.Delivery delivery = consumer.nextDelivery();
        Gson gson = new Gson();
        Message message = gson.fromJson(new String(delivery.getBody()), Message.class);
        System.out.println(message);
    }
    
    //Consumer consumer = new DefaultConsumer(channel) {
      //@Override
//      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
//          throws IOException {
//        String message = new String(body, "UTF-8");
//        System.out.println(" [x] Received '" + message + "'");
//      }
    //};
    //channel.basicConsume(QUEUE_NAME, true, consumer);
  }
}