package com.tsystems.logisticsProject.beans;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.logging.Logger;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "LogisticsWebApp.update"),
        @ActivationConfigProperty (propertyName = "connectionParameters", propertyValue = "host = 127.0.0.1; port = 8161"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class TopicSubscriber implements MessageListener {

    private static final Logger LOG = Logger.getLogger(WebsocketPushBean.class.getName());

    @Inject
    private WebsocketPushBean pushBean;

    @Override
    public void onMessage(Message message) {
        System.out.println("Received message from LogisticsWebApp.update channel");
        pushBean.pushUpdate();
    }
}

