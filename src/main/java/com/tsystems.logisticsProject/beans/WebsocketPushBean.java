package com.tsystems.logisticsProject.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

@Stateful
@LocalBean
public class WebsocketPushBean implements Serializable {
//
//    private static final Logger LOG = Logger.getLogger(WebsocketPushBean.class.getName());

    @Inject
    @Push(channel = "websocket")
    private PushContext pushContext;

    void pushUpdate() {
        System.out.println("Pushing notification to jsf");
        pushContext.send("update");
    }
}

