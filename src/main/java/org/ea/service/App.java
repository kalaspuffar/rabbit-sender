package org.ea.service;

import java.lang.Exception;
import java.io.IOException;

import com.rabbitmq.client.*;

public class App
{
    private final static String INPUT_QUEUE_NAME = "myinput";

    public static Channel connect() throws Exception {
      ConnectionFactory factory = new ConnectionFactory();
      factory.setHost("172.17.0.2");
      factory.setUsername("guest");
      factory.setPassword("guest");
      Connection connection = factory.newConnection();
      return connection.createChannel();
    }

    public static void main( String[] args ) {
      final Channel channel;
      try {
        channel = connect();

        channel.queueDeclare(INPUT_QUEUE_NAME, false, false, false, null);

        String message = "100/4";
        channel.basicPublish("", INPUT_QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
}
