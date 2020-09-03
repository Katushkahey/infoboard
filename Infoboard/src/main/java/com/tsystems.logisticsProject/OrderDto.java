package com.tsystems.logisticsProject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private String orderNumber;
    private String startedCity;
    private String finishedCity;
    private String status;
}
