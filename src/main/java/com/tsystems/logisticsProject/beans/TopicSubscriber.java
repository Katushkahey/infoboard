package com.tsystems.logisticsProject.beans;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.Serializable;
import java.util.logging.Logger;

@Named
public class TopicSubscriber implements MessageListener, Serializable {

    private static final Logger LOG = Logger.getLogger(TopicSubscriber.class.getName());

    @Inject
    public WebsocketPushBean pushBean;

    @Override
    public void onMessage(Message message) {
        LOG.info("Received message from LogisticsWebApp.update channel");
        pushBean.sendMessage();
    }
}