package com.tsystems.logisticsProject.beans;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSConsumer {

    public void consume() {
        System.out.println("Зашли в метод consume");
        String url = "tcp://localhost:61616";
        ConnectionFactory factory = new ActiveMQConnectionFactory(url);
        System.out.println(" Создана connectionFactory");
        try {
            Connection connection = factory.createConnection();
            System.out.println("Создался connection");
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("ActiveMQ.Advisory.Producer.Topic.LogisticsWebApp.update");
            MessageConsumer consumer = session.createConsumer(topic);
            TopicSubscriber listener = new TopicSubscriber();
            consumer.setMessageListener(listener);
            connection.start();
        } catch (JMSException exp) {
        }
    }
}
