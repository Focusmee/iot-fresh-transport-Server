package com.example.iotfreshtransportserver.domain.entity.uchart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Line {
    private List<String> categories;
    private List<LineSeries> series;

    // Getters and setters (omitted for brevity)
}

