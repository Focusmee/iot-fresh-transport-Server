package com.example.iotfreshtransportserver.controller;

import com.alibaba.fastjson.JSON;
import com.example.iotfreshtransportserver.domain.entity.LightInfo;
import com.example.iotfreshtransportserver.domain.entity.TemperatureInfo;
import com.example.iotfreshtransportserver.domain.uchart.Line;
import com.example.iotfreshtransportserver.domain.uchart.LineSeries;
import com.example.iotfreshtransportserver.service.LightInfoService;
import com.example.iotfreshtransportserver.service.TemperatureInfoService;
import com.example.iotfreshtransportserver.service.TransportCabinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/getLineData")
public class LineController {

    private final TransportCabinService transportCabinService;
    private final TemperatureInfoService temperatureInfoService;
    private final LightInfoService lightInfoService;

    @Autowired
    public LineController(TransportCabinService transportCabinService, TemperatureInfoService temperatureInfoService, LightInfoService lightInfoService) {
        this.transportCabinService = transportCabinService;
        this.temperatureInfoService = temperatureInfoService;
        this.lightInfoService = lightInfoService;
    }

    @GetMapping("/temperature/{vid}")
    public String getTemperatureData(@PathVariable Integer vid) {
        Line line = new Line();
        // 获取最新的6条数据
        List<TemperatureInfo> newList = temperatureInfoService.getNewList(vid,6);
        // Simulate the data retrieval from your data source
        // 横坐标时间
        List<String> timeStrings = newList.stream()
                .map(temperatureInfo -> String.valueOf(TemperatureInfo.formatDate(temperatureInfo.getTime())))
                .collect(Collectors.toList());
//        lineA.setCategories(Arrays.asList("2012", "2013", "2014", "2015", "2016", "2017"));
        line.setCategories(timeStrings);
        List<LineSeries> seriesList = new ArrayList<>();
        List<Double> tins = newList.stream()
                .map(TemperatureInfo::getTin)
                .collect(Collectors.toList());
        List<Double> touts = newList.stream()
                .map(TemperatureInfo::getTout)
                .collect(Collectors.toList());
        seriesList.add(new LineSeries("室外温度",tins));
        seriesList.add(new LineSeries("室内湿度",touts));

        line.setSeries(seriesList);
        System.out.println("LINE");
        return JSON.toJSONString(line);
    }
    @GetMapping("/light/{vid}")
    public String getLightData(@PathVariable Integer vid) {
        Line line = new Line();
        // 获取最新的6条数据
        List<LightInfo> newList = lightInfoService.getNewList(vid,6);
        // Simulate the data retrieval from your data source
        // 横坐标时间
        List<String> timeStrings = newList.stream()
                .map(lightInfo -> String.valueOf(lightInfo.formatDate(lightInfo.getTime())))
                .collect(Collectors.toList());
        line.setCategories(timeStrings);
        List<LineSeries> seriesList = new ArrayList<>();
        List<Double> lxins = newList.stream()
                .map(LightInfo::getLxin)
                .collect(Collectors.toList());
        List<Double> louts = newList.stream()
                .map(LightInfo::getLxd)
                .collect(Collectors.toList());
        seriesList.add(new LineSeries("厢内光照",lxins));
        seriesList.add(new LineSeries("厢外光照",louts));
        line.setSeries(seriesList);
        System.out.println("LINE");
        return JSON.toJSONString(line);
    }
}
