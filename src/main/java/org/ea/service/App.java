package org.ea.service;

import java.lang.Exception;
import java.io.IOException;

import com.rabbitmq.client.*;

public class App
{
    private final static String INPUT_QUEUE_NAME = "myinput";

    public static void main( String[] args ) {
      Connection connection = null;
      Channel channel = null;
      try {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.17.0.2");
        factory.setUsername("guest");
        factory.setPassword("guest");
        connection = factory.newConnection();
        channel = connection.createChannel();

        channel.queueDeclare(INPUT_QUEUE_NAME, false, false, false, null);

        String message = "102*40*3";
        channel.basicPublish("", INPUT_QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        try {
          if(channel != null) channel.close();
          if(connection != null) connection.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
}
