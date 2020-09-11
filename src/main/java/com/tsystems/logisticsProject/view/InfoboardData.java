package com.tsystems.logisticsProject.view;

import com.tsystems.logisticsProject.OrderDto;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.primefaces.model.chart.PieChartModel;

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
public class InfoboardData {

    private ObjectMapper objectMapper = new ObjectMapper();
    private final String URL_TO_GET_ORDERS_BY_REST = "http://localhost:8080/infoboard/orders";
    private final String URL_TO_GET_DRIVERS_BY_REST = "http://localhost:8080/infoboard/info/drivers";
    private final String URL_TO_GET_TRUCKS_BY_REST = "http://localhost:8080/infoboard/info/trucks";
    private final String LEGEND_POSITION = "w";

    /**
     * gets list of top orders from rest api
     *
     * @return list of top orders
     * @throws IOException for errors parsing the rest api response
     */
    public List<OrderDto> getTopOrders() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL_TO_GET_ORDERS_BY_REST);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        return objectMapper.readValue(response, new TypeReference<List<OrderDto>>() {
        });
    }

    /**
     * gets driver statistics from rest api and builds a pie chart model
     *
     * @return pie chart model
     * @throws IOException for errors parsing the rest api response
     */
    public PieChartModel getDriversInfo() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL_TO_GET_DRIVERS_BY_REST);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        LinkedHashMap<String, Integer> driversInfo = objectMapper.readValue(response,
                new TypeReference<LinkedHashMap<String, Integer>>() {
                });

        int totalDrivers = driversInfo.values().stream().mapToInt(Integer::intValue).sum();
        return convertToPieChartModel(driversInfo, "Drivers", totalDrivers);
    }

    /**
     * gets truck statistics from rest api and builds a pie chart model
     *
     * @return pie chart model
     * @throws IOException for errors parsing the rest api response
     */
    public PieChartModel getTrucksInfo() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL_TO_GET_TRUCKS_BY_REST);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        LinkedHashMap<String, Integer> trucksInfo = objectMapper.readValue(response,
                new TypeReference<LinkedHashMap<String, Integer>>() {
                });

        int totalTrucks = trucksInfo.values().stream().mapToInt(Integer::intValue).sum();
        return convertToPieChartModel(trucksInfo, "Truck", totalTrucks);
    }

    private PieChartModel convertToPieChartModel(LinkedHashMap<String, Integer> hashMap, String title, int totalCount) {
        PieChartModel model = new PieChartModel();
        hashMap.forEach(model::set);
        model.setTitle(String.format(title + " (total %d)", totalCount));
        model.setShowDataLabels(true);
        model.setLegendPosition(LEGEND_POSITION);
        model.setShadow(true);

        return model;
    }
}
