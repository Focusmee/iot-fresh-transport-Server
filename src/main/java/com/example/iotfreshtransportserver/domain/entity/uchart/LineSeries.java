package com.example.iotfreshtransportserver.domain.entity.uchart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineSeries {
    private String name;
    private List<Double> data;
    private String color;

    public LineSeries(String name, List<Double> asList) {
        this.name=name;
        this.data=asList;

    }


    // Getters and setters (omitted for brevity)
}
