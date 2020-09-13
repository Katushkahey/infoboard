package com.tsystems.logisticsProject.beans;

import javax.ejb.Startup;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Logger;

@Named
@Startup
public class WebsocketPushBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(WebsocketPushBean.class.getName());

    @Inject
    @Push(channel = "websocket")
    public PushContext pushContext;

    public void sendMessage() {
        LOG.info("Pushing notification to jsf");
        pushContext.send("update");
    }
}