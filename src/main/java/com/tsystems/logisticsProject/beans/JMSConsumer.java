package com.tsystems.logisticsProject.beans;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;
import java.util.logging.Logger;

@ApplicationScoped
@Named
public class JMSConsumer {

    private final String URL = ActiveMQConnectionFactory.DEFAULT_BROKER_BIND_URL;
    private final String TOPIC = "ActiveMQ.Advisory.Producer.Topic.LogisticsWebApp.update";
    private static final Logger LOG = Logger.getLogger(JMSConsumer.class.getName());

    @Inject
    private TopicSubscriber topicSubscriber;

    public void consume() {
        ConnectionFactory factory = new ActiveMQConnectionFactory(URL);
        try {
            Connection connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(TOPIC);
            MessageConsumer consumer = session.createConsumer(topic);
            consumer.setMessageListener(topicSubscriber);
        } catch (JMSException exp) {
            LOG.warning("JMS commection exception " + exp.getMessage());
        }
    }
}
