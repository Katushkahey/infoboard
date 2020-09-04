package com.tsystems.logisticsProject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements Serializable {

    private String orderNumber;
    private String startedCity;
    private String finishedCity;
    private String status;
}
