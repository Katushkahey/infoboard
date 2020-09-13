package com.tsystems.logisticsProject.view;

import com.tsystems.logisticsProject.OrderDto;
import com.tsystems.logisticsProject.beans.JMSConsumer;
import org.primefaces.model.chart.PieChartModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;

@Named
@ApplicationScoped
public class InfoboardView {

    private JMSConsumer jmsConsumer;
    private InfoboardData infoboardData;

    @Inject
    public InfoboardView(JMSConsumer jmsConsumer, InfoboardData infoboardData) {
        this.jmsConsumer = jmsConsumer;
        this.infoboardData = infoboardData;
    }

    @PostConstruct
    public void init() {
        jmsConsumer.consume();
    }

    public List<OrderDto> getTopOrders() throws IOException {
        return infoboardData.getTopOrders();
    }

    public PieChartModel getDriversInfo() throws IOException {
        return infoboardData.getDriversInfo();
    }

    public PieChartModel getTrucksInfo() throws IOException {
        return infoboardData.getTrucksInfo();
    }
}
