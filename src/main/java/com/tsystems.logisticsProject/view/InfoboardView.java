package com.tsystems.logisticsProject.view;

import com.tsystems.logisticsProject.OrderDto;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.primefaces.model.chart.PieChartModel;

import javax.ejb.Startup;
import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

@Named
@ApplicationScoped
@Startup
@Stateful
public class InfoboardView {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * gets list of latest orders from rest api
     * @return list of latest orders
     * @throws IOException for errors parsing the rest api response
     */
    public List<OrderDto> getTopOrders() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/infoboard/orders");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        return objectMapper.readValue(response, new TypeReference<List<OrderDto>>(){});
    }

    /**
     * gets driver statistics from rest api and builds a donut chart model
     * @return donut chart model
     * @throws IOException for errors parsing the rest api response
     */
    public PieChartModel getDriversInfo() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/infoboard/info/drivers");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        LinkedHashMap<String, Integer> driversInfo = objectMapper.readValue(response,
                new TypeReference<LinkedHashMap<String, Integer>>(){});
        int totalDrivers = driversInfo.values().stream().mapToInt(Integer::intValue).sum();

        PieChartModel model = new PieChartModel();
        driversInfo.forEach(model::set);
        model.setTitle(String.format("Drivers (total %d)", totalDrivers));
        model.setShowDataLabels(true);
        model.setLegendPosition("w");
        model.setShadow(true);

        return model;
    }
    public PieChartModel getTrucksInfo() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/infoboard/info/trucks");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        LinkedHashMap<String, Integer> trucksInfo = objectMapper.readValue(response,
                new TypeReference<LinkedHashMap<String, Integer>>(){});
        int totalTrucks = trucksInfo.values().stream().mapToInt(Integer::intValue).sum();

        PieChartModel model = new PieChartModel();
        trucksInfo.forEach(model::set);
        model.setTitle(String.format("Trucks (total %d)", totalTrucks));
        model.setShowDataLabels(true);
        model.setLegendPosition("w");
        model.setShadow(true);

        return model;
    }
}
