package web;
import java.util.Scanner;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {

  private final static String QUEUE_NAME = "hello";

  public static void main(String[] argv) throws Exception {
    
	 ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("ajalvesneto.koding.io");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    String message = "";
    
    Scanner scanner = new Scanner(System.in);
    
    while (!message.equals("sair")){
    	message = scanner.nextLine();
    	channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
    }	
    
    channel.close();
    connection.close();
    
    
    
    

    
  }
}