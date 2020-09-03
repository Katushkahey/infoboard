package com.tsystems.logisticsProject.beans;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "infoboard.update"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class TopicSubscriberBean implements MessageListener {

//    private static final Logger LOG = Logger.getLogger(WebsocketPushBean.class.getName());

    @Inject
    private WebsocketPushBean pushBean;

    @Override
    public void onMessage(Message message) {
//        LOG.info("Received message from logiweb.update channel");
        pushBean.pushUpdate();
    }
}

