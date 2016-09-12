package com.jianguo.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by Administrator on 2016/9/12.
 */
public class Send
{
    //��������
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception
    {
        /**
         * �����������ӵ�MabbitMQ
         */
        ConnectionFactory factory = new ConnectionFactory();
        //����MabbitMQ��������ip����������
        factory.setHost("localhost");
        //����һ������
        Connection connection = factory.newConnection();
        //����һ��Ƶ��
        Channel channel = connection.createChannel();
        //ָ��һ������
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //���͵���Ϣ
        String message = "hello world!";
        String message1 = "hello world11222!";
        //�������з���һ����Ϣ
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        channel.basicPublish("", QUEUE_NAME, null, message1.getBytes());
        channel.basicPublish("", QUEUE_NAME, null, message1.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        //�ر�Ƶ��������
        channel.close();
        connection.close();
    }
}
